package utils;

import java.util.Random;

public class RandomUtils {

  public RandomUtils() {}

  public static int getInRange(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }
}
