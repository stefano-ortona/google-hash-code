package google.com.ortona.hashcode.data_center.logic.model;

public class Server {

	private int size;
	private int capacity;
	int id;
	private int pool;
	private Slot initialSlot;
	
	public int getId() {
		return id;
	}

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
		return id + "_[" + this.size + "," + this.capacity + "]";
	}

}
