package utils;

import com.garymace.session.generator.base.models.session.SetType;
import com.garymace.session.generator.base.models.session.rules.config.RepDetail;
import java.util.Random;

public class SetRepUtils {

  SetRepUtils() {}

  public static int determineReps(RepDetail repDetail) {
    Random random = new Random();
    int reps =
      random.nextInt((repDetail.getMax() - repDetail.getMin()) + 1) + repDetail.getMin();

    if (repDetail.getSetType() == SetType.TRIANGLE_SET) {
      int remainder = reps % 3;
      return remainder == 0 && reps != 0 ? reps : reps + 3 - remainder;
    }
    return reps;
  }
}
