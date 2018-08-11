package pokemonbattle;

/** 
 * Class to create, initialize, and store pokemon moves with their
 * respective varibles. 
 * and Close Combat reduces the user's stats.
 * @version 5.0
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
      
    public Move(String n, String a, int d, double h, int pp, int pri, boolean ph, boolean sp, boolean eff){
        name = n;
        type = a;
        damage = d;
        accuracy = h;
        powerPoints = pp;
        maxPowerPoints = pp;
        priority = pri;
        physical = ph;
        special = sp;
        effect= eff;
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
    	double randy = Math.random();
    	if (randy <= accuracy/100){
           return true;
    	} 
    	else return false;
    }
    
    /**
     * Calculates if a move is a critical hit or not. 
     * @return true if critical.
     */
    public boolean isCritical() {
    	
    	double randy = Math.random();
    	if(randy <= .0625) {
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
     * Apply's a move's specific effect to the battle
     * TODO: Account for more moves.
     * @param attacker Pokemon using the move
     * @param enemy Pokemon being targeted
     * @param damage damage being done
     */
    public void applyEffect(Pokemon attacker, Pokemon defender, int damage) {
    	
    	double randy = Math.random();
    	
    	switch(type) {
    		case "fire":
    			if (name.equals("Flare Blitz")) {
    				attacker.setHealth( attacker.getHealth() - (int) Math.round( (1.0/3.0) * damage));
    				effectMessage = attacker.getName() + " took recoil!";
    				if (randy <= .1) {
    					// TODO: Status effects - defender.setStatus("burn");	
    					effectMessage += "\n" + defender.getName() + " has been burned!";
    				}
    			}
    			if (name.equals("Fire Blast")) {
    				if (randy <= .1) {
    					// defender.setStatus("burn");
    					effectMessage += "\n" + defender.getName() + " has been burned!";
    				}
    			}
    		break;
    		case "fighting":
    			if (name.equals("Close Combat")) {
    				attacker.incrementStage("Defense","-");
    				attacker.incrementStage("SpDefense", "-");
    				// TODO: Display message for when stat stages are maxed lower or mased higher
    				effectMessage = attacker.getName() + "'s Defense and Special Defense have decreased!";
    			}
    	}
    		
    }
}


