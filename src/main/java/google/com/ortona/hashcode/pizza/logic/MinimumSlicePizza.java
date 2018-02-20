package google.com.ortona.hashcode.pizza.logic;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import google.com.ortona.hashcode.pizza.model.Ingredient;
import google.com.ortona.hashcode.pizza.model.PizzaStatus;
import google.com.ortona.hashcode.pizza.model.Point;
import google.com.ortona.hashcode.pizza.model.Slice;

public class MinimumSlicePizza {
  private final IngredientScoreComputation score;

  private static final Logger LOGGER = LoggerFactory.getLogger(MinimumSlicePizza.class);

  public MinimumSlicePizza(IngredientScoreComputation score) {
    this.score = score;
  }

  public PizzaStatus computeSlices(Ingredient[][] pizza, final int L, final int maxSliceSize) {
    final PizzaStatus status = new PizzaStatus(pizza);
    // initialise score
    this.score.initialise(pizza);
    // compute initialisePoints
    final List<Point> startPoint = computeInitialisePoint(pizza);
    // randomize points
    final Map<Ingredient, Integer> curStatus = Maps.newHashMap();
    Collections.shuffle(startPoint);
    startPoint.forEach(p -> {
      // create a new fake map
      final Map<Ingredient, Integer> curStatusMap = Maps.newHashMap();
      curStatusMap.putAll(curStatus);
      LOGGER.info("Trying to expand point '{}'.", p);
      // check the point is not currently occupied
      if (!status.isCellOccupied(p.x, p.y)) {
        // put the current ingredient in the map
        curStatusMap.put(pizza[p.x][p.y], curStatusMap.getOrDefault(pizza[p.x][p.y], 0) + 1);
        // expand until you can
        boolean canStillExpand = true;
        Slice slice = new Slice(p.x, p.y, pizza[p.x][p.y]);
        while (canStillExpand) {
          slice = expandSlice(slice, status, curStatusMap, maxSliceSize, L);
          if (slice == null) {
            LOGGER.info("Slice cannot be expanded anymore, trashing.");
            // cannot expand anymore, drop initial slice
            canStillExpand = false;
          } else {
            LOGGER.info("Slice can be expanded to '{}'.", slice);
            // check slice is valid
            if (isValid(slice, pizza, L)) {
              LOGGER.info("Slice is now valid, adding it to the solution.");
              // add current slice
              status.addSlice(slice);
              canStillExpand = false;
              // adjorn status
              curStatus.clear();
              curStatus.putAll(curStatusMap);
            }
          }
        }
      } else {
        LOGGER.info("Point is already occupied by other slices, cannot expand.");
      }
    });
    return status;
  }

  private void addMap(Map<Ingredient, Integer> original, Map<Ingredient, Integer> toAdd) {
    toAdd.forEach((k, v) -> {
      Integer value = original.get(k);
      value = value == null ? v : value + v;
      original.put(k, value);
    });
  }

