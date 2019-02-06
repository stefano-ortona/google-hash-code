package google.com.ortona.hashcode.qualification_2017.model;

import java.util.HashMap;
import java.util.Map;

public class Endpoint {
	int id;
	int dataCenterLatency;
	Map<Cache, Integer> cache2latency = new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDataCenterLatency() {
		return dataCenterLatency;
	}

	public void setDataCenterLatency(int dataCenterLatency) {
		this.dataCenterLatency = dataCenterLatency;
	}

	public Map<Cache, Integer> getCache2latency() {
		return cache2latency;
	}

	public void setCache2latency(Map<Cache, Integer> cache2latency) {
		this.cache2latency = cache2latency;
	}

	@Override
	public String toString() {
		String cache2latency = "";
		for (final Map.Entry<Cache, Integer> entry : this.cache2latency.entrySet()) {
			cache2latency += " " + entry.getKey().getId() + " " + entry.getValue();
		}
		return this.id + " " + cache2latency;
	}
}
