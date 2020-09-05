package utils;

import java.util.Collection;

public class CollectionUtils {

  public CollectionUtils() {}

  public static <T> T getRandomItem(Collection<T> collection) {
    int randomItem = (int) (Math.random() * collection.size());
    for (T item : collection) {
      if (randomItem-- == 0) {
        return item;
      }
    }
    throw new RuntimeException("Unexpected error; didn't find an item in collection");
  }
}
