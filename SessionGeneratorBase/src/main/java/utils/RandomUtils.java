package utils;

import com.garymace.session.generator.base.models.session.rules.SessionRules;
import java.util.Random;

public class RandomUtils {

  public RandomUtils() {}

  public static int getInRange(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }

  public static int getInSessionRulesRange(SessionRules sessionRules) {
    return (
      new Random()
      .nextInt((sessionRules.getMaxDistance() - sessionRules.getMinDistance()) + 1) +
      sessionRules.getMinDistance()
    );
  }
}
