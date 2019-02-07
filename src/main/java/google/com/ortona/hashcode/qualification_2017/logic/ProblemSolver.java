package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class ProblemSolver {

	Logger LOG = LoggerFactory.getLogger(getClass());

	GetNextBestRequest bRequest = new GetNextBestRequest();

	public SolutionContainer process(ProblemContainer problem) {
		final List<Request> allRequests = problem.getRequest();
		final Set<Cache> usedCaches = new HashSet<>();
		final ScoreCalculator scoreCalc = new ScoreCalculator();
		scoreCalc.initializeRequests(problem.getRequest());
		// order requests
		bRequest.orderedRequestList(allRequests);
		while (!allRequests.isEmpty()) {
			// get next best request
			final Request nextBest = allRequests.remove(0);
			if (nextBest == null) {
				LOG.info("Finished processing as there are no more caches available!");
				break;
			}
			final Cache c = nextBest.getCacheDesignated();
			if (c == null) {
				// request cannot be satisfied
				continue;
			}
			if (c.getAvailableCapacity() < nextBest.getV().getSize()) {
				// recompute score
				bRequest.calculateBestRequestScore(nextBest, nextBest.getE().getCache2latency());
				insertNewRequest(nextBest, allRequests);
				continue;
			}
			final int curScore = computeScore(nextBest, c);
			LOG.info("Improved request '{}' for video '{}' with score '{}'. Remaining {} requests", nextBest.getId(),
					nextBest.getV(), curScore, allRequests.size());
			// add to the cache only if not already present
			if (!c.getVideos().contains(nextBest.getV())) {
				c.addVideo(nextBest.getV());
				// add used cache
				usedCaches.add(c);
			}
		}
		final SolutionContainer sContainer = new SolutionContainer();
		sContainer.setCaches(usedCaches);
		sContainer.setScore(scoreCalc.computeScore());
		return sContainer;
	}

	private void insertNewRequest(Request r, List<Request> orderedRequests) {
		if (r.getScore() > 0) {
			for (int i = 0; i < orderedRequests.size(); i++) {
				if (orderedRequests.get(i).getScore() <= r.getScore()) {
					orderedRequests.add(i, r);
					return;
				}
			}
		}
	}

	private int computeScore(Request r, Cache c) {
		return r.getQuantity() * (r.getE().getDataCenterLatency() - r.getE().getCache2latency().get(c));
	}

}
