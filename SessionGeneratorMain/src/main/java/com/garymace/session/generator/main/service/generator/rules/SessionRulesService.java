package com.garymace.session.generator.main.service.generator.rules;

import com.garymace.session.generator.base.models.SportType;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.google.inject.Inject;

public class SessionRulesService {

    private final SwimMainsetSessionRules swimMainsetSessionRules;
    private final SwimWarmupSessionRules swimWarmupSessionRules;

    @Inject
    public SessionRulesService(SwimMainsetSessionRules swimMainsetSessionRules,
                               SwimWarmupSessionRules swimWarmupSessionRules) {
        this.swimMainsetSessionRules = swimMainsetSessionRules;
        this.swimWarmupSessionRules = swimWarmupSessionRules
    }

    public SessionRules getRules(Profile profile, SessionStageType sessionStageType) {
        if (profile.getSportType() == SportType.SWIM) {
            if (sessionStageType == SessionStageType.WARMUP){
                return swimWarmupSessionRules.supplySessionRules(profile);
            } else if (sessionStageType == SessionStageType.MAINSET) {
                return swimMainsetSessionRules.supplySessionRules(profile);
            }
        }
    }
}
