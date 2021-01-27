package google.com.ortona.hashcode.y_2021.pizza.model;

import java.util.ArrayList;
import java.util.List;

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
        return 0;
    }

    @Override
    public String toString() {
        String string = "";
        string += pizzaList.size();
        for (final Pizza pa : getPizzaList()) {
            string += "\n" + pa.getId();
        }
        return string;
    }
}
