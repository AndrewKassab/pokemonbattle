package pokemonbattle;

import java.util.Scanner;
import java.util.ArrayList;

/**
* Class to create, initialize, and store a Pokemon and their moves. 
* @TODO: Update status effects to be a class
* @author Andrew Kassab
*/
public class Pokemon{
  
  private final String name;
  private final int pokeID;
  private Type[] type; // TODO: Change to ArrayList 
  private Move[] moves = new Move[4];
  private Move activeMove;
  private Status[] status; // TODO: Combine into an ArrayList
  private Stats stats;
  
  public Pokemon(String name, int ID, Type[] types, int health, int attack, int defense, 
                  int spAttack, int spDefense, int speed, Move[] moves){
    this.name = name;
    this.pokeID = ID;
    this.type = types;
    this.moves = moves;
    stats = new Stats(health,attack,defense,spAttack,spDefense,speed);
    status = new Status[] {Status.NULLSTATUS, Status.NULLSTATUS};
  }
  
  
  public String getName(){
    return name;
  }
  
  public int getHealth(){
    return stats.health;
  }
  
  public int getMaxHealth() {
    return stats.maxHealth;
  }
  
  public void setHealth(int h){
    // Prevents negative values and values higher than max HP
    if (h <= 0) {
      stats.health = 0;
    }
    if (h >= stats.maxHealth) {
      stats.health = stats.maxHealth;
    }
    else stats.health = h;
  }
  
  public Type[] getType(){
    return type;
  }
  
  /**
   * Checks if a pokemon recives a same type attack bonus from the move they are using.
   * @param move The move being used
   * @return true if atleast one of the pokemon's types is equal to the move's type
   */
  public boolean hasSTAB( Move move ) {
    if (type[0] == move.getType() || type[1] == move.getType()) {
      return true;
    } else return false;
  }
  
  public Move[] getMoves() {
    return moves;
  }

  public void setMoves( Move[] moveset ) {
    moves = moveset;
  }
  
  public Move getActiveMove() {
    return activeMove;
  }

  public void setActiveMove(Move m) {
    activeMove = m;
  }
  
  /**
   * Returns the requested stat
   */
  public int getStat(Stat stat) {
    switch(stat) {
    case ATTACK:
      return stats.attack;
    case DEFENSE:
      return stats.defense;
    case SPATTACK:
      return stats.spAttack;
    case SPDEFENSE:
      return stats.spDefense;
    case SPEED:
      return stats.speed;
    case HEALTH:
      return stats.health;
    default:
      System.err.println("Invalid stat requested, exiting...");
      System.exit(-1);
      return -1;
    }
  }
  
  public int getPokeID() {
    return pokeID;
  }
  
  /**
   * Checks if the pokemon is fainted.
   * @return true if the Pokemon's health is zero
   */
  public boolean isFainted() {
    if (stats.health <= 0) {
      return true;
    }
    else return false;
  }
  
  // For lethal status'
  public Status[] getStatus() {
    return status;
  }

  public void setStatus(Status effect) {
    if ( effect.type == StatusType.LETHAL ) {
      status[0] = effect;
      status[0].counter = 0;
    } else {
      status[1] = effect;
      status[1].counter = 0;
    }
  }   

  public int getStatusCounter() {
    return statusCounter;
  }

  // For non-lethal status'
  public String getnonLethalStatus() {
    return nonLethal;
  }

  public void setNonLethalStatus(String effect) {
    nonLethalCounter = 0;
    nonLethal = effect;
  }

  public int getnonLethalCounter() {
    return nonLethalCounter;
  }
  
  /**
   * Applys a status effect to the battle.
   * For effects that work at the end of turns.
   */
  public void applyPostStatus(Trainer trainer) {
    
    if (status == null) {
      return;
    }
    
    switch (status) {
    case "burn":
      setHealth(getHealth() - (int) Math.round(getMaxHealth()/16.0));
      System.out.println(name + " is hurt by its burn!");
      if (isFainted()) {
        System.out.println(name + " has fainted!");
        trainer.selectPokemon();
      }
      return;
    case "poison":
      setHealth(getHealth() - (int) Math.round(getMaxHealth()/8.0));
      System.out.println(name + " is hurt by poison!");
      if (isFainted()) {
        System.out.println(name + " has fainted!");
        System.out.println();
        trainer.selectPokemon();
      }
      else System.out.println();
      return;
    case "badPoison":
      setHealth(getHealth() - (int) ( Math.round( getMaxHealth() * 
                  ( 1.0/16.0 + (statusCounter * 1.0/16.0) ) ) ) );
      statusCounter++;
      System.out.println(name + " is hurt by bad poison!");
      if (isFainted()) {
        System.out.println(name + " has fainted!");
        System.out.println();
        trainer.selectPokemon();
      }
      else System.out.println();
      return;
    }
  }   
  
