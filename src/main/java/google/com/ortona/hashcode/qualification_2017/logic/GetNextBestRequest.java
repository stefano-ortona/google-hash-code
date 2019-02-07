package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Request;

public class GetNextBestRequest {

	public void orderedRequestList(List<Request> requestList) {

		requestList.forEach(request -> calculateBestRequestScore(request, request.getE().getCache2latency()));

		requestList.sort((o1, o2) -> {
			if (Integer.MAX_VALUE <= Math.abs(o2.getScore() - o1.getScore())) {
				throw new RuntimeException("This should not happen!");
			}
			if (o1.getScore() != o2.getScore()) {
				return (int) (o2.getScore() - o1.getScore());
			}
			return o1.getQuantity() - o2.getQuantity();
		});
	}

	public long calculateBestRequestScore(Request request, Map<Cache, Integer> cache2latency) {
		long bestScore = 0;
		request.setScore(0);
		request.setCacheDesignated(null);

		for (final Map.Entry<Cache, Integer> cacheIntegerEntry : cache2latency.entrySet()) {
			if (cacheIntegerEntry.getKey().getAvailableCapacity() >= request.getV().getSize()) {
				final long curScore = calculateScore(request.getQuantity(), request.getE().getDataCenterLatency(),
						request.getE().getCache2latency().get(cacheIntegerEntry.getKey()));
				if (curScore > bestScore) {
					request.setCacheDesignated(cacheIntegerEntry.getKey());
					request.setScore(curScore);
					bestScore = curScore;
				}
			}
		}
		return bestScore;
	}

	private long calculateScore(int quantityRequest, int endpointLatency, int cacheLatency) {
		if (endpointLatency <= cacheLatency) {
			throw new RuntimeException("Cache latency cannot be bigger than endpoint latency!");
		}
		final long score = quantityRequest * (endpointLatency - cacheLatency);
		if (score < 0) {
			throw new RuntimeException("Score cannot be negative!");
		}
		return score;
	}

}
