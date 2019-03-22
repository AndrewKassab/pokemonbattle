package pokemonbattle;

/**
 * This class contains the methods and objects to handle 
 * a battle. There will be two trainers in the battle and the 
 * user will take control of both trainer's partys / pokemon. 
 * TODO: BUG: Dark pulse did not do anything to Infernape?? 
 * TODO: BUG: Infernape at 0 health and didn't faint but match ended?
 * @author Andrew Kassab
 */
public class Battle 
{   
  // FILLER FOR TESTING
  private Move ultimate = new Move("InstantKO",Type.FIRE,10000,100,99,5,true,true,false); 

  /* FIRE */

  // May burn
  private Move fireBlast = new Move("Fire Blast",Type.FIRE,110,85,5,0,false,true,true); 
  // Has Recoil.
  private Move flareBlitz = new Move("Flare Blitz",Type.FIRE,120,100,15,0,true,false,true); 
  // Burns Enemy
  private Move will_O_Wisp = new Move("Will-O-Wisp",Type.FIRE,0,85,15,0,false,true,true); 
  
  /* WATER */

  private Move hydroPump = new Move("Hydro Pump",Type.WATER,110,80,5,0,false,true,false);  
  // priority +1
  private Move aquaJet = new Move("Aqua Jet",Type.WATER,40,100,20,1,true,false,false); 

  /* GRASS */

  // TODO: Charges for 1 turn
  private Move solarBeam = new Move("Solar Beam", Type.GRASS, 120,100,10,0,false,true,false); 
  private Move leafBlade = new Move("Leaf Blade", Type.GRASS, 90,100,15,0,true,false,false);
    
  /* FIGHTING */

  // Lowers user's attack
  private Move closeCombat = new Move("Close Combat",Type.FIGHTING,120,100,5,0,true,false,true); 
  // May lower SpDef
  private Move focusBlast = new Move("Focus Blast",Type.FIGHTING,120,70,5,0,false,true,true); 
   
  /* ELECTRIC */

  // May Paralyze
  private Move thunderPunch = new Move("Thunder Punch",Type.ELECTRIC,75,100,15,0,true,false,false);
  // May Paralyze
  private Move thunderbolt = new Move("Thunderbolt",Type.ELECTRIC,90,100,15,0,false,true,false); 
  // Allows user to switch Pokemon
  private Move voltSwitch = new Move("Volt Switch",Type.ELECTRIC,70,100,20,0,true,false,true); 
  // Paralyzes opponent
  private Move thunderWave = new Move("Thunder Wave",Type.ELECTRIC,0,90,20,0,false,true,true);

  /* GHOST */

  // May lower spDefense
  private Move shadowBall = new Move("Shadow Ball",Type.GHOST,80,100,15,0,false,true,true);
  // Confuses target
  private Move confuseRay = new Move("Confuse-Ray", Type.GHOST,0,100,10,0,false,false,true); 
  
  /* BUG */
  
  // priority +1
  private Move bulletPunch = new Move("Bullet Punch",Type.BUG,40,100,30,1,true,false,false); 
  // Allows user to switch Pokemon
  private Move uTurn = new Move("U-turn",Type.BUG,70,100,20,0,true,false,true); 
   
  /* DARK */

  // TODO: May cause Flinch.
  private Move darkPulse = new Move("Dark Pulse", Type.DARK,80,100,15,0,false,true,false); 
  // May lower defense
  private Move crunch = new Move("Crunch",Type.DARK,80,100,15,0,true,false,true); 
    
  /* PSYCHIC */

  private Move zenHeadbutt = new Move("Zen Headbutt",Type.PSYCHIC,80,90,15,0,true,false,false);
  // Heals to max and puts pokemon to sleep
  private Move rest= new Move("Rest",Type.PSYCHIC,0,100,10,0,false,false,true); 
   
  /* ICE */

  // May Freeze
  private  Move iceBeam = new Move("Ice Beam",Type.ICE,90,100,10,0,false,true,true); 
    
  /* NORMAL */
  
  // May Paralyze
  private Move bodySlam = new Move("Body Slam",Type.NORMAL,85,100,15,0,true,false,true); 
  // Raises Attack / SpAttack stats
  private Move swordsDance = new Move("Swords Dance",Type.NORMAL,0,100,20,0,false,false,true); 
  // Heals user by half of max HP
  private Move recover = new Move("Recover",Type.NORMAL,0,100,20,0,false,false,true); 
    
  /* GROUND */

