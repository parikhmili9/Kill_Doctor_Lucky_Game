package worldmodel.model;

import java.util.Random;

/**
 * This class implements predictable and random number generator.
 *
 * @author shreyas.terdalkar
 *
 */
public class RandomGenerator implements RandomInterface {

  private Random rand;
  private int randomNum;
  
  /**
   * Generic constructor used to construct random no generator class.
   *
   */
  public RandomGenerator() {
    this.rand = new Random();
    this.randomNum = -1;
  }
  
  /**
   * This constructor is used to generate mocked random number.
   *
   * @param var represents mock random value
   */
  public RandomGenerator(int var) {
    this.rand = null;
    this.randomNum = var;
  }
  
  @Override
  public int randomGenerator() {
    if (this.rand == null) {
      return this.randomNum;
    } else {
      return (this.rand.nextInt(4));
    }
  }

}
