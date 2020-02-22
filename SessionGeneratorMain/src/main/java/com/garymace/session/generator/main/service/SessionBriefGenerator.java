package com.garymace.session.generator.main.service;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class SessionBriefGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SessionBriefGenerator.class);

    @Inject
    public SessionBriefGenerator() {

    }

    public Set<SwimSessionBrief> generateFrom(Profile profile) {

    }
}
