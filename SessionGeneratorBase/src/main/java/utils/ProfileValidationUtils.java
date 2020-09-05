package utils;

import com.garymace.session.generator.base.models.profile.Profile;

public class ProfileValidationUtils {

  public ProfileValidationUtils() {}

  public static boolean isProfileValid(Profile profile) {
    return (
      profile.getWeeklySessionPreference() > 0 &&
      profile.getWeeklySessionPreference() < 14
    );
  }
}