  // TODO: Create independent instances for the same move for different pokemon.
  private Move earthquake = new Move("Earthquake",Type.GROUND,100,100,10,0,true,false,false); 
  // May lower spDef
  private Move earthPower = new Move("Earth Power",Type.GROUND,90,100,10,0,false,true,true); 
    
  /* STEEL */

  private Move meteorMash = new Move("Meteor Mash", Type.STEEL,90,90,10,0,true,false,false);
  
  /* POSION */

  // May poison
  private Move sludgeWave = new Move("Sludge Wave",Type.POISON,95,100,10,0,false,true,true); 
  private Move toxic = new Move("Toxic",Type.POISON,0,85,10,0,false,true,true); // Badly poisons enemy
    
  /* ROCK */

  private Move stoneEdge = new Move("Stone Edge",Type.ROCK,100,80,5,0,true,false,false);
    
  /* FLYING */

  // Heals the user half of max HP
  private Move roost = new Move("Roost",Type.FLYING,0,100,10,0,false,false,true); 
  
  /* MOVESET DEFINITIONS */
  private Move[] venuMoves = new Move[] {earthquake,solarBeam,ultimate,ultimate};
  private Move[] charMoves = new Move[] {flareBlitz,earthquake,roost,ultimate};
  private Move[] blasMoves = new Move[] {hydroPump,iceBeam,darkPulse,ultimate};
  private Move[] gengMoves = new Move[] {shadowBall,sludgeWave,focusBlast,toxic};
  private Move[] joltMoves = new Move[] {thunderbolt,shadowBall,voltSwitch,thunderWave};
  private Move[] snorMoves = new Move[] {bodySlam,earthquake,thunderPunch,rest};
  private Move[] scizMoves = new Move[] {bulletPunch,uTurn,swordsDance,roost};
  private Move[] tyranMoves = new Move[] {earthquake,stoneEdge,crunch,ultimate};
  private Move[] metaMoves = new Move[] {zenHeadbutt,earthquake,meteorMash,confuseRay};
  private Move[] inferMoves = new Move[] {flareBlitz,closeCombat,swordsDance,will_O_Wisp};
  private Move[] lucMoves = new Move[] {closeCombat,meteorMash,swordsDance,ultimate};
  private Move[] grenMoves = new Move[] {hydroPump,iceBeam,darkPulse,ultimate};
  
  /* POKEMON DEFINITIONS */
  private Pokemon venusaur = new Pokemon("Venusaur", 3 ,new Type[] {Type.GRASS,Type.NULLTYPE},
                                           364,289,291,328,328,284,venuMoves);

  private Pokemon charizard = new Pokemon("Charizard",6,new Type[]{Type.FIRE,Type.FLYING},
                                            360,348,293,295,295,328,charMoves); 

  private Pokemon blastoise = new Pokemon("Blastoise",9,new Type[] {Type.WATER,Type.NULLTYPE},
                                            362,291,328,295,339,280,blasMoves);

  private Pokemon gengar = new Pokemon("Gengar",94,new Type[] {Type.GHOST, Type.POISON},
                                         324,251,240,294,273,350,gengMoves);

  private Pokemon jolteon = new Pokemon("Jolteon",135,new Type[] {Type.ELECTRIC, Type.NULLTYPE},
                                          334,251,240,350,317,394,joltMoves);

  private Pokemon snorlax = new Pokemon("Snorlax",143,new Type[] {Type.NORMAL, Type.NULLTYPE},
                                          524,350,251,251,250,174,snorMoves);

  private Pokemon scizor = new Pokemon("Scizor",212,new Type[] {Type.GRASS, Type.NULLTYPE},
                                         344,394,328,229,284,251,scizMoves);

  private Pokemon tyranitar = new Pokemon("Tyranitar",248,new Type[]{Type.ROCK,Type.DARK},
                                            404,403,350,317,328,243,tyranMoves);

  private Pokemon metagross = new Pokemon("Metagross",376,new Type[] {Type.PSYCHIC,Type.STEEL},
                                            364,405,394,317,306,262,metaMoves);

  private Pokemon infernape = new Pokemon("Infernape",392,new Type[] {Type.FIRE,Type.FIGHTING},
                                            356,337,265,337,265,346,inferMoves);

  private Pokemon lucario = new Pokemon("Lucario",448,new Type[] {Type.FIGHTING,Type.STEEL},
                                          344,350,262,361,262,306,lucMoves);

  private Pokemon greninja = new Pokemon("Greninja",658,new Type[] {Type.WATER,Type.DARK},
                                           348,317,356,335,265,377,grenMoves);

