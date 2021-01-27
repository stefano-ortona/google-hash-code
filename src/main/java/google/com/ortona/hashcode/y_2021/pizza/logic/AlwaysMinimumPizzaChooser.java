package google.com.ortona.hashcode.y_2021.pizza.logic;

import google.com.ortona.hashcode.y_2021.pizza.model.Pizza;

import java.util.List;

public class AlwaysMinimumPizzaChooser extends APizzaChooser {

    @Override
    boolean shouldStop(List<Pizza> pizzaList, List<Pizza> pizzaAllocated
            , boolean hasTeam_2, boolean hasTeam_3, boolean hasTeam_4) {

        if (pizzaList.isEmpty()) return true; // EXIT

        int teamSizeMax = getTeamSizeMax(hasTeam_2, hasTeam_3, hasTeam_4);
        if (pizzaAllocated.size() == teamSizeMax) {
            return true; // EXIT
        }

        int teamSizeMin = getTeamSizeMin(hasTeam_2, hasTeam_3, hasTeam_4);

        if (pizzaList.size() < teamSizeMin) {
            return false; // OK
        }

        return pizzaAllocated.size() == teamSizeMin;
    }

}
