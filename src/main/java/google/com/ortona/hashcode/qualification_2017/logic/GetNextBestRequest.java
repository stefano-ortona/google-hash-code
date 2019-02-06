package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Pair;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.Video;

public class GetNextBestRequest {

    public Map<Request, Cache> getNextRequest(List<Request> requests, AtomicInteger curScore) {

        final Map<Request, Cache> result = new HashMap<>();

        final AtomicInteger scoreBest = new AtomicInteger(0);

        final Iterator<Request> requestIt = requests.iterator();

        while (requestIt.hasNext()) {
            final Request request = requestIt.next();

            final Pair<Cache, Integer> currentResult = new Pair<>();

            request.getE().getCache2latency().entrySet().stream()
                    .filter(cacheIntegerEntry -> isCacheLargeEnough(request, cacheIntegerEntry))
                    .forEach(cacheIntegerEntry -> {
                        final Cache cache = cacheIntegerEntry.getKey();
                        final int score = calculateScore(request.getQuantity(), request.getE().getDataCenterLatency(),
                                cacheIntegerEntry.getValue());
                        if ((currentResult.getValue() == null) || (score > currentResult.getValue())) {
                            currentResult.setKey(cache);
                            currentResult.setValue(score);
                        }
                    });

            if ((currentResult.getKey() != null) && !currentResult.getKey().getVideos().contains(request.getV())) {

                if (!isAlreadyInOtherEfficientCache(request.getV(), currentResult.getKey(),
                        request.getE().getCache2latency())) {

                    if ((currentResult.getValue() != null) && (currentResult.getValue() > scoreBest.get())) {
                        updateResult(result, request, currentResult.getKey());
                        scoreBest.set(currentResult.getValue());
                    }
                }
            } else {
                // if request already satisfied, need to increment the score
                if ((currentResult.getKey() != null) && currentResult.getKey().getVideos().contains(request.getV())) {
                    curScore.addAndGet(calculateScore(request.getQuantity(), request.getE().getDataCenterLatency(),
                            request.getE().getCache2latency().get(currentResult.getKey())));
                }
                // request is already satisfied or cannot be satisfied anymore
                requestIt.remove();
            }

        }

        return result.isEmpty() ? null : result; // TODO: 2019-02-06 attenzione a oggetto fake
    }


    public void orderedRequestList(List<Request> requestList) {

        requestList.forEach(request -> calculateBestRequestScore(request, request.getE().getCache2latency()));

        requestList.sort((o1, o2) -> o2.getScore() - o1.getScore());
    }

    private int calculateBestRequestScore(Request request, Map<Cache, Integer> cache2latency) {
        int bestScore = 0;

        for (Map.Entry<Cache, Integer> cacheIntegerEntry : cache2latency.entrySet()) {
            int curScore = calculateScore(request.getQuantity()
                    , request.getE().getDataCenterLatency()
                    , cacheIntegerEntry.getKey().getAvailableCapacity());
            if (cacheIntegerEntry.getKey().getAvailableCapacity() >= request.getV().getSize()) {
                bestScore = Math.max(bestScore, curScore);
                request.setCacheDesignated(cacheIntegerEntry.getKey());
                request.setScore(bestScore);
            }
        }

        return bestScore;
    }






    /*
     * Internal methods
     */

    private boolean isCacheLargeEnough(Request request, Map.Entry<Cache, Integer> cache2LatencyEntry) {
        return cache2LatencyEntry.getKey().getAvailableCapacity() >= request.getV().getSize();
    }

    private int calculateScore(int quantityRequest, int endpointLatency, int cacheLatency) {
        return quantityRequest * (endpointLatency - cacheLatency);
    }

    private boolean isAlreadyInOtherEfficientCache(Video video, Cache cacheBest, Map<Cache, Integer> cache2latency) {
        return false;
        /*
         * Optional<Map.Entry<Cache, Integer>> otherCache2LatencyWithVideo =
         * cache2latency.entrySet() .stream() .filter(new Predicate<Map.Entry<Cache,
         * Integer>>() {
         *
         * @Override public boolean test(Map.Entry<Cache, Integer> cacheIntegerEntry) {
         * return cacheIntegerEntry.getKey().getVideos().contains(video) &&
         * !cacheIntegerEntry.getKey().equals(cacheBest); } }) .findFirst();
         */
    }

    private void updateResult(Map<Request, Cache> result, Request request, Cache cache) {
        result.clear();
        result.put(request, cache);
    }

}
