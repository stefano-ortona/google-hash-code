package google.com.ortona.hashcode.qualification_2016.checks;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import google.com.ortona.hashcode.qualification_2016.model.Action;
import google.com.ortona.hashcode.qualification_2016.model.Order;
import google.com.ortona.hashcode.qualification_2016.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import google.com.ortona.hashcode.qualification_2016.model.SolutionContainer;

public class SolutionCorrectnessTest {

  ProblemContainer container;

  public void checkSolution(SolutionContainer sol) {

  }

  private void checkSolutionByOrder(List<Action> a, Order o) {
    final Map<Product, Integer> foundDeliveries = Maps.newHashMap(o.getProducts2quantity());
    // filter only actions regarding that dron
    a.stream().filter(ac -> ac.getType().equals("D") && (ac.getId() == o.getId())).forEach(ac -> {
      final int prodid = ac.getProductId();
      decreaseQuantity(foundDeliveries, prodid, ac.getQuantity());
    });
    if(foundDeliveries.isEmpty() && )

  }

  private void decreaseQuantity(Map<Product, Integer> map, int id, int quantity) {
    final Product p = map.keySet().stream().filter(el -> el.getId() == id).findFirst().get();
    map.put(p, map.get(p) - quantity);
    if (map.get(p) == 0) {
      map.remove(p);
    }
  }

  public void setContainer(ProblemContainer c) {
    this.container = c;
  }

}
