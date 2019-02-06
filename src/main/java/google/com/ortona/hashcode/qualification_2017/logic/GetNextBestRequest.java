package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Request;

public class GetNextBestRequest {

    public Map<Request, Cache> getNextRequest(List<Request> requests) {

        Map<Request, Cache> result = new HashMap<>();

        int scoreBest = 0;

        requests.forEach(request -> request.getE().getCache2latency().entrySet()
                .stream()
                .filter(cacheIntegerEntry -> isCacheLargeEnough(request, cacheIntegerEntry))
                .forEach(cacheIntegerEntry -> {
                    Cache cache = cacheIntegerEntry.getKey();
                    int score = calculateScore(request.getQuantity(), request.getE().getDataCenterLatency(), cacheIntegerEntry.getValue());
                    if (score > scoreBest) {
                        updateResult(result, request, cache);
                    }
                }));

        return result.isEmpty() ? null : result;
    }


    /*
     * Internal methods
     */

    private boolean isCacheLargeEnough(Request request, Map.Entry<Cache, Integer> cache2LatencyEntry) {
        return cache2LatencyEntry.getKey().getSize() <= request.getV().getSize();
    }

    private int calculateScore(int quantityRequest, int endpointLatency, int cacheLatency) {
        return quantityRequest * (endpointLatency - cacheLatency);
    }

    private void updateResult(Map<Request, Cache> result, Request request, Cache cache) {
        result.clear();
        result.put(request, cache);
    }

}
