package google.com.ortona.hashcode.y_2021.pizza.logic;

import google.com.ortona.hashcode.y_2021.pizza.model.Pizza;
import google.com.ortona.hashcode.y_2021.pizza.model.TeamAllocation;

import java.util.*;

public abstract class APizzaChooser {

    public TeamAllocation choose(Map<Integer, Integer> teamMap, List<Pizza> pizzaList) {

        boolean hasTeam_2 = teamMap.get(2) > 0;
        boolean hasTeam_3 = teamMap.get(3) > 0;
        boolean hasTeam_4 = teamMap.get(4) > 0;

        int pizzaSize = pizzaList.size();

        if (pizzaSize <= 1) return null;
        if (!hasTeam_2 && pizzaSize <= 2) return null;
        if (!hasTeam_2 && !hasTeam_3 && pizzaSize <= 3) return null;
        if (!hasTeam_2 && !hasTeam_3 && !hasTeam_4) return null;

        List<Pizza> pizzaAllocated = new ArrayList<>();

        if (isLastStep(teamMap, pizzaList.size())) {
            System.out.println(pizzaList);
        }

        while (true) {
            Pizza pizza = getNextPizza(pizzaList, pizzaAllocated, hasTeam_2, hasTeam_3, hasTeam_4);
            if (pizza == null) break; // EXIT
            pizzaList.remove(pizza);
            pizzaAllocated.add(pizza);
        }

        return new TeamAllocation(pizzaAllocated);
    }

    private boolean isLastStep(Map<Integer, Integer> teamMap, int pizzaSize) {
        if (teamMap.get(2) + teamMap.get(3) + teamMap.get(4) <= 1) return true;
        int teamSizeMin = getTeamSizeMin(teamMap);
        int remain = pizzaSize - teamSizeMin;

        Map<Integer, Integer> tmpTeamMap = new HashMap<>();
        tmpTeamMap.putAll(teamMap);
        tmpTeamMap.put(teamSizeMin, tmpTeamMap.get(teamSizeMin) - 1);
        int tmpTeamSizeMin = getTeamSizeMin(teamMap);

        return remain >= tmpTeamSizeMin;
    }

    private Pizza getNextPizza(List<Pizza> pizzaList, List<Pizza> pizzaAllocated, boolean hasTeam_2, boolean hasTeam_3, boolean hasTeam_4) {

        if (pizzaList.isEmpty()) return null; // EXIT

        int teamSizeMax = getTeamSizeMax(hasTeam_2, hasTeam_3, hasTeam_4);
        if (pizzaAllocated.size() == teamSizeMax) {
            return null; // EXIT
        }

        if (shouldStop(pizzaList, pizzaAllocated, hasTeam_2, hasTeam_3, hasTeam_4)) return null; // EXIT

        Set<String> ingredientSet = new HashSet<>();
        for (Pizza pizza : pizzaAllocated) {
            ingredientSet.addAll(pizza.getIngredientList());
        }

        long ingredientAdded = -1;
        Pizza pizzaAdded = null;

        for (Pizza pizza : pizzaList) {
            long delta = pizza.getIngredientList().stream()
                    .filter((i) -> !ingredientSet.contains(i))
                    .count();

            if (delta > ingredientAdded) {
                pizzaAdded = pizza;
                ingredientAdded = delta;
            }
            if (delta == ingredientAdded && pizza.getIngredientList().size() < pizzaAdded.getIngredientList().size()) {
                pizzaAdded = pizza;
            }
        }

        return pizzaAdded;
    }

    abstract boolean shouldStop(List<Pizza> pizzaList, List<Pizza> pizzaAllocated, boolean hasTeam_2, boolean hasTeam_3, boolean hasTeam_4);


    private int getTeamSizeMin(Map<Integer, Integer> teamMap) {
        return getTeamSizeMin(teamMap.get(2) > 0
                , teamMap.get(3) > 0
                , teamMap.get(4) > 0);
    }

    protected int getTeamSizeMin(boolean hasTeam_2, boolean hasTeam_3, boolean hasTeam_4) {
        if (hasTeam_2) return 2;
        if (hasTeam_3) return 3;
        return 4;
    }

    protected int getTeamSizeMax(boolean hasTeam_2, boolean hasTeam_3, boolean hasTeam_4) {
        if (hasTeam_4) return 4;
        if (hasTeam_3) return 3;
        return 2;
    }

}
