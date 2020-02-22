package com.garymace.session.generator.main;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.main.service.SessionBriefGenerator;
import com.google.inject.Inject;
import utils.ProfileLoadingUtils;

import java.util.Optional;
import java.util.Set;

public class SessionGeneratorMain {
    private static final String VALID_INPUT =
            "{\"athletic_level\":\"...\", \"weekly_session_preference\":\"...\", \"sport_type\":\"...\"}";

    private final ProfileLoadingUtils profileLoadingUtils;
    private final SessionBriefGenerator sessionBriefGenerator;

    @Inject
    public SessionGeneratorMain(ProfileLoadingUtils profileLoadingUtils,
                SessionBriefGenerator sessionBriefGenerator) {
            this.profileLoadingUtils = profileLoadingUtils;
            this.sessionBriefGenerator = sessionBriefGenerator;
        }

        public void main(String[] args) {
            if (args.length != 1) {
                throw new RuntimeException(String.format("Invalid input; expected profileInput file of form: %s", VALID_INPUT));
            }
            Optional<Profile> maybeProfile = profileLoadingUtils.loadProfile(args);
        if (!maybeProfile.isPresent()) {
            throw new RuntimeException("An error occurred while attempting to load profile");
        }

        Set<SwimSessionBrief> sessionBriefs = sessionBriefGenerator.generateFrom(maybeProfile.get());
    }
}
