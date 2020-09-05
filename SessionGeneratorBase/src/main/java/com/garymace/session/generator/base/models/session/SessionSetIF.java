package com.garymace.session.generator.base.models.session;

import com.hubspot.immutables.style.HubSpotStyle;
import java.util.List;
import org.immutables.value.Value;
import org.immutables.value.Value.Derived;

@Value.Immutable
@HubSpotStyle
public interface SessionSetIF {
  int getSetReps();
  int getPostSessionRestSeconds();
  SetType getSetType();
  List<SetItem> getSetItems();

  @Derived
  default int getSetDistance() {
    return (
      getSetReps() *
      getSetItems().stream().map(SetItem::getDistance).reduce(Integer::sum).orElse(0)
    );
  }
}
