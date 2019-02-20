package google.com.ortona.hashcode.qualification_2017.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import google.com.ortona.hashcode.qualification_2017.model.Cache;
import google.com.ortona.hashcode.qualification_2017.model.Endpoint;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.Video;

public class ProblemReader2 {

  final String resPath = "src/main/resources/google/com/ortona/hashcode/qualification_2017/";

  public ProblemContainer readProblem(String fileLocation) throws IOException {
    final BufferedReader reader = new BufferedReader(new FileReader(new File(resPath + fileLocation)));
    final String firstLine[] = reader.readLine().split(" ");
    final int endNumb = Integer.parseInt(firstLine[1]);
    final int reqNumber = Integer.parseInt(firstLine[2]);
    final int cacheSize = Integer.parseInt(firstLine[4]);
    final String secLine[] = reader.readLine().split(" ");
    final List<Video> allV = new ArrayList<>();
    for (int i = 0; i < secLine.length; i++) {
      allV.add(new Video(i, Integer.parseInt(secLine[i])));
    }
    final List<Cache> allCaches = new ArrayList<>();
    final List<Endpoint> allEnd = new ArrayList<>();
    for (int i = 0; i < endNumb; i++) {
      final String[] firstL = reader.readLine().split(" ");
      final Endpoint en = new Endpoint();
      en.setId(i);
      en.setDataCenterLatency(Integer.parseInt(firstL[0]));
      final int lines = Integer.parseInt(firstL[1]);
      final Map<Cache, Integer> cac2lat = new HashMap<Cache, Integer>();
      for (int j = 0; j < lines; j++) {
        final String[] line = reader.readLine().split(" ");
        final int cacheId = Integer.parseInt(line[0]);
        final Cache c = getCacheFromList(allCaches, cacheId, cacheSize);
        cac2lat.put(c, Integer.parseInt(line[1]));
      }
      en.setCache2latency(cac2lat);
      allEnd.add(en);
    }
    final List<Request> allReq = new ArrayList<>();
    for (int i = 0; i < reqNumber; i++) {
      final String[] line = reader.readLine().split(" ");
      final int videoId = Integer.parseInt(line[0]);
      final int enId = Integer.parseInt(line[1]);
      final int quantity = Integer.parseInt(line[2]);
      final Request req = new Request();
      req.setId(i);
      req.setQuantity(quantity);
      req.setV(getVideoFromList(allV, videoId));
      req.setE(getEndpointFromList(allEnd, enId));
      allReq.add(req);
    }
    reader.close();
    final ProblemContainer con = new ProblemContainer();
    con.setRequest(allReq);
    return con;
  }

  private Video getVideoFromList(List<Video> video, int videoId) {
    return video.stream().filter(v -> v.getId() == videoId).findFirst().get();
  }

  private Endpoint getEndpointFromList(List<Endpoint> endpoint, int id) {
    return endpoint.stream().filter(e -> e.getId() == id).findFirst().get();
  }

  private Cache getCacheFromList(List<Cache> caches, int cacheId, int cacheCapacity) {
    final Optional<Cache> foundCache = caches.stream().filter(c -> c.getId() == cacheId).findFirst();
    if (foundCache.isPresent()) {
      return foundCache.get();
    }
    final Cache c = new Cache();
    c.setId(cacheId);
    c.setSize(cacheCapacity);
    caches.add(c);
    return c;
  }

}
