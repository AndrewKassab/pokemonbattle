package pokemonbattle;

/**
 * Stores and manipulates pokemon's stats.
 * @author precisemotion
 */
public class Stat {
  
  int value;
  int base;
  int stage;

  public Stat( int value ) {
    this.value = value;
    this.base = value;
    stage = 0;
  }

  /**
   * Factors in stat stages to calculate a Pokemon's stat in battle.
   */
  private void calculateStat() {
    value = (int) (base * Math.round( ( Math.max( 2.0, 2.0 + stage) )/
                                           ( Math.max( 2.0, 2.0 - stage) ) ) );
  }

  public void incrementStat( Operator op ) {
    switch(op) {
    case INCREMENT:
      if (stage < 6){
        stage++;
      }
      break;
    case DECREMENT:
      if (stage > -6) {
        stage++;
      }    
      break;
    }
    calculateStat();
  }
  
  public void resetStage() {
    stage = 0;
    calculateStat();
  }
}

class Stats {

  Stat health;
  Stat attack;
  Stat spAttack;
  Stat defense;
  Stat spDefense;
  Stat speed;
  
  public Stats( int health, int attack, int spAttack, int defense, int spDefense, int speed) {
    this.health = new Stat(health);
    this.attack = new Stat(attack);
    this.spAttack = new Stat(spAttack);
    this.defense = new Stat(defense);
    this.spDefense = new Stat(spDefense);
    this.speed = new Stat(speed);
  }
  
}

enum Operator {
  INCREMENT, DECREMENT;
}