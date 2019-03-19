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
  private int activeIndex;
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
                          party[i].getHealth() + "/" + party[i].getMaxHealth());
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
    System.out.print("Select a Pokemon (by name): ");
      
    selection = keyboard.next();
    System.out.println();
      
    for (int i = 0; i < party.length; i++) {

      if (party[i].getName().equalsIgnoreCase(selection)){
        if (party[i].canBattle()) { 
          if (party[i] == activePokemon) {
            System.out.println("That Pokemon is already in battle!\n");
            selectPokemon();
          }
          if (activePokemon == null) {
            System.out.println("Let's go, " + party[i].getName() + "!\n");
          }
          else {
            activePokemon.resetStages();
          }
          activePokemon = party[i];
          activeIndex = i;               
          return;
        }
        else {
          System.out.println(party[i].getName() + " is unable to battle, please try again.");
          System.out.println();               
          selectPokemon();
        }
      }

    }
    
    System.out.println("Invalid pokemon selected, please try again.");
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
      System.out.println("The trainer has swapped to " + activePokemon.getName() + "!\n");
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
      System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
      
      // Applys an attack's effect (if it has one) 
      if (move.hasEffect()) {
        move.applyEffect(this,enemy,damage);
      }
      
      // Display damage and effect messages
      Battle.displayMessages(damage, move, target);
      
      // Apply damage
      target.setHealth(target.getHealth() - damage);
              
      // If the target pokemon has fainted
      if (target.getHealth() <= 0)
      {
        System.out.println(target.getName() + " has fainted!");
        if (enemy.canContinue()) {
          System.out.println("Select another Pokemon from your party.");
          System.out.println();
          enemy.selectPokemon();
          enemy.setCanAttack(false);
          target = enemy.getActivePokemon();
          System.out.println("Let's go, " + target.getName() + "!\n");
        }
        // Battle ends if trainer can't continue
        else {
          battleEnded();
        }
      }
      
      // When a fire move is used on a frozen opponent
      if (target.getStatus() != null && 
        target.getStatus().equals("frozen") && move.getType().equals("fire")) {
        System.out.println(target.getName() + " thawed out!");
        System.out.println();
        target.setStatus(null);
      }
      
      // If the attacking Pokemon has fainted from recoil 
      // TODO: Fix recoil ordering 
      if (getActivePokemon().getHealth() <= 0) {
        System.out.println(getActivePokemon().getName() + " has fainted!");
        if (canContinue()) {
          System.out.println("Select another Pokemon from your party.");
          System.out.println();
          selectPokemon();
          setCanAttack(false);
          System.out.println("Let's go, " + getActivePokemon().getName() + "!");
        
        }
        // Battle ends if trainer can't continue
        else {
          battleEnded();
        }
      }
    }
    else{
      System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
      System.out.println("But it missed!\n");
    }    
    
    activePokemon.resetMove();        
      
  }
    
    /**
     * Checks if the trainer has Pokemon in their party that can still battle
     * @return true if the trainer can switch out another Pokemon
     */
  public boolean canContinue() {
    for (int i = 0; i < party.length; i++) {
      if (party[i].getHealth() > 0) {
        return true;
      }
    }
    return false;
  }
    
  /**
   * Handles an event where a trainer is unable to continue battling
   * and the battle must end.
  */
  public static void battleEnded(){
    // TODO: Add names for trainers??
      System.out.println("The battle has ended!");
      System.exit(0);
  }
  
}
