package google.com.ortona.hashcode.qualification_2016.model;

public class Product {
  int id;
  int weight;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Product other = (Product) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
