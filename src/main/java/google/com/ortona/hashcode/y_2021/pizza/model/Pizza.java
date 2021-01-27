package google.com.ortona.hashcode.y_2021.pizza.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pizza {

    private int id;

    private List<String> ingredientList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
