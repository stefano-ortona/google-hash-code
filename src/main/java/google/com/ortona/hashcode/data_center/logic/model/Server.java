package google.com.ortona.hashcode.data_center.logic.model;

public class Server {

  private int size;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + capacity;
    result = (prime * result) + id;
    result = (prime * result) + ((initialSlot == null) ? 0 : initialSlot.hashCode());
    result = (prime * result) + pool;
    result = (prime * result) + size;
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
    final Server other = (Server) obj;
    if (capacity != other.capacity) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (initialSlot == null) {
      if (other.initialSlot != null) {
        return false;
      }
    } else if (!initialSlot.equals(other.initialSlot)) {
      return false;
    }
    if (pool != other.pool) {
      return false;
    }
    if (size != other.size) {
      return false;
    }
    return true;
  }

  private int capacity;
  int id;
  private int pool;
  private Slot initialSlot;

  public int getPool() {
    return pool;
  }

  public void setPool(int pool) {
    this.pool = pool;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getSize() {
    return size;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Slot getInitialSlot() {
    return initialSlot;
  }

  public void setInitialSlot(Slot initialSlot) {
    this.initialSlot = initialSlot;
  }

  public Server(int id, int size, int capacity) {
    this.id = id;
    this.capacity = capacity;
    this.size = size;
  }

  @Override
  public String toString() {
    return id + "_[" + this.capacity + "," + this.size + "]";
  }

}
