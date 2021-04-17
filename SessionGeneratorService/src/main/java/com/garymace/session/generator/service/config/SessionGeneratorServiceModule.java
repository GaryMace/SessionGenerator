package com.garymace.session.generator.service.config;

import com.garymace.session.generator.service.resource.SessionGeneratorResource;
import com.google.inject.Binder;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;

public class SessionGeneratorServiceModule
  extends DropwizardAwareModule<SessionGeneratorServiceConfiguration> {

  @Override
  public void configure(Binder binder) {
    // You can use the configuration to conditionally bind resources
    if (getConfiguration().bindResources()) {
      binder.bind(SessionGeneratorResource.class);
    }
/*    // You can also bind request/response filters
    if (getConfiguration().bindFilters()) {
      binder.bind(ExampleRequestFilter.class);
      binder.bind(ExampleResponseFilter.class);
    }*/

    // Any Managed, Task, HealthCheck, or ServerLifecycleListener bound in Guice will be added to Dropwizard for you
/*    if (getConfiguration().bindExtras()) {
      binder.bind(SessionGeneratorManager.class);
      binder.bind(TrainingSessionGenerator.class);
      binder.bind(SwimSessionRulesService.class);
      binder.bind(SessionRulesService.class);
    }*/
  }
}
