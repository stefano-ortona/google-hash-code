/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.qualification_2017;

import google.com.ortona.hashcode.UtilsFileStreaming;
import google.com.ortona.hashcode.qualification_2016.model.*;
import google.com.ortona.hashcode.qualification_2017.model.Endpoint;
import google.com.ortona.hashcode.qualification_2017.model.Video;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    // Videos
    @Test
    public void testVideos() {
        //50 50 80 30 110
//        endpointsActual.add(new Video(0, 50));
//        endpointsActual.add(new Video(1, 50));
//        endpointsActual.add(new Video(2, 80));
//        endpointsActual.add(new Video(3, 30));
//        endpointsActual.add(new Video(4, 110));
        testVideos(videosExample, videosActual);
    }

    // Endpoints



    // ============================ utils
    private void testHeader(int[] actualHeader, int[] header) {

        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    private void testVideos(List<Video> videos, List<Video> actual) {

        for (int i = 0; i < videos.size(); i++) {
            Assert.assertEquals(videos.get(i), actual.get(i));
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
