package google.com.ortona.hashcode.y_2021.pizza.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamAllocation {

    private List<Pizza> pizzaList = new ArrayList<>();

    public TeamAllocation(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    /*
     * Logic
     */

    public int getScore() {
        Set<String> ingredientSet = new HashSet<>();
        for (Pizza pizza : getPizzaList()) {
            ingredientSet.addAll(pizza.getIngredientList());
        }
        return (int) Math.pow(ingredientSet.size(), 2);
    }

    @Override
    public String toString() {
        String string = "";
        string += pizzaList.size();
        for (final Pizza pa : getPizzaList()) {
            string += " " + pa.getId();
        }
        return string;
    }
}
