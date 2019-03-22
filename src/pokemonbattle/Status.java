package pokemonbattle;

/**
 * Status effects
 */
public enum Status {

  PARALYSIS (StatusType.LETHAL, "%s is paralyzed! It might be unable to move!", 
             "%s is paralyzed! It can't move!"),
  FROZEN    (StatusType.LETHAL, "%s is frozen solid!", "%s thawed out!"),
  SLEEP     (StatusType.LETHAL, "%s is fast asleep.", "%s woke up!"),
  BURN      (StatusType.LETHAL, "%s is hurt by its burn!", "%s has fainted!"),
  POISON    (StatusType.LETHAL, "%s is hurt by poison!", "%s has fainted!"),
  BADPOISON (StatusType.LETHAL, "%s is hurt by bad poison!", "%s has fainted!"),
  CONFUSION (StatusType.NONLETHAL, "%s is confused!", "%s hurt itself in confusion!"),
  NULLSTATUS(StatusType.NULLSTAT, "NULL", "NULL" );
  

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

}

/**
 * Indicates the type of status effect
 * @author precisemotion
 */
enum StatusType {
  LETHAL, NONLETHAL, NULLSTAT;
}