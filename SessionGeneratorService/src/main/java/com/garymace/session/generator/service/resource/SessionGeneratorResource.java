package com.garymace.session.generator.service.resource;

import java.util.Set;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.service.managers.SessionGeneratorManager;
import com.google.inject.Inject;

@Path("generate")
public class SessionGeneratorResource {

  private final SessionGeneratorManager sessionGeneratorManager;

  @Inject
  public SessionGeneratorResource(SessionGeneratorManager sessionGeneratorManager) {
    this.sessionGeneratorManager = sessionGeneratorManager;
  }

  @POST
  public Set<SwimTrainingSession> generate(Profile userProfile) {
    return sessionGeneratorManager.generateSession(userProfile);
  }
}
