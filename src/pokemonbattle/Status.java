package pokemonbattle;

/**
 * Status effects
 */
public enum Status {

  PARALYSIS (StatusType.LETHAL, "%s is paralyzed! It might be unable to move!\n", 
             "%s is paralyzed! It can't move!\n"),
  FROZEN    (StatusType.LETHAL, "%s is frozen solid!\n", "%s thawed out!\n"),
  SLEEP     (StatusType.LETHAL, "%s is fast asleep.\n", "%s woke up!\n"),
  BURN      (StatusType.LETHAL, "%s is hurt by its burn!\n", "%s has fainted!\n"),
  POISON    (StatusType.LETHAL, "%s is hurt by poison!\n", "%s has fainted!\n"),
  BADPOISON (StatusType.LETHAL, "%s is hurt by bad poison!\n", "%s has fainted!\n"),
  CONFUSION (StatusType.NONLETHAL, "%s is confused!\n", "%s hurt itself in confusion!\n"),
  NULLSTATUS(StatusType.NULLSTAT, "NULL\n", "NULL\n" );
  

  final StatusType type;
  private final String messageOne;
  private final String messageTwo;
  int counter;
  
  private Status(StatusType type, String mOne, String mTwo) {
    this.type = type;
    messageOne = mOne;
    messageTwo = mTwo;
    counter = 0;
  }

  /**
   * Applys a status effect to the battle.
   * For effects that work at the end of turns.
   */
  public void applyPostStatus(Trainer trainer) {
    
    Pokemon poke = trainer.getActivePokemon();
    String name = poke.getName();

    switch (this) {
    case BURN:
      poke.setHealth(poke.getHealth().getValue() - (int) Math.round(poke.getMaxHealth()/16.0));
      System.out.printf(messageOne, name);
      if (poke.isFainted()) {
        System.out.printf(messageTwo, name);
        trainer.selectPokemon();
      } else System.out.println();
      counter++;
      break;
    case POISON:
      poke.setHealth(poke.getHealth().getValue() - (int) Math.round(poke.getMaxHealth()/8.0));
      System.out.printf(messageOne, name);
      if (poke.isFainted()) {
        System.out.printf(messageTwo, name);
        trainer.selectPokemon();
      } else System.out.println();
      counter++;
      break;
    case BADPOISON:
      poke.setHealth(poke.getHealth().getValue() - (int) ( Math.round( poke.getMaxHealth() * 
                  ( 1.0/16.0 + (counter * 1.0/16.0) ) ) ) );
      System.out.printf(messageOne, name);
      if (poke.isFainted()) {
        System.out.printf(messageTwo, name);
        trainer.selectPokemon();
      } else System.out.println();
      counter++;
      break;
     default:
       break;
    }

  }   

  /**
   * Applys a status effect to the battle
   * For effects that work at the beginning of turns.
   * @throws InterruptedException 
   */
  public void applyPreStatus(Trainer trainer) throws InterruptedException {

    double random = Math.random();
    Pokemon poke = trainer.getActivePokemon();
    String name = poke.getName();
    
    //TODO remove status condition
    switch (this) {
    case PARALYSIS:
      System.out.printf(messageOne, name);
      Thread.sleep(2000);
      if (random < .25) {
        System.out.printf(messageTwo, name);
        System.out.println();
        trainer.setCanAttack(false);
      }
      counter++;
      break;
    case FROZEN:
      if (Math.random() >= .20) {
        System.out.printf(messageOne,name);
        System.out.println();
        trainer.setCanAttack(false);
        counter++;
      }
      else {
        System.out.printf(messageTwo,name);
        System.out.println();
        poke.setStatus(NULLSTATUS, StatusType.LETHAL);
      }
      break;
    case SLEEP:
      if (counter < 3) {
        if (random >= .33) { // 33% chance of waking up.
          System.out.printf(messageOne, name);
          System.out.println();
          counter++;
          trainer.setCanAttack(false);
        }
        else {
          System.out.printf(messageTwo, name);
          System.out.println();
          poke.setStatus(NULLSTATUS, StatusType.LETHAL);
        }
      }
      else { // After the 3rd turn, gauranteed wake up.
        System.out.printf(messageTwo, name);
        System.out.println();
        poke.setStatus(NULLSTATUS, StatusType.LETHAL);
      }
        break;
      case CONFUSION:
        System.out.printf(messageOne,name);
        // been less than 4 turns
        if (counter < 4){
          // 33% chance of snapping out
          if (random >= .33){
            random = Math.random(); 
            // 33% chance of hurting self
            if (random < .33){
              System.out.printf(messageTwo, name);
              // TODO: Fix length 
              int damage = (int) Math.round(((22 * 40 * (poke.getAttack().getValue()/
                                              poke.getDefense().getValue()))/50.0 + 2));
              poke.setHealth(poke.getHealth().getValue() - damage);
              trainer.setCanAttack(false);
              counter++;
              return;
            }
            // still confused but didn't attack self
            else {
              counter++;
              return;
            }
          }
        }
        // snaps out of confusion 
        System.out.println(name + " snapped out of it's confusion!");
        poke.setStatus(NULLSTATUS,StatusType.NONLETHAL);
        break;
      default:// TODO add more non-lethals
        break;  
      }
  }
}

/**
 * Indicates the type of status effect
 * @author precisemotion
 */
enum StatusType {
  LETHAL, NONLETHAL, NULLSTAT;
}