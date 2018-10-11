package pokemonbattle;

/** 
 * Class to create, initialize, and store pokemon moves with their
 * respective varibles. 
 * TODO: Fix U-Turn and Volt-Switch Bugs
 * @version 7.1
 * @author Andrew Kassab
 */
public class Move
{
    private final String name;
    private final String type;
    private int damage;
    private double accuracy;
    private int powerPoints;
    private int maxPowerPoints;
    private int priority; // label for speed priority
    private boolean physical; // physical label
    private boolean special; // special label
    private boolean effect; // if a move has an effect
    private String effectMessage; 
      
    public Move(String n, String a, int d, double h, int pp, int pri, boolean ph, boolean sp, boolean hasEffect){
        name = n;
        type = a;
        damage = d;
        accuracy = h;
        powerPoints = pp;
        maxPowerPoints = pp;
        priority = pri;
        physical = ph;
        special = sp;
        effect = hasEffect;
    }
    
    public String getType(){
        return type;
    }
    
    public String getName(){
        return name;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public double getAccuracy() {
    	return accuracy;
    }
    public void setAccuracy(int a) {
    	accuracy = a;
    }
    
    public int getPP() {
    	return powerPoints;
    }
    public int getMaxPP() {
    	return maxPowerPoints;
    }
    public void setPP(int pp) {
    	powerPoints = pp;
    }
    
    /**
     * Checks if a move is a physical move.
     * @return true if the move is a physical move.
     */
    public boolean isPhysical() {
    	return physical;
    }
    
    /**
     * Checks if a move is a special move.
     * @return true if the move is a special move.
     */
    public boolean isSpecial() {
    	return special;
    }
    
    /**
     * Checks if a move can be used (if it has any power points left)
     * @return true if a move can be used.
     */
    public boolean hasPP() {
    	if (powerPoints >= 1) {
    		return true;
    	}
    	else return false;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public boolean hasEffect() {
    	return effect;
    }
    
    public String getMessage() {
    	return effectMessage;
    }
    public void resetMessage() {
    	effectMessage = null;
    }
    
    public void printMessage() {
    	System.out.println(effectMessage);
    }
        
    /**
     * Determines if a move hits in its attack turn by
     * generating a random number and checking the accurary value
     * @return whether the attack landed or not.
     */
    public boolean hit() 
    {
    	double random = Math.random();
    	if (random <= accuracy/100){
           return true;
    	} 
    	else return false;
    }
    
    /**
     * Calculates if a move is a critical hit or not. 
     * @return true if critical.
     */
    public boolean isCritical() {
    	
    	double random = Math.random();
    	if(random <= .0625) {
    		return true;
    	}
    	else return false;
    }
    
    /**
     * Takes in a move, and then determines what types this move
     * is super effective against.
     * @param m Move being used.
     * @return String array containing all effective types.
     */
    public String[] getPosEffects()
    {
        String[] result = new String[]{""};
        
        // Checks type of move
        switch (type){ 
        	case "normal": 
                return result;
            case "fire": 
                result = new String[]{"grass","ice","bug","steel"};
                return result;
            case "water": 
                result = new String[]{"fire","ground","rock"};
                return result;
            case "electric": 
                result = new String[]{"water","flying"};
                return result;
            case "grass": 
                result = new String[]{"water","ground","rock"};
                return result;
            case "ice": 
                result = new String[]{"grass","ground","flying","dragon"};
                return result;
            case "fighting": 
                result = new String[]{"normal","ice","rock","dark","steel"};
                return result;
            case "poison": 
                result = new String[]{"grass","fairy"};
                return result;
            case "ground": 
                result = new String[]{"fire","electric","poison","rock","steel"};
                return result;
            case "flying": 
                result = new String[]{"grass","fighting","bug"};
                return result;
            case "psychic": 
                result = new String[]{"fighting","poison"};
                return result;
            case "bug": 
                result = new String[]{"grass","psychic","dark"};
                return result;
            case "rock": 
                result = new String[]{"fire","ice","flying","bug"};
                return result;
            case "ghost": 
                result = new String[]{"psychic","ghost"};
                return result;
            case "dragon": 
                result = new String[]{"dragon"};
                return result;
            case "dark": 
                result = new String[]{"psychic","rock"};
                return result;
            case "steel": 
                result = new String[]{"ice","fairy","rock"};
                return result;
            case "fairy": 
                result = new String[]{"fighting","dragon","dark"};
                return result;                 
        }
        
        // If the type does not match any case, error displays and program ends.
        System.out.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES"); 
        System.exit(0); 
        return result;        
    }
    
    /**
     * Takes in a move, and then determines what types this move
     * is not very effective against.
     * @param m Move being used.
     * @return String array containing all resistant types.
     */
    public String[] getNegEffects()
    {
        String[] result = new String[]{""};
        
        // Check type of move
        switch (type){
            case "normal": 
                result = new String[]{"ground","rock","steel"};
                return result;
            case "fire": 
                result = new String[]{"fire","water","rock","dragon"};
                return result;
            case "water": 
                result = new String[]{"water","grass","dragon"};
                return result;
            case "electric": 
                result = new String[]{"ground","electric","grass","dragon"};
                return result;
            case "grass": 
                result = new String[]{"fire","grass","poison","bug","dragon","steel"};
                return result;
            case "ice": 
                result = new String[]{"fire","water","ice","steel"};
                return result;
            case "fighting": 
                result = new String[]{"ghost","poison","flying","psychic","bug","fairy",};
                return result;
            case "poison": 
                result = new String[]{"steel","ground","rock","ghost"};
                return result;
            case "ground": 
                result = new String[]{"fly","grass","bug"};
                return result;
            case "flying": 
                result = new String[]{"electric","rock","steel"};
                return result;
            case "psychic": 
                result = new String[]{"dark","psychic","steel"};
                return result;
            case "bug": 
                result = new String[]{"fire","fighting","poison","flying","ghost","steel","fairy"};
                return result;
            case "rock": 
                result = new String[]{"fighting","ground","steel"};
                return result;
            case "ghost": 
                result = new String[]{"normal","dark"};
                return result;
            case "dragon": 
                result = new String[]{"fairy","steel"};
                return result;
            case "dark": 
                result = new String[]{"fighting","dark","fairy"};
                return result;
            case "steel": 
                result = new String[]{"fire","water","electric","steel"};
                return result;
            case "fairy": 
                result = new String[]{"fire","poison","steel"};
                return result;                 
        }
        
        // If the type does not match any case, error displays and program ends.
        System.out.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES");
        System.exit(0);
        return result;   
    }
    
     /**
     * Takes in a move, and then determines what type this move
     * is not at all effective against.
     * @param m Move being used.
     * @return String containing the resistant type (if any exists).
     */
    public String getZeroEffects()
    {
        String result = "empty";
        
        // Check type of move
        switch (type){
            case "normal": 
                result = "ghost";
                return result;
            case "electric": 
                result = "ground";
                return result;
            case "fighting": 
                result = "ghost";
                return result;
            case "poison": 
                result = "steel";
                return result;
            case "ground": 
                result = "fly";
                return result;
            case "psychic": 
                result = "dark";
                return result;
            case "ghost": 
                result = "normal";      
                return result;
        }
  
        return result;   
    }

    /**
     * Apply's an ATTACK move's specific effect to the battle
     * TODO: Account for more moves.
     * @param attacker Pokemon using the move
     * @param enemy Pokemon being targeted
     * @param damage damage being done
     */
    public void applyEffect(Trainer trainer, Trainer enemy,int damage) {
    	
    	double random = Math.random();
    	Pokemon attacker = trainer.getActivePokemon();
    	Pokemon target = enemy.getActivePokemon();
    	String typeOne = target.getType()[0];
    	String typeTwo = target.getType()[1];
    	String targStatus = target.getStatus();
    	
    	switch(type) {
    		case "fire":
    			// Applys recoil damage to the user and a chance of burning
    			if (name.equals("Flare Blitz")) {
    				attacker.setHealth( attacker.getHealth() - (int) Math.round( (1.0/3.0) * damage));
    				effectMessage = attacker.getName() + " took recoil!";
    				if (random < .1) {
    					// Fire cannot be burned
    					if (!typeOne.equals("fire") && !typeTwo.equals("fire") && !targStatus.equals("burn")) {
    						target.setStatus("burn");
    						effectMessage += "\n" + target.getName() + " has been burned!";
    					}
    				}
    			}
    			// Has a chance to burn the target
    			if (name.equals("Fire Blast")) {
    				if (random < .1) {
    					// Fire cannot be burned
    					if (!typeOne.equals("fire") && !typeTwo.equals("fire") && !targStatus.equals("burn")) {
    						target.setStatus("burn");
    						effectMessage += "\n" + target.getName() + " has been burned!";
    					}
    				}
    				return;
    			}
    		break;
    		case "fighting":
    			// Lowers user's defense and spDefense after attacking
    			if (name.equals("Close Combat")) {
    				attacker.incrementStage("Defense","-");
    				attacker.incrementStage("SpDefense", "-");
    				// TODO: Display message for when stat stages are maxed lower
    				effectMessage = attacker.getName() + "'s Defense and Special Defense have decreased!";
    				return;
    			}
    			// May lower target's spDefense
    			if (name.equals("Focus Blast")) {
    				if (random < .1) {
    					target.incrementStage("spDefense", "-");
    					effectMessage = target.getName() + "'s Special Defense has decreased!";
    				}
    				return;
    			}
    		break;
    		case "bug":
    			// Allows a user to swap Pokemon after attacking
    			if (name.equals("U-turn")) {
    				effectMessage = attacker.getName() + " went back!"; // add Trainer Names
    				System.out.println();
    				attacker.setActiveMove(null);
    				trainer.selectPokemon();
    				effectMessage = effectMessage + "\n" + trainer.getActivePokemon().getName() + " has entered the battle!";
    				return;
    			}
    		break;
    		case "electric":
    			// Allows a user to swap Pokemon after attacking
    			// TODO: May Paralyze
    			if (name.equals("Volt Switch")) {
    				effectMessage = attacker.getName() + " went back!"; // TODO: add Trainer Names
    				System.out.println();
    				trainer.selectPokemon();
    				effectMessage = effectMessage + "\n" + trainer.getActivePokemon().getName() + " has entered the battle!";
    				return;
    			}
    			// 10% Chance to paralyze
    			if (name.equals("Thunder Punch")) {
    				// Electric and ground can't be paralyzed
    				if (random < .1 && !typeOne.equals("electric") && !typeTwo.equals("electric") 
    						&& !typeOne.equals("ground") && !typeTwo.equals("ground") && !targStatus.equals("paralysis")) {
    					target.setStatus("paralysis");
    					effectMessage = target.getName() + " was paralyzed!";
    				}
    				return;
    			}
    			// 10% Chance to paralyze
    			if (name.equals("Thunderbolt")) {
    				// Electric and ground can't be paralyzed
    				if (random < .1 && !typeOne.equals("electric") && !typeTwo.equals("electric") 
    						&& !typeOne.equals("ground") && !typeTwo.equals("ground") && !targStatus.equals("paralysis")) {
    					target.setStatus("paralysis");
    					effectMessage = target.getName() + " was paralyzed!";
    				}
    				return;
    			}
    		break;
    		case "ghost":
    			// 20% May lower target's spDefense
    			if (name.equals("Shadow Ball")) {
    				if (random < .2) {
    					target.incrementStage("spDefense", "-");
    					effectMessage = target.getName() + "'s Special Defense has decreased!";
    				}
    				return;
    			}
    			
    		break;
    		case "ground":
    			// 10% May lower target's spDefense
    			if (name.equals("Earth Power")) {
    				if (random < .1) {
    					target.incrementStage("spDefense", "-");
    					effectMessage = target.getName() + "'s Special Defense has decreased!";
    				}
    				return;
    			}
    		break;
    		case "dark": 
    			// 20% May lower target's defense
    			if (name.equals("Crunch")) {
    				if (random < .2) {
    					if (target.getStage("Defense") == -6) {
    						effectMessage = target.getName() + "'s Defense can't go any lower!";
    					}
    					else {
    					target.incrementStage("Defense", "-");
    						effectMessage = target.getName() + "'s Defense has fallen!";
    					}
    				}
    				return;
    			}
    		break;
    		case "ice":
    			// 10% May freeze
    			if (name.equals("Ice Beam")) {
    				// Ice and Fire cannot be frozen
    				if (random < .1 && !typeOne.equals("fire") && !typeTwo.equals("fire")
    						&& !typeOne.equals("ice") && !typeTwo.equals("ice") && !targStatus.equals("frozen")) {
    					effectMessage = target.getName() + " was frozen!";
    					target.setStatus("frozen");
    				}
    				return;
    			}
    		break;
    		case "poison":
    			// 10% Chance of poisoning
    			if (name.equals("Sludge Wave")) {
    				// Poison and Steel cannot be poisoned
    				if (random < .1 && !typeOne.equals("poison") && !typeTwo.equals("poison") 
    						&& !typeOne.equals("steel") && !typeTwo.equals("steel") && !targStatus.equals("poison")) {
    					target.setStatus("poison");
    					effectMessage = target.getName() + " was poisoned!";
    				}
    				return;
    			}
    		break;
    		case "normal":
    			// 30% Chance to paralyze
    			if (name.equals("Body Slam")) {
    				// Electric and ground can't be paralyzed
    				if (random < .3 && !typeOne.equals("electric") && !typeTwo.equals("electric") && !typeOne.equals("ground") 
    						&& !typeTwo.equals("ground") && !targStatus.equals("paralysis")) {
    					target.setStatus("paralysis");
    					effectMessage = target.getName() + " was paralyzed!";
    				}
    				return;
    			}
    			
    	}
    }
    
    public void useEffect(Trainer trainer, Trainer enemy) {
    	
    	Pokemon attacker = trainer.getActivePokemon();
    	Pokemon target = enemy.getActivePokemon();
    	double random = Math.random();
    	String typeOne = target.getType()[0];
    	String typeTwo = target.getType()[1];
    	String targStatus;
    	
    	if (target.getStatus() != null){
    		targStatus = target.getStatus();
    	}
    	else targStatus = ""; // Prevent null pointer
    	
    	switch(type) {
    		case "normal":
    			// Increases user's attack stats by 2 stages
    			if (name.equals("Swords Dance")) {
    				System.out.println(attacker.getName() + " used Swords Dance!");
    				if (attacker.getStage("Attack") == 6 && attacker.getStage("spAttack") == 6 ) {
    					System.out.println(attacker.getName() + "'s Attack and Special Attack can't go any higher!");
    				}
    				else if (attacker.getStage("Attack") == 6) {
    					attacker.incrementStage("SpAttack", "+");
    					attacker.incrementStage("SpAttack", "+");
    					System.out.println(attacker.getName() + "'s Special Attack rose sharply!");
    					System.out.println(attacker.getName() + "'s Attack can't go any higher!");
    					System.out.println();
    				}
    				else if (attacker.getStage("SpAttack") == 6) {
    					attacker.incrementStage("Attack", "+");
    					attacker.incrementStage("Attack", "+");
    					System.out.println(attacker.getName() + "'s Attack rose sharply!");
    					System.out.println(attacker.getName() + "'s Special Attack can't go any higher!");
    				}
    				else {
    					attacker.incrementStage("Attack","+");
    					attacker.incrementStage("SpAttack", "+");
    					attacker.incrementStage("Attack","+");
    					attacker.incrementStage("SpAttack", "+");		
    					System.out.println(attacker.getName() + "'s Attack and Special Attack have rose sharply!");
    				}
    				System.out.println();
    				return;
    			}
    			// Heals the user
    			if (name.equals("Recover")) {
    				System.out.println(attacker.getName() + " used Recover!");
    				if (attacker.getHealth() != attacker.getMaxHealth()) {
    					System.out.println(attacker.getName() + " restored some HP!");
    					attacker.setHealth(attacker.getHealth() + (int) Math.round(attacker.getMaxHealth()/2));
    				}
    				else System.out.println(attacker.getName() + "'s health can't go any higher!");
    				System.out.println();
    				return;
    			}
    		break;
    		case "fly":
    			// Heals the user
    			if (name.equals("Roost")) {
    				System.out.println(attacker.getName() + " used Roost!");
    				if (attacker.getHealth() != attacker.getMaxHealth()) {
    					System.out.println(attacker.getName() + " restored some HP!");
    					attacker.setHealth(attacker.getHealth() + (int) Math.round(attacker.getMaxHealth()/2));
    				}
    				else System.out.println(attacker.getName() + "'s health can't go any higher!");
    				System.out.println();
    				return;
    			}
    		break;
    		case "psychic":
    			// Heals user to max and puts them to sleep
    			if (name.equals("Rest")) {
    				
    				System.out.println(attacker.getName() + " used Rest!");
					System.out.println(attacker.getName() + " fell asleep!");
    				
    				if (attacker.getStatus() != null) { // If there is a pre-existing status effect
    					System.out.println(attacker.getName() + " is cured!");
    					attacker.setStatus(null);
    				}	
    				attacker.setStatus("sleep"); // Health is already max
    				if (attacker.getHealth() == attacker.getMaxHealth()) {
    					System.out.println(attacker.getName() + "'s health can't go any higher!");
    					System.out.println();
    				}
    				else {
    					attacker.setHealth(attacker.getMaxHealth());
    					System.out.println(attacker.getName() + "'s health is restored to max");	
    					System.out.println();
    				}
    				return;
    			}
    		break;
    		case "electric":
    			// Paralyzes opponent
    			if (name.equals("Thunder Wave")) {
    				System.out.println(attacker.getName() + " used Thunder Wave!");
    				if (random < .9) { // 90% chance
    					// Doesn't work on ground and electric types
    					if ( typeOne.equals("ground") || typeTwo.equals("ground") || 
    							typeOne.equals("electric") || typeTwo.equals("electric")) {
    						System.out.println("But it didn't work!");
    						System.out.println();
    						return;
    					}
    					if (targStatus.equals("paralysis")) {
							System.out.println(target.getName() + " is already paralyzed!");
    						System.out.println();
						}
    					else {
    						target.setStatus("paralysis");
    						System.out.println(target.getName() + " was paralyzed!");
    						System.out.println();
    					}
    					
    				}
    				else {
    					System.out.println("But it missed!");
    					System.out.println();
    				}
    				return;
    			}
    		break;
    		case "poison":
    			// Badly poisons target
    			if (name.equals("Toxic")) {
    				System.out.println(attacker.getName() + " used Toxic!");
    				if (random < .85) {
        				// Poison and Steel cannot be poisoned
    					if (!typeOne.equals("poison") && !typeTwo.equals("poison") 
    							&& !typeOne.equals("steel") && !typeTwo.equals("steel")) {
    						if (targStatus.equals("badPoison")) {
    							System.out.println(target.getName() + " is already poisoned!");
        						System.out.println();
    						}
    						else {
    							System.out.println(target.getName() + " was badly poisoned!");
    							System.out.println();
    							target.setStatus("badPoison");
    						}
    					}
    					else {
    						System.out.println("But it didn't work!");
    						System.out.println();
    					}
    				}
    				else {
    					System.out.println("But it missed!");
    					System.out.println();
    				}
    				return;
    			}
    		break;
    		case "fire":
    			// Badly poisons target
    			if (name.equals("Will-O-Wisp")) {
    				System.out.println(attacker.getName() + " used Will-O-Wisp!");
    				if (random < .85) {
        				// Fire cannot be burned
    					if (!typeOne.equals("fire") && !typeTwo.equals("fire")) {
    						if (targStatus.equals("burn")) {
    							System.out.println(target.getName() + " is already burned!");
        						System.out.println();
    						}
    						else {
    							System.out.println(target.getName() + " was burned!");
    							System.out.println();
    							target.setStatus("burn");
    						}
    					}
    					else {
    						System.out.println("But it didn't work!");
    						System.out.println();
    					}
    				}
    				else {
    					System.out.println("But it missed!");
    					System.out.println();
    				}
    				return; 		
    			}
    		break;
    	}
    }
}


