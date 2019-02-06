package google.com.ortona.hashcode;


import google.com.ortona.hashcode.qualification_2017.model.Endpoint;
import google.com.ortona.hashcode.qualification_2017.model.ProblemContainer;
import google.com.ortona.hashcode.qualification_2017.model.Request;
import google.com.ortona.hashcode.qualification_2017.model.Video;
import google.com.ortona.hashcode.qualification_2017.model.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsFileStreaming {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private List<Video> videos;
    private Map<Integer, Video> id2Video;
    private List<Endpoint> endpoints;
    private Map<Integer, Endpoint> id2Endpoint;
    private List<Request> requests;
    private ProblemContainer problemContainer;

    // 2. generate setters and getters for header and data
    public void setHeader(int[] header) {
        this.header = header;
    }

    public int[] getHeader() {
        return header;
    }

    public int getVideosAmount() { return this.getHeader()[0];}

    public int getEndpointsAmount() { return this.getHeader()[1];}

    public int getRequestsAmount() { return this.getHeader()[2];}

    public int getCachesAmount() { return this.getHeader()[3];}

    public int getCacheSize() { return this.getHeader()[5];}


    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }


    // 5 videos, 2 endpoints, 4 request descriptions, 3 caches 100MB each.

    public void createVideos() {
        String[] file = this.getFile();
        String dataVideos = file[1];
        String[] split = splitString(dataVideos, " ");

        int[] converted = convertArrayOfStringToArrayOfInt(split);
        List<Video> videos = convertArrayOfStringToListOfVideo(converted);

        this.setVideos(videos);
    }

    public List<Video> convertArrayOfStringToListOfVideo(int[] dataRaw) {

        List<Video> result = new ArrayList<>();
        id2Video = new HashMap<>();

        for (int i = 0; i < dataRaw.length; i++) {
            Video v =new Video(i, dataRaw[i]);
            id2Video.put(i, v);
            result.add(v);
        }
        return result;
    }

    public void createEndpoints() {
        int endpointAmount = this.getEndpointsAmount();
        int index = 1; // row index of the first endpoint - 1

        id2Endpoint = new HashMap<>();
        endpoints =new ArrayList<>();

        for (int i = 0; i < endpointAmount; i++) {

            Endpoint endpoint = new Endpoint();
            endpoint.setId(i);

            index++;
            String[] split = splitString(this.file[index], " ");
            int[] converted = convertArrayOfStringToArrayOfInt(split);

            endpoint.setDataCenterLatency(converted[0]);
            int connectedCaches = converted[1];

            Map<Cache, Integer> cache2latency = new HashMap<>();

            for(int j = 0 ; j < connectedCaches; j++ ) {

                index++;
                String[] details = splitString(this.file[index], " ");
                int[] detailsConverted = convertArrayOfStringToArrayOfInt(details);

                int cacheId = detailsConverted[0];
                int latency = detailsConverted[1];

                Cache cache = new Cache();
                cache.setId(cacheId);

                cache2latency.put(cache, latency);

            }

            System.out.println(cache2latency.size());
            System.out.println(cache2latency.size());
            System.out.println(cache2latency.size());
            System.out.println(cache2latency.size());
            System.out.println(cache2latency.size());
            System.out.println(cache2latency.size());

            endpoint.setCache2latency(cache2latency);

            endpoints.add(endpoint);
            id2Endpoint.put(endpoint.getId(), endpoint);

        }

        this.setEndpoints(endpoints);

    }

    public void createRequests() {

    }

    public void createProblemContainer() {

    }




    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/qualification_2017/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFileStreaming(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            //LOGGER.info("Header creation: start");
            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Unavailable slot creation: start");
            createVideos();
            //LOGGER.info("Unavailable slot creation: done");

            //LOGGER.info("Server creation: start");
            createEndpoints();
            //LOGGER.info("Server creation: done");


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Utils
    public void readFile(String filepath) throws IOException {

        String line;
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filepath));

        while ((line = in.readLine()) != null) {
            list.add(line);  //Add line to file
        }

        //Cast List to Array of Array
        String[] stringArr = list.toArray(new String[0]);

        this.setFile(stringArr);

    }

    public String getFirstLineOfFile() {
        return this.file[0];
    }

    public String[] splitString(String string, String separator) {
        return string.split(separator);
    }

    public String[] cloneArrayOfString(String[] source, Integer start, Integer to) {
        return Arrays.copyOfRange(source, start, to);
    }

    public char[] convertStringToArrayOfChar(String string) {
        return string.toCharArray();
    }

    public char[][] cconvertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

        char[][] result = new char[dataRaw.length][dataRaw[0].length()];

        for (int i = 0; i < dataRaw.length; i++) {
            result[i] = convertStringToArrayOfChar(dataRaw[i]);
        }
        return result;
    }

    public String convertArrayOfChartToString(char[] a) {
        return new String(a);
    }

    public int[] convertArrayOfStringToArrayOfInt(String[] strings) {
        return Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();

    }

//    public List<Server> convertArrayOfStringToListOfServers(String[] dataRaw) {
//
//
//        List<Server> result = new ArrayList<>();
//
//        for (int i = 0; i < dataRaw.length; i++) {
//            result.add(convertStringToServer(dataRaw[i], i));
//        }
//        return result;
//    }
//
//    public Server convertStringToServer(String string, int index) {
//
//        String[] split = splitString(string, " ");
//        return new Server(index, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
//    }
//
//    public List<Slot> convertArrayOfStringToListOfSlot(String[] dataRaw) {
//
//        List<Slot> result = new ArrayList<>();
//
//        for (int i = 0; i < dataRaw.length; i++) {
//            result.add(convertStringToSlot(dataRaw[i]));
//        }
//        return result;
//    }
//
//    public Slot convertStringToSlot(String string) {
//
//        String[] split = splitString(string, " ");
//        return new Slot(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
//    }

}
