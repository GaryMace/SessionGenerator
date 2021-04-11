package com.garymace.session.generator.main.service;

import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.SetType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.garymace.session.generator.base.models.session.rules.config.PostSetRestDuration;
import com.garymace.session.generator.base.models.session.rules.config.RepDetail;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CollectionUtils;
import utils.RandomUtils;
import utils.SetRepUtils;

public class SessionSetService {
  private static final Logger LOG = LoggerFactory.getLogger(SessionSetService.class);

  @Inject
  public SessionSetService() {}

  public static SessionSet generateSessionSet(SessionRules sessionRules) {
    DistanceDetail distanceDetail = CollectionUtils.getRandomItem(
      sessionRules.getPermittedDistanceDetails()
    );
    SetType setType = CollectionUtils.getRandomItem(
      distanceDetail.getPermittedSetTypes()
    );
    RepDetail repDetailsForSetType = distanceDetail
      .getRepDetails()
      .stream()
      .filter(repDetail -> repDetail.getSetType() == setType)
      .findFirst()
      .get(); // Let's just pretend it's always there.. for now

    int restSeconds = distanceDetail
      .getPostSetRestDurations()
      .stream()
      .filter(repDetail -> repDetail.getSetType() == setType)
      .findFirst()
      .get()
      .getRestDurationSeconds(); // Let's just pretend it's always there.. for now

    return SessionSet
      .builder()
      .setSetReps(SetRepUtils.determineReps(repDetailsForSetType))
      .setPostSessionRestSeconds(determinePostSessionRestSeconds(restSeconds))
      .setSetType(setType)
      .setSetItems(buildSetItems(sessionRules, distanceDetail, setType, restSeconds))
      .build();
  }

  private static int determinePostSessionRestSeconds(int restSeconds) {
    BigDecimal rawPostSessionRest = BigDecimal
      .valueOf(restSeconds)
      .multiply(BigDecimal.valueOf(1.5));
    rawPostSessionRest = rawPostSessionRest.setScale(-1, RoundingMode.HALF_UP);

    return rawPostSessionRest.intValue();
  }

  private static List<SetItem> buildSetItems(
    SessionRules sessionRules,
    DistanceDetail distanceDetail,
    SetType setType,
    int restSeconds
  ) {
    if (setType != SetType.PYRAMID) {
      return Collections.singletonList(
        SetItem
          .builder()
          .setDistance(distanceDetail.getDistance())
          .setRestSeconds(restSeconds)
          .build()
      );
    }

    List<DistanceDetail> pyramidableDistanceDetails = sessionRules
      .getPermittedDistanceDetails()
      .stream()
      .filter(detail -> detail.getPermittedSetTypes().contains(SetType.PYRAMID))
      .sorted(Comparator.comparingInt(DistanceDetail::getDistance))
      .collect(Collectors.toList());
    int maxPyramidLevels = pyramidableDistanceDetails.size();
    int pyramidLevelToBuild = RandomUtils.getInRange(2, maxPyramidLevels);

    List<SetItem> pyramidSetItems = pyramidableDistanceDetails
      .subList(0, pyramidLevelToBuild)
      .stream()
      .map(
        detail ->
          SetItem
            .builder()
            .setDistance(detail.getDistance())
            .setRestSeconds(
              detail
                .getPostSetRestDurations()
                .stream()
                .filter(
                  postSetRestDuration ->
                    postSetRestDuration.getSetType() == SetType.PYRAMID
                )
                .map(PostSetRestDuration::getRestDurationSeconds)
                .findFirst()
                .get()
            )
            .build()
      )
      .collect(Collectors.toList());

    List<SetItem> pyramidSecondHalf = new ArrayList<>(
      pyramidSetItems.subList(0, pyramidSetItems.size() - 1)
    );
    Collections.reverse(pyramidSecondHalf);
    return ImmutableList
      .<SetItem>builder()
      .addAll(pyramidSetItems)
      .addAll(pyramidSecondHalf)
      .build();
  }
}
