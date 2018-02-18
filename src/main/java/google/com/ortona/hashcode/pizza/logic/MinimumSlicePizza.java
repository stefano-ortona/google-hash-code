package google.com.ortona.hashcode.pizza.logic;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import google.com.ortona.hashcode.pizza.model.Inrgedient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;
import google.com.ortona.hashcode.pizza.model.Point;
import google.com.ortona.hashcode.pizza.model.Slice;

public class MinimumSlicePizza {
  private final ScoreComputation score;

  public MinimumSlicePizza(ScoreComputation score) {
    this.score = score;
  }

  public PizzaStatus computeSlices(Inrgedient[][] pizza, final int L) {
    final PizzaStatus status = new PizzaStatus(pizza);
    // compute initialisePoints
    final List<Point> startPoint = computeInitialisePoint(pizza);
    // randomize points
    final Map<Inrgedient, Integer> curStatus = Maps.newHashMap();
    Collections.shuffle(startPoint);
    startPoint.forEach(p -> {
      // create a new fake map
      final Map<Inrgedient, Integer> curStatusMap = Maps.newHashMap();
      curStatusMap.putAll(curStatus);
      // check the point is not currently occupied
      if (!status.isCellOccupied(p.x, p.y)) {
        // put the current ingredient in the map
        curStatusMap.put(pizza[p.x][p.y], 1);
        // expand until you can
        boolean canStillExpand = true;
        Slice slice = new Slice(p.x, p.y, p.x, p.y);
        while (canStillExpand) {
          slice = expandSlice(slice, status, curStatusMap);
          if (slice == null) {
            // cannot expand anymore, drop initial slice
            canStillExpand = false;
            status.removeSlice(new Slice(p.x, p.y, p.x, p.y));
          }
          // check slice is valid
          if (isValid(slice, pizza, L)) {
            // add current slice
            status.addSlice(slice);
            canStillExpand = false;
            // adjorn status
            curStatus.clear();
            curStatus.putAll(curStatusMap);
          }
        }
      }
    });
    return status;
  }

  private Slice expandSlice(Slice slice, PizzaStatus status, final Map<Inrgedient, Integer> curStatus) {
    // try to expand in all possible 6 direction

    Map<Inrgedient, Integer> bestMap = null;
    double bestScore = -1;
    int newUpLX = 0;
    int newUpLY = 0;
    int newLoRX = 0;
    int newLoRY = 0;
    boolean hasExpand = false;
    // expand left
    final Inrgedient[][] pizza = status.pizza;
    Map<Inrgedient, Integer> map1ToAdd = null;
    final boolean left = status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.upperLeftY - 1);
    if (left) {
      map1ToAdd = Maps.newHashMap();
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        final int curValue = map1ToAdd.getOrDefault(pizza[i][slice.upperLeftY - 1], 0) + 1;
        map1ToAdd.put(pizza[i][slice.upperLeftY - 1], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, map1ToAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY - 1;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = map1ToAdd;
      }
    }
    // expand up
    Map<Inrgedient, Integer> map2ToAdd = null;
    final boolean up = status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.upperLeftX - 1);
    if (up) {
      map2ToAdd = Maps.newHashMap();
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        final int curValue = map2ToAdd.getOrDefault(pizza[slice.upperLeftX - 1][j], 0) + 1;
        map2ToAdd.put(pizza[slice.upperLeftX - 1][j], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, map2ToAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX - 1;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = map2ToAdd;
      }
    }
    // expand left and up
    if (left && up && status.isCellOccupied(slice.upperLeftX - 1, slice.upperLeftY - 1)) {
      final Map<Inrgedient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(map2ToAdd);
      toAdd.putAll(map2ToAdd);
      final int curValue = toAdd.getOrDefault(pizza[slice.upperLeftX - 1][slice.upperLeftY - 1], 0) + 1;
      toAdd.put(pizza[slice.upperLeftX - 1][slice.upperLeftY - 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX - 1;
        newUpLY = slice.upperLeftY - 1;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = toAdd;
      }
    }

    // expand right
    final boolean right = status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.lowerRightY + 1);
    if (right) {
      map1ToAdd = Maps.newHashMap();
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        final int curValue = map1ToAdd.getOrDefault(pizza[i][slice.lowerRightY + 1], 0) + 1;
        map1ToAdd.put(pizza[i][slice.lowerRightY + 1], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, map1ToAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY + 1;
        hasExpand = true;
        bestMap = map1ToAdd;
      }
    }
    // expand down
    final boolean down = status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.upperLeftX + 1);
    if (down) {
      map2ToAdd = Maps.newHashMap();
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        final int curValue = map2ToAdd.getOrDefault(pizza[slice.lowerRightX + 1][j], 0) + 1;
        map2ToAdd.put(pizza[slice.lowerRightX + 1][j], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, map2ToAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX + 1;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = map2ToAdd;
      }
    }
    // expand right and down
    if (right && down && status.isCellOccupied(slice.lowerRightX + 1, slice.lowerRightY + 1)) {
      final Map<Inrgedient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(map2ToAdd);
      toAdd.putAll(map2ToAdd);
      final int curValue = toAdd.getOrDefault(pizza[slice.lowerRightX + 1][slice.lowerRightY + 1], 0) + 1;
      toAdd.put(pizza[slice.lowerRightX + 1][slice.lowerRightY + 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice);
      if (curScore > bestScore) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX + 1;
        newLoRY = slice.lowerRightY + 1;
        hasExpand = true;
        bestMap = toAdd;
      }
    }

    if (hasExpand) {
      slice.upperLeftX = newUpLX;
      slice.upperLeftY = newUpLY;
      slice.lowerRightX = newLoRX;
      slice.lowerRightY = newLoRY;
      // update cur status with best map
      curStatus.putAll(bestMap);
      return slice;
    }
    // cannot be expanded
    return null;

  }

  private List<Point> computeInitialisePoint(Inrgedient[][] pizza) {
    final List<Point> allPoints = Lists.newLinkedList();
    // get minimum ingredients
    final Map<Inrgedient, Integer> ing2presence = Maps.newHashMap();
    for (int i = 0; i < pizza.length; i++) {
      for (int j = 0; j < pizza[0].length; j++) {
        final int curValue = ing2presence.getOrDefault(pizza[i][j], 0) + 1;
        ing2presence.put(pizza[i][j], curValue);
      }
    }
    final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    final Inrgedient mimimum[] = new Inrgedient[1];
    ing2presence.forEach((k, v) -> {
      if (v < min.get()) {
        min.set(v);
        mimimum[0] = k;
      }
    });

    // get all points with minimum ingredient
    for (int i = 0; i < pizza.length; i++) {
      for (int j = 0; j < pizza[0].length; j++) {
        if (pizza[i][j] == mimimum[0]) {
          final Point p = new Point(i, j);
          allPoints.add(p);
        }
      }
    }
    return allPoints;
  }

  private boolean isValid(Slice slice, Inrgedient[][] pizza, int L) {
    // check it contains minimum number of ingredients
    final Map<Inrgedient, Integer> ingr2count = Maps.newHashMap();
    final Set<Inrgedient> toSatisfy = Sets.newHashSet(Inrgedient.values());

    for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        final int count = ingr2count.getOrDefault(pizza[i][j], 0) + 1;
        ingr2count.put(pizza[i][j], count);
        if (count >= L) {
          toSatisfy.remove(pizza[i][j]);
          if (toSatisfy.isEmpty()) {
            return true;
          }
        }
      }
    }
    return false;

  }

}
