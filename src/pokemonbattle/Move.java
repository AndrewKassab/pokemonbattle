package pokemonbattle;

/** 
 * Class to create, initialize, and store pokemon moves with their
 * respective varibles. 
 * TODO: Fix U-Turn and Volt-Switch Bugs
 * TODO: Make type into its own object
 * @version 7.3
 * @author Andrew Kassab
 */
public class Move
{
  private final String name;
  private final Type type;
  private int damage;
  private double accuracy;
  private int powerPoints;
  private int maxPowerPoints;
  private int priority; // label for speed priority
  private boolean physical; // physical label
  private boolean special; // special label
  private boolean effect; // if a move has an effect
  private boolean wasCritical; // if a move was a critical hit
  private String effectMessage;
    
  public Move(String name, Type type, int damage, double accuracy, int pp, int priority, 
                boolean isPhysical, boolean isSpecial, boolean hasEffect){
    this.name = name;
    this.type = type;
    this.damage = damage;
    this.accuracy = accuracy;
    this.powerPoints = pp;
    this.maxPowerPoints = pp;
    this.priority = priority;
    this.physical = isPhysical;
    this.special = isSpecial;
    this.effect = hasEffect;
  }
  
  public Type getType(){
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
      wasCritical = true;
      return true;
    }
    else {
      wasCritical = false;
      return false;
    }
  }
  
  /**
   * Checks if a move was a critical hit in its attack turn.
   * For message display purposes. 
   * @return true if the move was critical
   */
  public boolean wasCritical() {
    if (wasCritical) {
      wasCritical = false;
      return true;
    }
    else return false;
  }
  
  /**
   * Apply's an ATTACK move's specific effect to the battle
   * TODO: Account for more moves.
   * @param attacker Pokemon using the move
   * @param enemy Pokemon being targeted
   * @param damage damage being done
   */
  public void applyEffect(Trainer trainer, Trainer enemy, int damage) {
    
    double random = Math.random();
    Pokemon attacker = trainer.getActivePokemon();
    Pokemon target = enemy.getActivePokemon();
    Type typeOne = target.getType()[0];
    Type typeTwo = target.getType()[1];
    Status targStatus = target.getLethalStatus();
    
    switch(type) {
    case FIRE:
       // Applys recoil damage to the user and a chance of burning
      if (name.equals("Flare Blitz")) {
        attacker.setHealth( attacker.getHealth() - (int) Math.round( (1.0/3.0) * damage));
        effectMessage = attacker.getName() + " took recoil!";
        if (random < .1) {
          // Fire cannot be burned
          if ( ( typeOne != Type.FIRE ) && ( typeTwo != Type.FIRE ) && 
                ( targStatus != Status.BURN ) );
          {
            target.setStatus(Status.BURN, StatusType.LETHAL);
            effectMessage += "\n" + target.getName() + " has been burned!";
          }
        }
      }
      // Has a chance to burn the target
      if (name.equals("Fire Blast")) {
        if (random < .1) {
          // Fire cannot be burned
          if ( ( typeOne != Type.FIRE ) && ( typeTwo != Type.FIRE ) && ( targStatus != Status.BURN )){
            target.setStatus(Status.BURN, StatusType.LETHAL);
            effectMessage += "\n" + target.getName() + " has been burned!";
          }
        }
        return;
      }
      break;
    case FIGHTING:
      // Lowers user's defense and spDefense after attacking
      if (name.equals("Close Combat")) {
        attacker.incrementStage(Stat.DEFENSE , Operator.DECREMENT);
        attacker.incrementStage(Stat.SPDEFENSE, Operator.DECREMENT);
        // TODO: Display message for when stat stages are maxed lower
        effectMessage = attacker.getName() + "'s Defense and Special Defense have decreased!";
        return;
      }
      // May lower target's spDefense
      if (name.equals("Focus Blast")) {
        if (random < .1) {
          target.incrementStage(Stat.SPDEFENSE, Operator.DECREMENT);
          effectMessage = target.getName() + "'s Special Defense has decreased!";
        }
        return;
      }
      break;
    case BUG:
      // Allows a user to swap Pokemon after attacking
      if (name.equals("U-turn")) {
        effectMessage = attacker.getName() + " went back!"; // add Trainer Names
        System.out.println();
        attacker.setActiveMove(null);
        trainer.selectPokemon();
        effectMessage = effectMessage + "\n" + trainer.getActivePokemon().getName() +
                  " has entered the battle!";
        return;
      }
      break;
    case ELECTRIC:
      // Allows a user to swap Pokemon after attacking
      // TODO: May Paralyze
      if (name.equals("Volt Switch")) {
        effectMessage = attacker.getName() + " went back!"; // TODO: add Trainer Names
        System.out.println();
        trainer.selectPokemon();
        effectMessage = effectMessage + "\n" + trainer.getActivePokemon().getName() +
                  " has entered the battle!";
        return;
      }
      // 10% Chance to paralyze
      if (name.equals("Thunder Punch")) {
        // Electric and ground can't be paralyzed
        if ( (random < .1) && ( typeOne != Type.ELECTRIC ) && ( typeTwo != Type.ELECTRIC )
              && ( typeOne != Type.GROUND ) && ( typeTwo != Type.GROUND )
                && ( targStatus != Status.PARALYSIS) ) 
        {
          target.setStatus(Status.PARALYSIS, StatusType.LETHAL);
          effectMessage = target.getName() + " was paralyzed!";
        }
        return;
      }
      // 10% Chance to paralyze
      if (name.equals("Thunderbolt")) {
        // Electric and ground can't be paralyzed
        if ( (random < .1) && ( typeOne != Type.ELECTRIC ) && ( typeTwo != Type.ELECTRIC )
              && ( typeOne != Type.GROUND ) && ( typeTwo != Type.GROUND )
                && !targStatus.equals("paralysis")) 
        {
          target.setStatus(Status.PARALYSIS, StatusType.LETHAL);
          effectMessage = target.getName() + " was paralyzed!";
        }
        return;
      }
      break;
    case GHOST:
      // 20% May lower target's spDefense
      if (name.equals("Shadow Ball")) {
        if (random < .2) {
          target.incrementStage(Stat.SPDEFENSE,Operator.DECREMENT); // TODO: Left off here (3/25)
          effectMessage = target.getName() + "'s Special Defense has decreased!";
        }
        return;
      }
      break;
    case GROUND:
      // 10% May lower target's spDefense
      if (name.equals("Earth Power")) {
        if (random < .1) {
          target.incrementStage(Stat.SPDEFENSE, Operator.DECREMENT);
          effectMessage = target.getName() + "'s Special Defense has decreased!";
        }
        return;
      }
      break;
    case DARK:
      // 20% May lower target's defense
      if (name.equals("Crunch")) {
        if (random < .2) {
          if (target.getStage(Stat.DEFENSE) == -6) {
            effectMessage = target.getName() + "'s Defense can't go any lower!";
          }
          else {
          target.incrementStage(Stat.DEFENSE, Operator.DECREMENT);
            effectMessage = target.getName() + "'s Defense has fallen!";
          }
        }
        return;
      }
      break;
    case ICE:
      // 10% May freeze
      if (name.equals("Ice Beam")) {
        // Ice and Fire cannot be frozen
        if ( (random < .1) && ( typeOne != Type.FIRE ) && ( typeTwo != Type.FIRE )
              && ( typeOne != Type.ICE ) && ( typeTwo != Type.ICE )
                && ( targStatus != Status.FROZEN ) )
        {
          effectMessage = target.getName() + " was frozen!";
          target.setStatus(Status.FROZEN, StatusType.LETHAL);
        }
        return;
      }
      break;
    case POISON:
      // 10% Chance of poisoning
      if (name.equals("Sludge Wave")) {
        // Poison and Steel cannot be poisoned
        if ( (random < .1) && ( typeOne != Type.POISON ) && ( typeTwo != Type.POISON )
              && ( typeOne != Type.STEEL ) && ( typeTwo != Type.STEEL )
                && ( targStatus != Status.POISON ) )
        {
            target.setStatus(Status.POISON,StatusType.LETHAL);
            effectMessage = target.getName() + " was poisoned!";
        }
        return;
      }
      break;
    case NORMAL:
      // 30% Chance to paralyze
      if (name.equals("Body Slam")) {
        // Electric and ground can't be paralyzed
        if ( (random < .1) && ( typeOne != Type.ELECTRIC ) && ( typeTwo != Type.ELECTRIC )
              && ( typeOne != Type.GROUND ) && ( typeTwo != Type.GROUND )
                && ( targStatus != Status.PARALYSIS ) )
        {
            target.setStatus( Status.PARALYSIS, StatusType.LETHAL );
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
    Type typeOne = target.getType()[0];
    Type typeTwo = target.getType()[1];
    Status targStatus;
    Status targNonLethalStatus;
    
    targStatus = target.getLethalStatus();

    targNonLethalStatus = target.getnonLethalStatus();
    
    switch(type) {
    case NORMAL:
      // Increases user's attack stats by 2 stages
      if (name.equals("Swords Dance")) {
        System.out.println(attacker.getName() + " used Swords Dance!");
        if (attacker.getStage(Stat.ATTACK) == 6 && attacker.getStage(Stat.SPATTACK) == 6 ) {
          System.out.println(attacker.getName() + 
                              "'s Attack and Special Attack can't go any higher!");
        }
        else if (attacker.getStage(Stat.ATTACK) == 6) {
          attacker.incrementStage(Stat.SPATTACK, Operator.INCREMENT);
          attacker.incrementStage(Stat.SPATTACK, Operator.INCREMENT);
          System.out.println(attacker.getName() + "'s Special Attack rose sharply!");
          System.out.println(attacker.getName() + "'s Attack can't go any higher!");
          System.out.println();
        }
        else if (attacker.getStage(Stat.SPATTACK) == 6) {
          attacker.incrementStage(Stat.ATTACK, Operator.INCREMENT);
          attacker.incrementStage(Stat.ATTACK, Operator.INCREMENT);
          System.out.println(attacker.getName() + "'s Attack rose sharply!");
          System.out.println(attacker.getName() + 
                              "'s Special Attack can't go any higher!");
        }
        else {
          attacker.incrementStage(Stat.ATTACK,Operator.INCREMENT);
          attacker.incrementStage(Stat.SPATTACK, Operator.INCREMENT);
          attacker.incrementStage(Stat.ATTACK,Operator.INCREMENT);
          attacker.incrementStage(Stat.SPATTACK, Operator.INCREMENT);
          System.out.println(attacker.getName() + 
                              "'s Attack and Special Attack have rose sharply!");
        }
        System.out.println();
        return;
      }
      // Heals the user
      if (name.equals("Recover")) {
        System.out.println(attacker.getName() + " used Recover!");
        if (attacker.getHealth() != attacker.getMaxHealth()) {
          System.out.println(attacker.getName() + " restored some HP!");
          attacker.setHealth(attacker.getHealth() + 
                              (int) Math.round(attacker.getMaxHealth()/2));
        }
        else System.out.println(attacker.getName() + "'s health can't go any higher!");
        System.out.println();
        return;
      }
      break;
    case FLYING:
      // Heals the user
      if (name.equals("Roost")) {
        System.out.println(attacker.getName() + " used Roost!");
        if (attacker.getHealth() != attacker.getMaxHealth()) {
          System.out.println(attacker.getName() + " restored some HP!");
          attacker.setHealth(attacker.getHealth() + 
                              (int) Math.round(attacker.getMaxHealth()/2));
        }
        else System.out.println(attacker.getName() + "'s health can't go any higher!");
        System.out.println();
        return;
      }
      break;
    case PSYCHIC:
      // Heals user to max and puts them to sleep
      if (name.equals("Rest")) {
        
        System.out.println(attacker.getName() + " used Rest!");
        System.out.println(attacker.getName() + " fell asleep!");
        
        if (attacker.getLethalStatus() != Status.NULLSTATUS) { 
          System.out.println(attacker.getName() + " is cured!");
          attacker.setStatus(Status.NULLSTATUS, StatusType.LETHAL);
        }  
        attacker.setStatus(Status.SLEEP, StatusType.LETHAL);
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
    case ELECTRIC:
      // Paralyzes opponent
      if (name.equals("Thunder Wave")) {
        System.out.println(attacker.getName() + " used Thunder Wave!");
        if (random < .9) { // 90% chance
          // Doesn't work on ground and electric types
          if ( typeOne.equals("ground") || typeTwo.equals("ground") || 
                typeOne.equals("electric") || typeTwo.equals("electric")) 
          {
              System.out.println("But it didn't work!");
            System.out.println();
            return;
          }
          if (targStatus == Status.PARALYSIS) {
            System.out.println(target.getName() + " is already paralyzed!");
            System.out.println();
          } else {
            target.setStatus(Status.PARALYSIS, StatusType.LETHAL);
            System.out.println(target.getName() + " was paralyzed!");
            System.out.println();
          }
        } else {
          System.out.println("But it missed!");
          System.out.println();
        }
        return;
    }
      break;
    case POISON:
      // Badly poisons target
      if (name.equals("Toxic")) {
        System.out.println(attacker.getName() + " used Toxic!");
        if (random < .85) {
          // Poison and Steel cannot be poisoned
          if ( ( typeOne != Type.POISON ) && ( typeTwo != Type.POISON )
                && ( typeOne != Type.STEEL ) && ( typeTwo != Type.STEEL ) ) 
          {
            if (targStatus == Status.BADPOISON) {
              System.out.println(target.getName() + " is already poisoned!");
                System.out.println();
            }
            else {
              System.out.println(target.getName() + " was badly poisoned!");
              System.out.println();
              target.setStatus(Status.BADPOISON, StatusType.LETHAL);
            }
          } else {
            System.out.println("But it didn't work!");
            System.out.println();
          }
        } else {
          System.out.println("But it missed!");
          System.out.println();
        }
        return;
      }
    break;
    case FIRE:
      // Badly poisons target
      if (name.equals("Will-O-Wisp")) {
        System.out.println(attacker.getName() + " used Will-O-Wisp!");
        if (random < .85) {
          // Fire cannot be burned
          if ( ( typeOne != Type.FIRE ) && ( typeTwo != Type.FIRE ) ){
            if (targStatus == Status.BURN){
              System.out.println(target.getName() + " is already burned!");
              System.out.println();
            } else {
              System.out.println(target.getName() + " was burned!");
              System.out.println();
              target.setStatus(Status.BURN,StatusType.LETHAL);
            }
          } else {
            System.out.println("But it didn't work!");
            System.out.println();
          }
        } else {
          System.out.println("But it missed!");
          System.out.println();
        }
        return;     
    }
      break;
    case GHOST:
      // confuses target
      if (name.equals("Confuse-Ray")){
        System.out.println(attacker.getName() + " used Confuse-Ray!");
        if ( targNonLethalStatus == Status.CONFUSION ) {
          System.out.println(target.getName() + " is already confused!");
        }
        else {
            System.out.println(target.getName() + " became confused!\n");
            target.setStatus(Status.CONFUSION, StatusType.NONLETHAL);
        }
        return;
      }
      break;
    }
  }
}