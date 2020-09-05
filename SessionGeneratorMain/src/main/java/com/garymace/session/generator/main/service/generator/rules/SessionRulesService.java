package com.garymace.session.generator.main.service.generator.rules;

import com.garymace.session.generator.base.models.SportType;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.google.inject.Inject;
import java.util.Optional;

public class SessionRulesService {
  private final SwimSessionRulesService swimSessionRulesService;

  @Inject
  public SessionRulesService(SwimSessionRulesService swimSessionRulesService) {
    this.swimSessionRulesService = swimSessionRulesService;
  }

  public SessionRules getRules(Profile profile, SessionStageType sessionStageType) {
    if (profile.getSportType() == SportType.SWIM) {
      Optional<SessionRules> maybeSessionRules = swimSessionRulesService.supplySessionRules(
        profile,
        sessionStageType
      );
      if (!maybeSessionRules.isPresent()) {
        throw new RuntimeException(
          String.format("Unable to find %s session rules for Swimming", sessionStageType)
        );
      }
      return maybeSessionRules.get();
    }
    throw new RuntimeException(); //TODO: do something else here
  }
}
