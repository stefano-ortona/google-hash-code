package google.com.ortona.hashcode.qualification_2017.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AtomicDouble;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.SolutionContainer;

public class ProblemSolver {

	Logger LOG = LoggerFactory.getLogger(getClass());

	GetNextBestRequest bRequest = new GetNextBestRequest();

	public SolutionContainer process(ProblemContainer problem) {
		final List<Request> allRequests = problem.getRequest();
		final AtomicInteger totScore = new AtomicInteger();
		final Set<Cache> usedCaches = new HashSet<>();
		final AtomicDouble allReqSize = new AtomicDouble(0);
		allRequests.forEach(r -> allReqSize.addAndGet(r.getQuantity()));
		while (!allRequests.isEmpty()) {
			// get next best request
			final Map<Request, Cache> best = bRequest.getNextRequest(allRequests, totScore);
			if ((best == null) || best.isEmpty()) {
				LOG.info("Finished processing as there are no more caches available!");
				break;
			}
			final Request r = best.keySet().iterator().next();
			final Cache c = best.values().iterator().next();
			final int curScore = computeScore(r, c);
			allRequests.remove(r);
			LOG.info("Improved request '{}' for video '{}' with score '{}'. Remaining {} requests", r.getId(), r.getV(),
					curScore, allRequests.size());
			totScore.addAndGet(curScore);
			// add to the cache
			c.addVideo(r.getV());
			// add used cache
			usedCaches.add(c);
		}
		final SolutionContainer sContainer = new SolutionContainer();
		sContainer.setScore((totScore.get() / allReqSize.get()) * 1000);
		sContainer.setCaches(usedCaches);
		return sContainer;
	}

	private int computeScore(Request r, Cache c) {
		return r.getQuantity() * (r.getE().getDataCenterLatency() - r.getE().getCache2latency().get(c));
	}

}