  /**
   * Applys a status effect to the battle
   * For effects that work at the beginning of turns.
   * @throws InterruptedException 
   */
  public void applyPreStatus(Trainer trainer) throws InterruptedException {
    double random = Math.random();
    
    if (status == null)
    {
      return;
    }
    
    switch (status) {
    case "paralysis":
      System.out.println(name + " is paralyzed! It might be unable to move!");
      Thread.sleep(2000);
      if (random < .25) {
        System.out.println(name + " is paralyzed! It can't move!");
        System.out.println();
        trainer.setCanAttack(false);
      }
      return;
    case "frozen":
      if (Math.random() >= .20) {
        System.out.println(name + " is frozen solid!");
        System.out.println();
        trainer.setCanAttack(false);
      }
      else {
        System.out.println(name + " thawed out!");
        System.out.println();
      }
      return;
    case "sleep":
      if (statusCounter < 3) {
        if (random >= .33) { // 33% chance of waking up.
          System.out.println(name + " is fast asleep.");
          System.out.println();
          statusCounter++;
          trainer.setCanAttack(false);
        }
        else {
          System.out.println(name + " woke up!");
          System.out.println();
          setStatus(null);
          statusCounter = 0;
        }
      }
      else { // After the 3rd turn, gauranteed wake up.
        System.out.println(name + " woke up!");
      System.out.println();
      setStatus(null);
      statusCounter = 0;
      }
      return;
    }
  }
  
  /**
   * Applys a non-lethal status effect to the battle
   */
  public void applyNonLethal(Trainer trainer){
      
    if (nonLethal == null){
      return;
    }

    double random = Math.random();
  
    switch( nonLethal ){
    case "confusion":

      System.out.println(name + " is confused!");
      // been less than 4 turns
      if (nonLethalCounter < 4){
        // 33% chance of snapping out
        if (random >= .33){
          random = Math.random(); 
          // 33% chance of hurting self
          if (random < .33){
            System.out.println(name + " hurt itself in confusion!");
            // TODO: Fix length 
            int damage = (int) Math.round(((22 * 40 * (stats.attack/stats.defense))/50.0 + 2));
            setHealth(getHealth() - damage);
            trainer.setCanAttack(false);
            nonLethalCounter++;
            return;
          }
          // still confused but didn't attack self
          else {
            nonLethalCounter++;
            return;
          }
        }
      }
      
      // snaps out of confusion 
      System.out.println(name + " snapped out of it's confusion!");
      setNonLethalStatus(null);
      return;

    case "": // TODO add more non-lethals
      break;  
    }

  }
              
  public int getStage(Stat stat) {
    switch (stat) {
    case ATTACK:
      return stats.attackStage;
    case DEFENSE: 
      return stats.defenseStage;
    case SPATTACK:
      return stats.spAttackStage;
    case SPDEFENSE:
      return stats.spDefenseStage;
    case SPEED:
      return stats.speedStage;      
    default:
      System.err.println("Error, invalid stat passed in, exiting...");
      System.exit(0);
      return -1;
    }
  }
  
  /**
   * Increments a stat stage for a specific stat either up or down.
   * @param stat the stat being incremented.
   * @param direction + or - indicating up or down.
   */
  public void incrementStage(Stat stat, Operator op) {
    switch(stat) {
    case ATTACK:
      stats.incrementAttack(op);
      break;
    case DEFENSE: 
      stats.incrementDefense(op);
      break;
    case SPDEFENSE:
      stats.incrementSpDefense(op);
      break;
    case SPATTACK:
      stats.incrementSpAttack(op);
      break;
    case SPEED:
      stats.incrementSpeed(op);
      break;
    default:
      System.err.println("Invalid Stat, exiting...");
      System.exit(-1);
      break;
    }
  }
  
  /**
   * Resets all stat stages back to 0 and adjusts stats
   * accordingly
   */
  public void resetStages() {
    stats.attackStage = 0;
    stats.defenseStage = 0;
    stats.spAttackStage = 0;
    stats.spDefenseStage = 0;
    stats.speedStage = 0;
    stats.calculateAttack();
    stats.calculateDefense();
    stats.calculateSpAttack();
    stats.calculateSpDefense();
    stats.calculateSpeed();
  }

  /**
   * Sets activeMove to null before next turn.
   */
  public void resetMove() {
    activeMove = null;
  }
  
  /**
   * Prints the Pokemon's move list and their current 
   * PP values. 
   */
  public void printMoves() {
    System.out.printf("%-20s%-10s%-8s%-11s%-8s%n","Attack","Type","Power","Accuracy","PP");
    System.out.println("-------------------------------------------------------");
    for (int i = 0; i < moves.length; i++) {
      System.out.printf("%-20s%-10s%-8s%-11s%-8s%n", moves[i].getName(), moves[i].getType(), 
                          moves[i].getDamage(), (int) moves[i].getAccuracy() + "%", 
                            moves[i].getPP() + "/" + moves[i].getMaxPP());
    }
  }   
  
