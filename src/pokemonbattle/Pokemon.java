package pokemonbattle;

import java.util.Scanner;

/**
 * Class to create, initialize, and store a Pokemon and their moves. 
 * @version 7.1
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
    private String status = null; // TODO: Implement Status Effect
    private int statusCounter = 0;
    
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
    	// Prevents negative values and values higher than max HP
        if (h <= 0) {
        	health = 0;
        }
        if (h >= maxHealth) {
        	health = maxHealth;
        }
        else health = h;
    }
    
    public String[] getType(){
        return type;
    }
    
    public int getSpeed(){
        return speed;
    }   
    public void setSpeed(int sp) {
    	speed = sp;
    }
    
    /**
     * Factors in stat stages to calculate a Pokemon's stat in battle.
     */
    public void calculateSpeed() {
    	speed = (int) (baseSpeed * Math.round( (Math.max(2.0,2.0+speedStage))/(Math.max(2.0,2.0-speedStage)) )) ;
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
    
    public int getAttack() {
    	return attack;
    }
    public void setAttack(int att) {
    	attack = att;
    }
    
    /**
     * Factors in stat stages to calculate a Pokemon's stat in battle.
     */
    public void calculateAttack() {
    	attack = (int) (baseAttack * Math.round( (Math.max(2.0,2.0+attackStage))/(Math.max(2.0,2.0-attackStage)) )) ;
    }
    
    public int getSpAttack() {
    	return spAttack;
    }
    public void setSpAttack(int spAtt) {
    	spAttack = spAtt;
    }
    
    /**
     * Factors in stat stages to calculate a Pokemon's stat in battle.
     */
    public void calculateSpAttack() {
    	spAttack = (int) (baseSpAttack * Math.round( (Math.max(2.0,2.0+spAttackStage))/(Math.max(2.0,2.0-spAttackStage)) )) ;
    }
    
    public int getDefense() {
    	return defense;
    }
    public void setDefense(int def) {
    	defense = def;
    }
    
    /**
     * Factors in stat stages to calculate a Pokemon's stat in battle.
     */
    public void calculateDefense() {
    	defense = (int) ( baseDefense * Math.round( ( Math.max(2.0,2.0+defenseStage) )/( Math.max(2.0,2.0-defenseStage)) )) ;
    }
    
    public int getSpDefense() {
    	return spDefense;
    }
    public void setSpDefense(int spDef) {
    	spDefense = spDef;
    }
    
    /**
     * Factors in stat stages to calculate a Pokemon's stat in battle.
     */
    public void calculateSpDefense() {
    	spDefense = (int) ( baseSpDefense * Math.round( (Math.max(2.0,2.0+spDefenseStage))/(Math.max(2.0,2.0-spDefenseStage)) )) ;
    }
    
    public int getPokeID() {
		return pokeID;
	}
    
    /**
     * Checks if the pokemon is fainted.
     * @return true if the Pokemon's health is zero
     */
    public boolean isFainted() {
    	if (health <= 0) {
    		return true;
    	}
    	else return false;
    }
    
    public String getStatus() {
    	return status;
    }
    public void setStatus(String effect) {
    	status = effect;
    }
    
    public int getStatusCounter() {
    	return statusCounter;
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
    			setHealth(getHealth() - (int) ( Math.round( getMaxHealth() * ( 1.0/16.0 + (statusCounter * 1.0/16.0) ) ) ) );
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
    public void applyPreStatus(Trainer t) throws InterruptedException {
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
    				t.setCanAttack(false);
    			}
    			return;
    		case "confusion":
    			
    			return;			
    		case "frozen":
    			if (Math.random() >= .20) {
    				System.out.println(name + " is frozen solid!");
    				System.out.println();
    				t.setCanAttack(false);
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
    					t.setCanAttack(false);
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
    					calculateAttack();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (attackStage > -6) {
    					attackStage--;
    					calculateAttack();
    				}		
    			}
    		return;
    		case("Defense"):
    			if (direction.equals("+")) {
    				if (defenseStage < 6) {
    					defenseStage++;
    					calculateDefense();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (defenseStage > -6 ) {
    					defenseStage--;
    					calculateDefense();
    				}
    			}
    		return;
    		case("SpDefense"):
    			if (direction.equals("+")) {
    				if (spDefenseStage < 6) {
    					spDefenseStage++;
    					calculateSpDefense();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (spDefenseStage > -6) {
    					spDefenseStage--;
    					calculateSpDefense();
    				}
    			}
    		return;
    		case("SpAttack"):
    			if (direction.equals("+")) {
    				if (spAttackStage < 6) {
    					spAttackStage++;
    					calculateSpAttack();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (spAttackStage > -6) {
    					spAttackStage--;
    					calculateSpAttack();
    				}
    			}
    		return;
    		case("Speed"):
    			if (direction.equals("+")) {
    				if (speedStage < 6) {
    					speedStage++;
    					calculateSpeed();
    				}
    			}
    			else if (direction.equals("-")) {
    				if (speedStage > -6) {
    					speedStage--;
    					calculateSpeed();
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
    	calculateAttack();
    	calculateDefense();
    	calculateSpAttack();
    	calculateSpDefense();
    	calculateSpeed();
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
