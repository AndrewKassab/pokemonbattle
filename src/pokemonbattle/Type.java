package pokemonbattle;

/**
 * Pokemon types
 * @author precisemotion
 */
public enum Type {
  NORMAL, FIRE, WATER, GRASS, ELECTRIC, ICE, FIGHTING, POISON, GROUND, FLYING,
    PSYCHIC, BUG, ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY, NULLTYPE;
  
  /**
   * Takes in a move, and then determines what types this move
   * is super effective against.
   * @param m Move being used.
   * @return String array containing all effective types.
   */
  public Type[] getPosEffects() {

    // Checks type of move
    switch (this){
    case NORMAL: 
      return new Type[] {};
    case FIRE:
      return new Type[] {GRASS,ICE,BUG,STEEL};
    case WATER:
      return new Type[] {FIRE,GROUND,ROCK};
    case ELECTRIC:
      return new Type[] {WATER,FLYING};
    case GRASS:
      return new Type[] {WATER,GROUND,ROCK};
    case ICE:
      return new Type[] {GRASS,GROUND,FLYING,DRAGON};
    case FIGHTING:
      return new Type[] {NORMAL,ICE,ROCK,DARK,STEEL};
    case POISON:
      return new Type[] {GRASS,FAIRY};
    case GROUND:
      return new Type[] {FIRE,ELECTRIC,POISON,ROCK,STEEL};
    case FLYING:
      return new Type[] {GRASS,FIGHTING,BUG};
    case PSYCHIC:
      return new Type[] {FIGHTING,POISON};
    case BUG:
      return new Type[] {GRASS,PSYCHIC,DARK};
    case ROCK:
      return new Type[] {FIRE,ICE,FLYING,BUG};
    case GHOST:
      return new Type[] {PSYCHIC,GHOST};
    case DRAGON:
      return new Type[] {DRAGON};
    case DARK:
      return new Type[] {PSYCHIC,ROCK};
    case STEEL: 
      return new Type[] {ICE,FAIRY,ROCK};
    case FAIRY:
      return new Type[] {FIGHTING,DRAGON,DARK};
    case NULLTYPE:
      return new Type[] {};
    default:
      System.err.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES"); 
      System.exit(-1); 
      return new Type[] {NULLTYPE}; // unreachable     
    }
    
    // If the type does not match any case, error displays and program ends.
  }
  
  /**
   * Takes in a move, and then determines what types this move
   * is not very effective against.
   * @param m Move being used.
   * @return String array containing all resistant types.
   */
  public Type[] getNegEffects() {
    
    // Check type of move
    switch (this){
    case NORMAL:
      return new Type[] {GROUND,ROCK,STEEL};
    case FIRE:
      return new Type[] {FIRE,WATER,ROCK,DRAGON};
    case WATER:
      return new Type[] {WATER,GRASS,DRAGON};
    case ELECTRIC:
      return new Type[] {GROUND,ELECTRIC,GRASS,DRAGON};
    case GRASS:
      return new Type[] {FIRE,GRASS,POISON,BUG,DRAGON,STEEL};
    case ICE:
      return new Type[] {FIRE,WATER,ICE,STEEL};
    case FIGHTING:
      return new Type[] {GHOST,POISON,FLYING,PSYCHIC,BUG,FAIRY};
    case POISON:
      return new Type[] {STEEL,GROUND,ROCK,GHOST};
    case GROUND:
      return new Type[] {FLYING,GRASS,BUG};
    case FLYING:
      return new Type[] {ELECTRIC,ROCK,STEEL};
    case PSYCHIC:
      return new Type[] {DARK,PSYCHIC,STEEL};
    case BUG:
      return new Type[] {FIRE,FIGHTING,POISON,FLYING,GHOST,STEEL,FAIRY};
    case ROCK:
      return new Type[] {FIGHTING,GROUND,STEEL};
    case GHOST:
      return new Type[] {NORMAL,DARK};
    case DRAGON:
      return new Type[] {FAIRY, STEEL};
    case DARK:
      return new Type[] {FIGHTING,DARK,FAIRY};
    case STEEL:
      return new Type[] {FIRE,WATER,ELECTRIC,STEEL};
    case FAIRY:
      return new Type[] {FIRE,POISON,STEEL};
    case NULLTYPE:
      return new Type[] {};
    default:
      System.err.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES");
      System.exit(-1);
      return new Type[] {NULLTYPE}; // unreachable
    }
  }
  
  /**
  * Takes in a move, and then determines what type this move
  * is not at all effective against.
  * @param m Move being used.
  * @return String containing the resistant type (if any exists).
  */
  public Type getZeroEffects() {

    // Check type of move
    switch (this){
    case NORMAL:
      return GHOST;
    case ELECTRIC:
      return GROUND;
    case FIGHTING:
      return GHOST;
    case POISON:
      return STEEL;
    case GROUND:
      return FLYING;
    case PSYCHIC:
      return DARK;
    case GHOST:
      return NORMAL;
    default:
      return NULLTYPE;
    }

  }

}