  /**
   * Handles move selection for trainer.
   * @return the move selected
   */
  public void selectMove(Trainer t) {
    
    Scanner keyboard = new Scanner(System.in);
    printMoves();
    String selection;
    System.out.println();
    System.out.print("Select a move for " + name + 
                       " (by name), or enter swap to switch Pokemon: ");
             
    selection = keyboard.nextLine();
    for (int i = 0; i < moves.length; i++) {
      if (selection.equalsIgnoreCase(moves[i].getName())) {
        if (moves[i].hasPP()) {
          System.out.println();
          activeMove = moves[i];
          return;
        }
        else {
          System.out.println("Out of power points, please try again.");
          System.out.println();
          selectMove(t);
        }
      }
        // Chooses to switch out Pokemon
        // TODO: Add ability to cancel and return to move selection
      if (selection.equalsIgnoreCase("swap")) {
        System.out.println();
        t.selectPokemon();
        return;
      }
    }
  
    System.out.println("Invalid move, please try again.");    
    selectMove(t);
  }
  
  /**
   * Checks if a Pokemon can battle (if it is above 0 health)
   * @return true if Pokemon can be called to battle
   */
  public boolean canBattle() {
    if (stats.health > 0) {
      return true;
    }
    else return false;
  }
  
}

/**
 * Stores and manipulates pokemon's stats.
 * @author precisemotion
 */
class Stats {
  
  int health;
  final int maxHealth;
  int attack;
  final int baseAttack;
  int spAttack;
  final int baseSpAttack;
  int defense;
  final int baseDefense;
  int spDefense;
  final int baseSpDefense;
  int speed;
  final int baseSpeed;

  int attackStage = 0;
  int spAttackStage = 0;
  int defenseStage = 0;
  int spDefenseStage = 0;
  int speedStage = 0; 

  public Stats( int h, int a, int sA, int d, int sD, int speed ) {
    health = h;
    maxHealth = h;
    attack = a;
    baseAttack = a;
    spAttack = sA;
    baseSpAttack = sA;
    defense = d;
    baseDefense = d;
    spDefense = sD;
    baseSpDefense = sD;
    this.speed = speed;
    baseSpeed = speed;
  }

  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  public void calculateSpeed() {
    speed = (int) (baseSpeed * Math.round( ( Math.max(2.0,2.0+speedStage) )/
                                           ( Math.max(2.0,2.0-speedStage) ) ) );
  }

  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  public void calculateAttack() {
    attack = (int) (attack * Math.round( ( Math.max(2.0,2.0+attackStage) )/
                                           ( Math.max(2.0,2.0-attackStage) ) ) );
  }
  
  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  public void calculateSpAttack() {
    spAttack = (int) (baseSpAttack * Math.round( ( Math.max(2.0,2.0+spAttackStage) )/
                                           ( Math.max(2.0,2.0-spAttackStage) ) ) );
  }
  
  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  public void calculateDefense() {
    defense = (int) (baseDefense * Math.round( ( Math.max(2.0,2.0+defenseStage) )/
                                           ( Math.max(2.0,2.0-defenseStage) ) ) );
  }

  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  public void calculateSpDefense() {
    spDefense = (int) (baseSpDefense * Math.round( ( Math.max(2.0,2.0+spDefenseStage) )/
                                           ( Math.max(2.0,2.0-spDefenseStage) ) ) );
  }
  
  public void incrementAttack( Operator op ) {
    switch(op) {
    case INCREMENT:
      if (attackStage < 6){
        attackStage++;
        calculateAttack();
      }
      break;
    case DECREMENT:
      if (attackStage > -6) {
        attackStage--;
        calculateAttack();
      }    
      break;
    }
  }

  public void incrementDefense( Operator op) {
    switch(op) {
    case INCREMENT:
      if (defenseStage < 6){
        defenseStage++;
        calculateDefense();
      }
      break;
    case DECREMENT:
      if (defenseStage > -6) {
        defenseStage--;
        calculateDefense();
      }    
      break;
    }
  }

  public void incrementSpDefense( Operator op) {
    switch(op) {
    case INCREMENT:
      if (spDefenseStage < 6){
        spDefenseStage++;
        calculateSpDefense();
      }
      break;
    case DECREMENT:
      if (spDefenseStage > -6) {
        spDefenseStage--;
        calculateSpDefense();
      }    
      break;
    }
  }
  
  public void incrementSpAttack( Operator op ) {
    switch(op) {
    case INCREMENT:
      if (spAttackStage < 6){
        spAttackStage++;
        calculateSpAttack();
      }
      break;
    case DECREMENT:
      if (spAttackStage > -6) {
        spAttackStage--;
        calculateSpAttack();
      }    
      break;
    }
  }
  
  public void incrementSpeed( Operator op ) {
    switch(op) {
    case INCREMENT:
      if (speedStage < 6){
        speedStage++;
        calculateSpeed();
      }
      break;
    case DECREMENT:
      if (speedStage > -6) {
        speedStage--;
        calculateSpeed();
      }    
      break;
    }
  }
}
