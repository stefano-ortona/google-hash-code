/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.qualification_2017;

import google.com.ortona.hashcode.UtilsFileStreaming;
import google.com.ortona.hashcode.qualification_2017.model.Endpoint;
import google.com.ortona.hashcode.qualification_2017.model.Video;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UtilsFileStreamingTest {

    private final String filePathExample = "example.in";
    private UtilsFileStreaming fr = new UtilsFileStreaming(filePathExample);
    // Header
    private int[] headerExample = fr.getHeader();
    private int[] actualHeaderExample = new int[]{5, 2, 4, 3, 100};
    // Videos
    private List<Video> videosExample = fr.getVideos();
    private List<Video> videosActual = new ArrayList<>();
    // Endpoints
    private List<Endpoint> endpointsExample = fr.getEndpoints();
    private List<Endpoint> endpointsActual = new ArrayList<>();


    // Problem Container
    //private ProblemContainer problemContainer = fr.getPro();


    // Example.in file

    // Header

    // 5 2 4 3 100
    // 5 videos, 2 endpoints, 4 request descriptions, 3 caches 100MB each.

    @Test
    public void testHeaderExample() {
        testHeader(actualHeaderExample, headerExample);
    }

    @Test
    public void testVideoAmount() {
        Assert.assertEquals(headerExample[0], actualHeaderExample[0]);
    }

    @Test
    public void testEndpointAmount() {
        Assert.assertEquals(headerExample[1], actualHeaderExample[1]);
    }

    @Test
    public void testRequestsAmount() {
        Assert.assertEquals(headerExample[2], actualHeaderExample[2]);
    }


    @Test
    public void testCacheAmount() {
        Assert.assertEquals(headerExample[3], actualHeaderExample[3]);
    }

    @Test
    public void testCacheSize() {
        Assert.assertEquals(headerExample[4], actualHeaderExample[4]);
    }

    // Endpoint
    @Test
    public void testEndpoint() {
        /*
        1000 3
        0 100
        2 200
        1 300
        */
        Endpoint e1 = new Endpoint();
        e1.setId(0);
        e1.setDataCenterLatency(1000);
        endpointsActual.add(e1);

        /*
        500 0
         */
        Endpoint e2 = new Endpoint();
        e2.setId(0);
        e2.setDataCenterLatency(1000);
        endpointsActual.add(e2);


        testEnd(endpointsExample, endpointsActual);
    }

    // Endpoints


    // ============================ utils
    private void testHeader(int[] actualHeader, int[] header) {

        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    private void testEnd(List<Endpoint> endpoints, List<Endpoint> actual) {

        for (int i = 0; i < endpoints.size(); i++) {
            Assert.assertEquals(endpoints.get(i), actual.get(i));
        }
    }


    private void testDataLineByLine(String[] actualData, char[][] data) {

        // line by line
        for (int i = 0; i < actualData.length; i++) {
            String d = fr.convertArrayOfChartToString(data[i]);
            Assert.assertEquals(d, actualData[i]);
        }

    }

    private void testDataCharByChar(String[] actualData, char[][] data) {

        for (int i = 0; i < actualData.length; i++) {
            testCharByChartLine(actualData[i], data[i]);
        }

    }

    private void testCharByChartLine(String line, char[] data) {

        for (int i = 0; i < line.length(); i++) {
            Assert.assertEquals(line.charAt(i), data[i]);
        }

    }
}
