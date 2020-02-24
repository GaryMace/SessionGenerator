package com.garymace.session.generator.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.main.config.SessionGeneratorModule;
import com.garymace.session.generator.main.service.TrainingSessionGenerator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.ProfileLoadingUtils;

import java.util.Optional;
import java.util.Set;

public class SessionGeneratorMain {
    private static final Logger LOG = LoggerFactory.getLogger(SessionGeneratorMain.class);
    private static final String VALID_INPUT =
            "{\"athletic_level\":\"...\", \"weekly_session_preference\":\"...\", \"sport_type\":\"...\"}";

    public SessionGeneratorMain() {
    }

    public static void main(String[] args) throws JsonProcessingException {
        Injector injector = Guice.createInjector(new SessionGeneratorModule());

        ProfileLoadingUtils profileLoadingUtils = injector.getInstance(ProfileLoadingUtils.class);
        TrainingSessionGenerator trainingSessionGenerator = injector.getInstance(TrainingSessionGenerator.class);
        ObjectMapper objectMapper = injector.getInstance(ObjectMapper.class);

        LOG.info("Starting programme");
        if (args.length != 1) {
            throw new RuntimeException(String.format("Invalid input; expected profileInput file of form: %s", VALID_INPUT));
        }
        Optional<Profile> maybeProfile = profileLoadingUtils.loadProfile(args);
        if (!maybeProfile.isPresent()) {
            throw new RuntimeException("An error occurred while attempting to load profile");
        }

        Set<SwimTrainingSession> trainingSessions = trainingSessionGenerator.generateFrom(maybeProfile.get());
        LOG.info("Session is: {}", objectMapper.writeValueAsString(trainingSessions));
    }
}
