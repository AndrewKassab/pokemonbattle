package pokemonbattle;

import java.util.Scanner;

/**
 * Class to create, initialize, and store a Pokemon and their moves. 
 * TODO: Add status effects.
 * @version 5.21
 * @author Andrew Kassab
*/
public class Pokemon
{
    
    private final String name;
    private final int pokeID;
    private int health;
    private int maxHealth;
    private String[] type;
    private int attack;
    private int baseAttack;
    private int spAttack;
    private int baseSpAttack;
    private int defense;
    private int baseDefense;
    private int spDefense;
    private int baseSpDefense;
    private int speed;
    private int baseSpeed;
    private Move[] moves = new Move[4];
    private Move activeMove;
    private String status = ""; // TODO: Implement Status Effect
    
    private int attackStage = 0;
    private int spAttackStage = 0;
    private int defenseStage = 0;
    private int spDefenseStage = 0;
    private int speedStage = 0;
    
    public Pokemon(String n, int ID, String[] a, int h, int att, int def, int spAtt, int spDef, int s, Move[] m){
        name = n;
        pokeID = ID;
        health = h;
        type = a;
        attack = att;
        baseAttack = att;
        spAttack = spAtt;
        baseSpAttack = spAtt;
        defense = def;
        baseDefense = def;
        spDefense = spDef;
        baseSpDefense = spDef;
        speed = s;
        baseSpeed = s;
        moves = m;
        maxHealth = h;
    }
    
    
    public String getName(){
        return name;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth() {
    	return maxHealth;
    }
    
    public void setHealth(int h){
        health = h;
    }
    
    public String[] getType(){
        return type;
    }
    
    public int getSpeed(){
        return speed;
    }   
    public void setSpeed() {
    	speed = (int) baseSpeed * Math.round( (Math.max(2,2+speedStage))/(Math.max(2,2-speedStage)) ) ;
    }
    
    public Move[] getMoves() {
    	return moves;
    }
    
    public Move getActiveMove() {
    	return activeMove;
    }
    public void setActiveMove(Move m) {
    	activeMove = m;
    }
    
    public int getAttack() {
    	return attack;
    }
    public void setAttack() {
    	attack = (int) baseAttack * Math.round( (Math.max(2,2+attackStage))/(Math.max(2,2-attackStage)) ) ;
    }
    
    public int getSpAttack() {
    	return spAttack;
    }
    public void setSpAttack() {
    	spAttack = (int) baseSpAttack * Math.round( (Math.max(2,2+spAttackStage))/(Math.max(2,2-spAttackStage)) ) ;
    }
    
    public int getDefense() {
    	return defense;
    }
    public void setDefense() {
    	defense = (int) baseDefense * Math.round( ( Math.max(2,2+defenseStage) )/( Math.max(2,2-defenseStage)) ) ;
    }
    
    public int getSpDefense() {
    	return spDefense;
    }
    public void setSpDefense() {
    	spDefense = (int) baseSpDefense * Math.round( (Math.max(2,2+spDefenseStage))/(Math.max(2,2-spDefenseStage)) ) ;
    }
    
    public int getPokeID() {
		return pokeID;
	}
    
    public String getStatus() {
    	return status;
    }
    public void setStatus(String effect) {
    	status = effect;
    }
    
    public void applyStatus() {
    	switch (status) {
    		case "burn":
    			
    	}
    }
    
    
    public int getStage(String stat) {
    	switch (stat) {
    		case "Attack":
    			return attackStage;
    		case "Defense":
    			return defenseStage;
    		case "spAttack":
    			return spAttackStage;
    		case "spDefense":
    			return spDefenseStage;
    		case "Speed":
    			return speedStage;    	
    	}
    	return 0;
    }
    
    /**
     * Increments a stat stage for a specific stat either up or down.
     * @param stat the stat being incremented.
     * @param direction + or - indicating up or down.
     */
    public void incrementStage(String stat, String direction) {
    	switch(stat) {
    		case("Attack"):
    			if (direction.equals("+")) {
    				if (attackStage < 6){
    					attackStage++;
    					setAttack();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (attackStage > -6) {
    					attackStage--;
    					setAttack();
    				}		
    			}
    		return;
    		case("Defense"):
    			if (direction.equals("+")) {
    				if (defenseStage < 6) {
    					defenseStage++;
    					setDefense();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (defenseStage > -6 ) {
    					defenseStage--;
    					setDefense();
    				}
    			}
    		return;
    		case("SpDefense"):
    			if (direction.equals("+")) {
    				if (spDefenseStage < 6) {
    					spDefenseStage++;
    					setSpDefense();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (spDefenseStage > -6) {
    					spDefenseStage--;
    					setSpDefense();
    				}
    			}
    		return;
    		case("SpAttack"):
    			if (direction.equals("+")) {
    				if (spAttackStage < 6) {
    					spAttackStage++;
    					setSpAttack();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (spAttackStage > -6) {
    					spAttackStage--;
        				setSpAttack();
    				}
    			}
    		return;
    		case("Speed"):
    			if (direction.equals("+")) {
    				if (speedStage < 6) {
    					speedStage++;
    					setSpeed();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (speedStage > -6) {
    					speedStage--;
    					setSpeed();
    				}
    			}
    		return;
    	}
    }
    
    /**
     * Resets all stat stages back to 0
     */
    public void resetStages() {
    	attackStage = 0;
    	defenseStage = 0;
    	spAttackStage = 0;
    	spDefenseStage = 0;
    	speedStage = 0;
    	setAttack();
    	setDefense();
    	setSpAttack();
    	setSpDefense();
    	setSpeed();
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
    				moves[i].getDamage(), (int) moves[i].getAccuracy() + "%", moves[i].getPP() + "/" 
    						+ moves[i].getMaxPP());
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
    	System.out.print("Select a move for " + name + " (by name), or enter swap to switch Pokemon: ");
    	     		
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
    	if (health > 0) {
    		return true;
    	}
    	else return false;
    }
    
    /**
     * TODO: Create Pokemon Factory
     */
    
}
