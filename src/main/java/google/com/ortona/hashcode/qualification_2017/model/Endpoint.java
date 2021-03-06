package google.com.ortona.hashcode.qualification_2017.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public String toString() {
        String cache2latency = "";
        for (final Map.Entry<Cache, Integer> entry : this.cache2latency.entrySet()) {
            cache2latency += " " + entry.getKey().getId() + " " + entry.getValue();
        }
        return this.id + " " + cache2latency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endpoint endpoint = (Endpoint) o;
        return id == endpoint.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
