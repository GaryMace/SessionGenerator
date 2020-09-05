package utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.garymace.session.generator.base.models.session.SessionStageDetails;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;

public class TrainingSessionLogger {
  public static final Logger LOG = LoggerFactory.getLogger(TrainingSessionLogger.class);

  public TrainingSessionLogger() {}

  public static void log(Set<SwimTrainingSession> swimTrainingSessions) {
    LOG.error("Limiting to just one set for MVP");
    Optional<SwimTrainingSession> maybeSessionToLog = swimTrainingSessions
      .stream()
      .findFirst();
    if (!maybeSessionToLog.isPresent()) {
      throw new RuntimeException("No sessions present to Log");
    }
    SwimTrainingSession sessionToLog = maybeSessionToLog.get();
    String logToPrint = "\nWarmup:";
    logToPrint += generateLogForSessionStage(sessionToLog.getWarmupSessionStageDetails());
    logToPrint += "Mainset:";
    logToPrint +=
      generateLogForSessionStage(sessionToLog.getMainsetSessionStageDetails());
    logToPrint += "Cooldown:";
    logToPrint +=
      generateLogForSessionStage(sessionToLog.getCooldownSessionStageDetails());
    LOG.info(logToPrint);
  }

  private static String generateLogForSessionStage(
    SessionStageDetails sessionStageDetails
  ) {
    String stageRepsLogLine = String.format(
      "\n%dx (\n",
      sessionStageDetails.getSetCount()
    );

    String allSetLogs = sessionStageDetails
      .getSessionSets()
      .stream()
      .map(
        sessionSet -> {
          List<SetItem> setItems = sessionSet.getSetItems();
          String logsForSetItems = setItems
            .stream()
            .map(
              setItem ->
                String.format(
                  "  %dx%dm %dsec/rec (%s)",
                  sessionSet.getSetReps(),
                  setItem.getDistance(),
                  setItem.getRestSeconds(),
                  sessionSet.getSetType()
                )
            )
            .reduce(String::concat)
            .orElse("NOTHING");
          return String.format(
            "%s\n\t\t\t\t %dsec/rec\n",
            logsForSetItems,
            sessionSet.getPostSessionRestSeconds()
          );
        }
      )
      .reduce(String::concat)
      .orElse("NOTHING");
    return stageRepsLogLine + allSetLogs + ")\n\n";
  }
}
