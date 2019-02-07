package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.Video;

public class ScoreCalculator {
	List<Request> allRequests = new ArrayList<Request>();

	public void initializeRequests(List<Request> requests) {
		this.allRequests.addAll(requests);
	}

	public double computeScore() {
		// first check all caches are sound
		checkCacheSoundness(allRequests);
		final AtomicLong score = new AtomicLong(0);
		allRequests.forEach(r -> {
			final Video v = r.getV();
			final Cache bestCache = getBestLatencyCache(r, v);
			if (bestCache != null) {
				// compute and update score
				long curScore = r.getE().getDataCenterLatency();
				curScore -= r.getE().getCache2latency().get(bestCache);
				curScore *= r.getQuantity();
				score.addAndGet(curScore);
			}
		});
		final AtomicLong dividend = new AtomicLong(0);
		allRequests.forEach(r -> dividend.addAndGet(r.getQuantity()));
		return ((score.get() + 0.) / dividend.get()) * 1000;
	}

	private void checkCacheSoundness(List<Request> requests) {
		requests.forEach(r -> {
			r.getE().getCache2latency().keySet().forEach(c -> checkSingleCache(c));
		});
	}

	private void checkSingleCache(Cache c) {
		// check all videos stored are not bigger than the cache size
		if (!c.checkSoundess()) {
			throw new IllegalArgumentException("Cache " + c + " is not sound!");
		}
	}

	private Cache getBestLatencyCache(Request r, Video v) {
		int bestScore = Integer.MAX_VALUE;
		Cache bestCache = null;
		for (final Entry<Cache, Integer> cache2lat : r.getE().getCache2latency().entrySet()) {
			if (cache2lat.getKey().getVideos().contains(v)) {
				if (cache2lat.getValue() < bestScore) {
					bestScore = cache2lat.getValue();
					bestCache = cache2lat.getKey();
				}
			}
		}
		return bestCache;
	}

}