  private Slice expandSlice(Slice slice, PizzaStatus status, final Map<Ingredient, Integer> curStatus, int maxSliceSize,
      int minSliceIngredient) {
    // try to expand in all possible 6 direction

    final int columnSize = (slice.lowerRightX - slice.upperLeftX) + 1;
    final int rowSize = (slice.lowerRightY - slice.lowerRightY) + 1;
    final int curSize = columnSize * rowSize;
    Map<Ingredient, Integer> bestMap = null;
    double bestScore = -1;
    int newUpLX = 0;
    int newUpLY = 0;
    int newLoRX = 0;
    int newLoRY = 0;
    boolean hasExpand = false;
    final Ingredient[][] pizza = status.pizza;
    Map<Ingredient, Integer> mapLeft = null;
    final boolean left = ((curSize + columnSize) <= maxSliceSize)
        && status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.upperLeftY - 1);
    if (left) {
      mapLeft = Maps.newHashMap();
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        final int curValue = mapLeft.getOrDefault(pizza[i][slice.upperLeftY - 1], 0) + 1;
        mapLeft.put(pizza[i][slice.upperLeftY - 1], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, mapLeft, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY - 1;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = mapLeft;
      }
    }
    // expand up
    Map<Ingredient, Integer> mapUp = null;
    final boolean up = ((curSize + rowSize) <= maxSliceSize)
        && status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.upperLeftX - 1);
    if (up) {
      mapUp = Maps.newHashMap();
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        final int curValue = mapUp.getOrDefault(pizza[slice.upperLeftX - 1][j], 0) + 1;
        mapUp.put(pizza[slice.upperLeftX - 1][j], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, mapUp, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX - 1;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = mapUp;
      }
    }
    // expand left and up
    if (((curSize + rowSize + columnSize + 1) <= maxSliceSize) && left && up
        && !status.isCellOccupied(slice.upperLeftX - 1, slice.upperLeftY - 1)) {
      final Map<Ingredient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(mapLeft);
      addMap(toAdd, mapUp);
      final int curValue = toAdd.getOrDefault(pizza[slice.upperLeftX - 1][slice.upperLeftY - 1], 0) + 1;
      toAdd.put(pizza[slice.upperLeftX - 1][slice.upperLeftY - 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
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
    final Map<Ingredient, Integer> mapRight = Maps.newHashMap();
    final boolean right = ((curSize + columnSize) <= maxSliceSize)
        && status.canExpandVertically(slice.upperLeftX, slice.lowerRightX, slice.lowerRightY + 1);
    if (right) {
      for (int i = slice.upperLeftX; i <= slice.lowerRightX; i++) {
        final int curValue = mapRight.getOrDefault(pizza[i][slice.lowerRightY + 1], 0) + 1;
        mapRight.put(pizza[i][slice.lowerRightY + 1], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, mapRight, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY + 1;
        hasExpand = true;
        bestMap = mapRight;
      }
    }
    // expand down
    final Map<Ingredient, Integer> mapDown = Maps.newHashMap();
    final boolean down = ((curSize + rowSize) <= maxSliceSize)
        && status.canExpandHorizontally(slice.upperLeftY, slice.lowerRightY, slice.lowerRightX + 1);
    if (down) {
      for (int j = slice.upperLeftY; j <= slice.lowerRightY; j++) {
        final int curValue = mapDown.getOrDefault(pizza[slice.lowerRightX + 1][j], 0) + 1;
        mapDown.put(pizza[slice.lowerRightX + 1][j], curValue);
      }
      final double curScore = this.score.computeScore(curStatus, mapDown, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX + 1;
        newLoRY = slice.lowerRightY;
        hasExpand = true;
        bestMap = mapDown;
      }
    }
    // expand right and down
    if (((curSize + rowSize + columnSize + 1) <= maxSliceSize) && right && down
        && !status.isCellOccupied(slice.lowerRightX + 1, slice.lowerRightY + 1)) {
      final Map<Ingredient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(mapRight);
      addMap(toAdd, mapDown);
      final int curValue = toAdd.getOrDefault(pizza[slice.lowerRightX + 1][slice.lowerRightY + 1], 0) + 1;
      toAdd.put(pizza[slice.lowerRightX + 1][slice.lowerRightY + 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
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

    // expand right and up
    if (((curSize + rowSize + columnSize + 1) <= maxSliceSize) && right && up
        && !status.isCellOccupied(slice.upperLeftX - 1, slice.lowerRightY + 1)) {
      final Map<Ingredient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(mapRight);
      addMap(toAdd, mapUp);
      final int curValue = toAdd.getOrDefault(pizza[slice.upperLeftX - 1][slice.lowerRightY + 1], 0) + 1;
      toAdd.put(pizza[slice.upperLeftX - 1][slice.lowerRightY + 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX - 1;
        newUpLY = slice.upperLeftY;
        newLoRX = slice.lowerRightX;
        newLoRY = slice.lowerRightY + 1;
        hasExpand = true;
        bestMap = toAdd;
      }
    }

    // expand left and down
    if (((curSize + rowSize + columnSize + 1) <= maxSliceSize) && left && down
        && !status.isCellOccupied(slice.lowerRightX + 1, slice.upperLeftY - 1)) {
      final Map<Ingredient, Integer> toAdd = Maps.newHashMap();
      toAdd.putAll(mapLeft);
      addMap(toAdd, mapDown);
      final int curValue = toAdd.getOrDefault(pizza[slice.lowerRightX + 1][slice.upperLeftY - 1], 0) + 1;
      toAdd.put(pizza[slice.lowerRightX + 1][slice.upperLeftY - 1], curValue);
      final double curScore = score.computeScore(curStatus, toAdd, slice, minSliceIngredient);
      if (isBetterScore(bestScore, curScore)) {
        bestScore = curScore;
        // expand
        newUpLX = slice.upperLeftX;
        newUpLY = slice.upperLeftY - 1;
        newLoRX = slice.lowerRightX - 1;
        newLoRY = slice.lowerRightY;
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
      addMap(curStatus, bestMap);
      addMap(slice.ingr2quantity, bestMap);
      return slice;
    }
    // cannot be expanded
    return null;

  }

  private List<Point> computeInitialisePoint(Ingredient[][] pizza) {
    final List<Point> allPoints = Lists.newLinkedList();
    // get minimum ingredients
    final Map<Ingredient, Integer> ing2presence = Maps.newHashMap();
    for (int i = 0; i < pizza.length; i++) {
      for (int j = 0; j < pizza[0].length; j++) {
        final int curValue = ing2presence.getOrDefault(pizza[i][j], 0) + 1;
        ing2presence.put(pizza[i][j], curValue);
      }
    }
    final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    final Ingredient mimimum[] = new Ingredient[1];
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

  private boolean isValid(Slice slice, Ingredient[][] pizza, int L) {
    // check it contains minimum number of ingredients
    final Map<Ingredient, Integer> ingr2count = Maps.newHashMap();
    final Set<Ingredient> toSatisfy = Sets.newHashSet(Ingredient.values());

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

  private boolean isBetterScore(final double bestScore, final double currentScore) {
    if (currentScore > bestScore) {
      return true;
    }
    if (currentScore == bestScore) {
      // random choice
      final Random m = new Random();
      final int nextRand = m.nextInt(2);
      return nextRand == 0;
    }
    return false;
  }

}