  // Creating the parties for each trainer
  private Pokemon[] partyOne = new Pokemon[] {charizard,greninja,lucario,jolteon,venusaur,snorlax};
  private Pokemon[] partyTwo = new Pokemon[] {infernape,blastoise,metagross,gengar,scizor,tyranitar};
    
  // Trainer's and their party Pokemon
  private Trainer trainerOne = new Trainer(partyOne);
  private Trainer trainerTwo = new Trainer(partyTwo);
    
  public Battle() throws InterruptedException {
    battle();
  }
  
  /**
  * Checks to see which Pokemon will be attacking first in the battle. 
  * If neither move has a priority factor, then it is decided by
  * comparing the two Pokemon's speed stats.
  * @param pa Pokemon A
  * @param pb Pokemon B
  * @param ma Move used by Pokemon A
  * @param mb Move used by Pokemon B
  * @return value 1 or 2, 1 for Pokemon A going first, and 2 for Pokemon B
  */
  public static int whosFirst(Pokemon pa, Pokemon pb, Move ma, Move mb) {
    int pokemonOneSpeed = pa.getStat(Stat.SPEED);
    int pokemonTwoSpeed = pb.getStat(Stat.SPEED);
    
    // Paralysis lowers speed by 1/2
    if (pa.getLethalStatus() != null && pa.getLethalStatus().equals("paralysis")) {
      pokemonOneSpeed = (int) Math.round(pokemonOneSpeed/2.0);
    }
    if (pb.getLethalStatus() != null && pb.getLethalStatus().equals("paralysis")) {
      pokemonTwoSpeed = (int) Math.round(pokemonTwoSpeed/2.0);
    }
    
    if ( ma == null ) {
      return 1;
    }
    else if (mb == null ) {
      return 2;
    }
    else if ( ma.getPriority() > mb.getPriority()){
      return 1;
    }
    else if ( mb.getPriority() > ma.getPriority()){
      return 2;
    }
    else if (pokemonOneSpeed > pokemonTwoSpeed){
      return 1;
    }
    else if (pokemonTwoSpeed > pokemonOneSpeed){
      return 2;
    }
    
    // Randomize in the event of a matching speed and priority case.
    else {
      if (Math.random() >= 0.5){
        return 1;
      }
      else return 2;
    }     
  }
  
  /**
   * Displays current health status for both Pokemon in battle
   * @param a First Pokemon.
   * @param b Second Pokemon.
   */
  public void displayHealth(Pokemon a, Pokemon b){
    if (a.getHealth() < 0){
        a.setHealth(0);
    }
    if (b.getHealth() < 0){
        b.setHealth(0);
    }
    System.out.println(a.getName() + ": " + a.getHealth() + " health");
    System.out.println(b.getName() + ": " + b.getHealth() + " health\n");
  }      
  
  /**
   * Handles the instructions and loop for an actual Pokemon battle to commence.
   * @param args.
   * @throws InterruptedException Thread sleeps for user to process results
   */
  public void battle() throws InterruptedException {    
    // Select the active Pokemon for each trainer
    trainerOne.selectPokemon();
    trainerTwo.selectPokemon();
    
    // Battle loop
    do{
        
      trainerOne.getActivePokemon().selectMove(trainerOne);
      trainerTwo.getActivePokemon().selectMove(trainerTwo);

      // Decide attacking order
      int result = whosFirst(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon(),
                              trainerOne.getActivePokemon().getActiveMove(), 
                                trainerTwo.getActivePokemon().getActiveMove());  
     
      // Attack turns begin
      if (result == 1) { // If trainer one attacks first
        trainerOne.getActivePokemon().applyPreStatus(trainerOne); 
        trainerOne.getActivePokemon().applyNonLethal(trainerOne);
        if (trainerOne.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
          trainerOne.Attack(trainerTwo);
        } 
        Thread.sleep(3000);
        if (trainerTwo.canAttack()) {
          trainerTwo.getActivePokemon().applyPreStatus(trainerTwo);
          trainerTwo.getActivePokemon().applyNonLethal(trainerTwo);
          if (trainerTwo.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            trainerTwo.Attack(trainerOne);
          }     
        }
      } else { // If trainer two attacks first
        trainerTwo.getActivePokemon().applyPreStatus(trainerTwo); 
        trainerTwo.getActivePokemon().applyNonLethal(trainerTwo);
        if (trainerTwo.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
          trainerTwo.Attack(trainerOne);
        }
        Thread.sleep(3000);
        if (trainerOne.canAttack()) {
          trainerOne.getActivePokemon().applyPreStatus(trainerOne);
          trainerOne.getActivePokemon().applyNonLethal(trainerOne);
          if (trainerOne.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            trainerOne.Attack(trainerTwo);
          }
        }
      }
        
      trainerOne.getActivePokemon().applyPostStatus(trainerOne);
      trainerTwo.getActivePokemon().applyPostStatus(trainerTwo);

      trainerTwo.setCanAttack(true);
      trainerOne.setCanAttack(true);
      
      // Print out health status
      displayHealth(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon());
      
    } while (trainerOne.canContinue() && trainerTwo.canContinue());
    // Loop continues as long as both Pokemon are still alive
  }
  
