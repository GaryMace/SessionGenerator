package com.garymace.session.generator.service.resource;

import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.service.managers.SessionGeneratorManager;
import com.garymace.session.generator.service.utils.ApiPreconditions;
import com.google.inject.Inject;

@Path("generate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionGeneratorResource {

  private final SessionGeneratorManager sessionGeneratorManager;

  @Inject
  public SessionGeneratorResource(SessionGeneratorManager sessionGeneratorManager) {
    this.sessionGeneratorManager = sessionGeneratorManager;
  }

  @POST
  public Set<SwimTrainingSession> generate(Profile userProfile) {
    ApiPreconditions.checkNotNull(userProfile, "UserProfile may not be null");
    return sessionGeneratorManager.generateSession(userProfile);
  }
}
