package pokemonbattle;

import java.util.Scanner;
import pokemonbattle.Battle;

/**
 * Class to create and initialize the Trainer object. 
 * A trainer has a party of Pokemon, and an identifier for which
 * Pokemon is currently active and in battle. 
 * TODO: Bugs, damage values not consistent with earthquake ? (charizard vs blastoise)
 * TODO: Add Player and CPU extend Trainer class
 * @version 7.3
 * @author precisemotion
 */
public class Trainer{
  
  private Pokemon activePokemon;
  private Pokemon[] party;
  private String[] inventory;
  private boolean canAttack;
  
  public Trainer(Pokemon[] team) {
    party = team;
    canAttack = true;
  }
  
  /**
   * Returns the trainer's current active pokemon in battle
   */
  public Pokemon getActivePokemon() {
    return activePokemon;
  }
  
  public void setCanAttack(boolean b) {
    canAttack = b;
  }
  
  /**
   * Checks if the trainer can continue their attack turn. If
   * their Pokemon has just fainted, canAttack will be false and
   * the battle moves onto the next turn.
   * @return if the turn can continue
   */
  public boolean canAttack() {
    return canAttack;
  }
  
  /**
   * Displays a trainers current party Pokemon and their status.
   */
  public void displayPokemon() {
    System.out.printf("%-15s%7s%n","POKEMON","HEALTH");
    System.out.println("-----------------------");
    for (int i = 0; i < party.length; i++) {
      System.out.printf("%-15s%-8s%n", party[i].getName(), 
                          party[i].getHealth().getValue() + "/" + party[i].getMaxHealth());
    }
    System.out.println();
  }
  
  /**
   * Handles Pokemon selection for a trainer. 
   * activePokemon variable is set to the choice made.
   */
  public void selectPokemon() {
    
    // If the trainer has no Pokemon left to battle 
    // TODO: Doesn't account for when both Pokemon die to a status
    if (!canContinue()) {
      battleEnded();
    }
    
    Scanner keyboard = new Scanner(System.in);
    String selection;
    displayPokemon();
    System.out.print(Messages.SEL_POKE);
      
    selection = keyboard.next();
    System.out.println();
      
    for (int i = 0; i < party.length; i++) {

      if (party[i].getName().equalsIgnoreCase(selection)){
        if (party[i].canBattle()) { 
          if (party[i] == activePokemon) {
            System.out.println(Messages.ALREADY_IN);
            selectPokemon();
          }
          if (activePokemon == null) {
            System.out.printf(Messages.POKE_SUMMON, party[i].getName());
          }
          else {
            activePokemon.resetStats();
          }
          activePokemon = party[i];
          return;
        }
        else {
          System.out.printf(Messages.POKE_UNABLE, party[i].getName() );
          System.out.println();               
          selectPokemon();
        }
      }

    }
    
    // Invalid pokemon selected, restart method
    System.out.println(Messages.INVALID_POKE);
    System.out.println();
    selectPokemon(); 

  }
    
  /**
   * Carries out an attack during the round and determines the
   * appropriate calculationTwo values by comparing types. Outputs
   * the results.
   * TODO: Seperate Method to make cleaner code  
   * @param t the trainer being attacked
   */
  public void Attack (Trainer enemy)
  {   
    
    Pokemon target = enemy.getActivePokemon();
    Move move = activePokemon.getActiveMove();     
       
    // If the trainer chose to swap Pokemon rather than attack.
    if (move == null) {
      System.out.printf(Messages.POKE_SWAP, activePokemon.getName());
      return;
    }
    
    // If the move is not an attack move
    if (move.getDamage() == 0) {
      move.useEffect(this, enemy);
      move.setPP(move.getPP() - 1);
      return;
    }
         
    // Calculate the damage being done
    int damage = Battle.calculateDamage( move, activePokemon, target );
        
    // Check if the attack has landed
    if (move.hit()){
      System.out.printf(Messages.MOVE_USE, activePokemon.getName() , move.getName()); 
      // Applys an attack's effect (if it has one) 
      if (move.hasEffect()) {
        move.applyEffect(this,enemy,damage);
      }
      
      // Display damage and effect messages
      Battle.displayMessages(damage, move, target);
      
      // Apply damage
      target.setHealth(target.getHealth().getValue() - damage);
              
      // If the target pokemon has fainted
      if (target.isFainted())
      {
        System.out.printf(Messages.HAS_FAINTED, target.getName());
        if (enemy.canContinue()) {
          System.out.println(Messages.SEL_NEW_POKE);
          System.out.println();
          enemy.selectPokemon();
          enemy.setCanAttack(false);
          target = enemy.getActivePokemon();
          System.out.printf(Messages.POKE_SUMMON, target.getName());
        }
        // Battle ends if trainer can't continue
        else {
          battleEnded();
        }
      }
      
      // When a fire move is used on a frozen opponent
      if ( ( target.getLethalStatus() == Status.FROZEN ) && ( move.getType() == Type.FIRE ) ){
        System.out.println(target.getName() + " thawed out!");
        System.out.println();
        target.setStatus(Status.NULLSTATUS, StatusType.LETHAL);
      }
      
      // If the attacking Pokemon has fainted from recoil 
      // TODO: Fix recoil ordering 
      if (activePokemon.getHealth().getValue() <= 0) {
        System.out.printf(Messages.HAS_FAINTED ,activePokemon.getName());
        if (canContinue()) {
          System.out.println(Messages.SEL_NEW_POKE);
          System.out.println();
          selectPokemon();
          setCanAttack(false);
          System.out.printf(Messages.POKE_SUMMON, activePokemon.getName());
        
        }
        // Battle ends if trainer can't continue
        else {
          battleEnded();
        }
      }
    }
    else{
      System.out.printf(Messages.MOVE_USE, activePokemon.getName(), move.getName()); 
      System.out.println(Messages.MISSED_EFF);
    }    
    
    activePokemon.resetMove();        
      
  }
    
    /**
     * Checks if the trainer has Pokemon in their party that can still battle
     * @return true if the trainer can switch out another Pokemon
     */
  public boolean canContinue() {
    for (int i = 0; i < party.length; i++) {
      if (party[i].getHealth().getValue() > 0) {
        return true;
      }
    }
    return false;
  }
    
  /**
   * Handles an event where a trainer is unable to continue battling
   * and the battle must end.
   * TODO: Call some method in Battle class
  */
  public static void battleEnded(){
      System.out.println(Messages.BATTLE_END);
      System.exit(0);
  }
  
}