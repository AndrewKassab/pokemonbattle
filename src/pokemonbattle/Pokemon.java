package pokemonbattle;

import java.util.Scanner;

/**
* Class to create, initialize, and store a Pokemon 
* @author Andrew Kassab
*/
public class Pokemon{
  
  private final String name;
  private final int pokeID;
  private Type[] type; // TODO: Change to ArrayList 
  private Move[] moves = new Move[4];
  private Move activeMove;
  private Status[] status; // ( 0 -> lethal, 1 -> nonlethal )
  //TODO:
  // private Status lethalStatus
  // private Status nonLethalStatus
  private Stats stats;
  
  public Pokemon(String name, int ID, Type[] types, int health, int attack, int defense, 
                  int spAttack, int spDefense, int speed, Move[] moves){
    this.name = name;
    this.pokeID = ID;
    this.type = types;
    this.moves = moves;
    stats = new Stats(health,attack,spAttack,defense,spDefense,speed);
    status = new Status[] {Status.NULLSTATUS, Status.NULLSTATUS};
  }
  
  public String getName(){
    return name;
  }
  
  public int getHealth(){
    return stats.health.value;
  }
  
  public int getMaxHealth() {
    return stats.health.base;
  }
  
  public int getAttack() {
    return stats.attack.value;
  }

  public int getSpAttack() {
    return stats.spAttack.value;
  }

  public int getDefense() {
    return stats.defense.value;
  }

  public int getSpDefense() {
    return stats.spDefense.value;
  }

  public int getSpeed() {
    return stats.spDefense.value;
  }
  
  
  
  public void setHealth(int health){
    // Prevents negative values and values higher than max HP
    if (health <= 0) {
      stats.health.value = 0;
    }
    if (health >= stats.health.base) {
      stats.health.value = stats.health.base;
    }
    else stats.health.value = health;
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
  
  public int getPokeID() {
    return pokeID;
  }
  
  /**
   * Checks if the pokemon is fainted.
   * @return true if the Pokemon's health is zero
   */
  public boolean isFainted() {
    if (stats.health.value <= 0) {
      return true;
    }
    else return false;
  }
  
  // For lethal status'
  public Status getLethalStatus() {
    return status[0];
  }

  public void setStatus(Status effect, StatusType type) {
    if ( type == StatusType.LETHAL ) {
      status[0] = effect;
      status[0].counter = 0;
    } else {
      status[1] = effect;
      status[1].counter = 0;
    }
  }   

  // For non-lethal status'
  public Status getnonLethalStatus() {
    return status[1];
  }
  
  /**
   * Resets all stat stages back to 0 stat values to base
   */
  public void resetStats() {
    stats.attack.resetStage();
    stats.defense.resetStage();
    stats.spAttack.resetStage();
    stats.spDefense.resetStage();
    stats.speed.resetStage();
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
    if (stats.health.value > 0) {
      return true;
    }
    else return false;
  }
  
}