  /**
   * Calculates the damage to be done in an attacking turn 
   * @param move The move being used
   * @param user Pokemon using the move
   * @param target Pokemon being targeted
   * @return The calculated damage value
   */
  public static int calculateDamage( Move move, Pokemon user, Pokemon target ) {
    
    int base = move.getDamage();
    double attack;
    double defense;
    
    if (move.isPhysical()) {
      attack = user.getStat(Stat.ATTACK);
      defense = target.getStat(Stat.DEFENSE);
    }
    else {
      attack = user.getStat(Stat.SPATTACK);
      defense = target.getStat(Stat.SPDEFENSE);               
    }

    // Calculate damage (before type effectiveness)
    int damage = (int) Math.round(((22 * base * (attack/defense))/50.0 + 2));    
    
    move.setPP(move.getPP() - 1);
    
    // Factor in STAB bonus
    if ( user.hasSTAB(move) ) {
      damage = (int) Math.round(damage * 1.5); 
    }
    
    damage = calculateEffectiveness( damage, move, target);
    
    if (move.isCritical()) {
      damage = damage * 2;
    }
    
    // Burn reduces physical damage by half
    if (user.getStatus() != null && user.getStatus().equals("burn") && move.isPhysical()) {
      damage = (int) Math.round(damage/2.0);
    }

    if (damage > target.getHealth()) {
      damage = target.getHealth();
    }
  
    return damage;
  }
  
  /**
   * Calculates the effectiveness of a move being used and adjusts damage accordingly
   * @param damage Current damage calculation
   * @param move The move being used
   * @param target Pokemon being targeted
   * @return Adjusted damage value
   */
  public static int calculateEffectiveness( int damage, Move move, Pokemon target) {
    Type[] positive = move.getType().getPosEffects();
    Type[] negative = move.getType().getNegEffects();
    Type zero = move.getType().getZeroEffects();
    Type[] pokeType = target.getType();
      
    for (int j = 0; j < 2; j++) {
      // Multiply damage by 2 for every positive match
      for (int i = 0; i < positive.length; i++){
         if ( positive[i] == pokeType[j] )
         {      
           damage = damage * 2;
         }
      }
       
      // Divide damage by 2 for every negative match made
      for (int i = 0; i < negative.length; i++){
        if ( negative[i] == pokeType[j] ) {
             damage = damage/2;
        }
      }        
       
      // If the move type does not work against the pokemon type at all
      if ( zero != Type.NULLTYPE && pokeType[j] == zero){
        damage = 0;
      }
    }       

    return damage;      
    
  }
  
  /**
   * Display battle messages related to the attacking turn.
   * TODO: Move strings to their own class as constants
   * @param damage Damage done
   * @param move Move being used
   * @param target Pokemon being attacked
   */
  public static void displayMessages( int damage, Move move, Pokemon target ) {
    
    int base = move.getDamage();
    
    // Super Effective
    if (base < calculateEffectiveness(base, move, target)){ 
        System.out.println("It's super effective!");      
        if (move.wasCritical()) {
          System.out.println(" A critical hit!");
        }
    }
    // Not at all effective
    else if (calculateEffectiveness(base, move, target) == 0){
        System.out.println("But it didn't work!");
        System.out.println();
        return;
    }
    // Not very effective
    else if (base > calculateEffectiveness(base, move, target)){
      System.out.println("It's not very effective...");
      if (move.wasCritical()) {
        System.out.print(" A critical hit!");
      }
    }
    // Normal effectiveness
    else {
      if (move.wasCritical()) {
        System.out.print(" A critical hit!");
      }
    }

    System.out.println(target.getName() + " took " + damage + " damage!\n");
    if (move.getMessage() != null) {
      move.printMessage();
      move.resetMessage();
    }
      
  }
}