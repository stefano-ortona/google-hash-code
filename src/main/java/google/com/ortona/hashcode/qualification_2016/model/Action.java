package google.com.ortona.hashcode.qualification_2016.model;

public class Action {
  private Drone drone;
  private String type; // L or D
  private int productId;
  private int id; // can be warehouse or order
  private int quantity;
  private Order o;

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setOrder(Order o) {
    this.o = o;
  }

  public Order getOrder() {
    return this.o;
  }

  @Override
  public String toString() {
    return drone.getId() + " " + type + " " + id + " " + productId + " " + quantity;
  }

}
