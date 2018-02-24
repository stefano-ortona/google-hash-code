/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import google.com.ortona.hashcode.data_center.logic.model.*;

/**
 * Unit test
 */
public class UtilsFileServerTest {

    private final String filePathExample = "dc.in";
    private UtilsFileServer fr = new UtilsFileServer(filePathExample);
    private Datacenter datacenter = fr.getDatacenter();
    private int[] actualHeader = new int[]{16, 100, 80, 45, 625};
    private int[] header = fr.getHeader();
    private List<Slot> unavailableSlots = fr.getUnavailableSlots();
    private List<Server> servers = fr.getServers();


//    private char[][] dataExample = fr.getData();
//    private int[] actualHeaderExample = new int[]{3, 5, 1, 6};
//    private String[] actualDataExample = new String[]{"TTTTT", "TMMMT", "TTTTT"};
//
//    private final String filePathSmall = "small.in";
//    private UtilsFile fr2 = new UtilsFile(filePathSmall);
//    private int[] headerSmall = fr2.getHeader();
//    private char[][] dataSmall = fr2.getData();
//    private int[] actualHeaderSmall = new int[]{6, 7, 1, 5};
//    private String[] actualDataSmall = new String[]{"TMMMTTT", "MMMMTMM", "TTMTTMT", "TMMTMMM", "TTTTTTM", "TTTTTTM"};

    @Test
    public void testHeaderServer() {
        testHeader(header, actualHeader);

    }

    private void testHeader(int[] actualHeader, int[] header) {
        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    @Test
    public void testUnavailableSlotsSizeServer() {
        int unavailableSlotSize = header[2];
        Assert.assertEquals(unavailableSlots.size(), unavailableSlotSize);
    }


    @Test
    public void testServersSizeServer() {
        int serversSize = header[4];
        Assert.assertEquals(servers.size(), serversSize);
    }

    @Test
    public void testFileIsAllRead() {
        int serversSize = fr.getFile().length;
        Assert.assertEquals(serversSize, unavailableSlots.size() + servers.size() + 1 );
    }
    


//    @Test
//    public void testHeaderExample() {
//        testHeader(actualHeaderExample, headerExample);
//
//    }
//
//    @Test
//    public void testDataLineByLineExample() {
//        testDataLineByLine(actualDataExample, dataExample);
//    }
//
//    @Test
//    public void testDataCharByCharExample() {
//        testDataCharByChar(actualDataExample, dataExample);
//    }
//
//    // Small.in file
//
//    @Test
//    public void testHeaderSmall() {
//        testHeader(actualHeaderSmall, headerSmall);
//    }
//
//    @Test
//    public void testDataLineByLineSmall() {
//        testDataLineByLine(actualDataSmall, dataSmall);
//    }
//
//    @Test
//    public void testDataCharByCharSmall() {
//        testDataCharByChar(actualDataSmall, dataSmall);
//    }
//
//    // utils
//
//    private void testDataLineByLine(String[] actualData, char[][] data) {
//
//        // line by line
//        for (int i = 0; i < actualData.length; i++) {
//            String d = fr.convertArrayOfChartToString(data[i]);
//            Assert.assertEquals(d, actualData[i]);
//        }
//
//    }
//
//    private void testHeader(int[] actualHeader, int[] header) {
//        for (int i = 0; i < actualHeader.length; i++) {
//            Assert.assertEquals(header[i], actualHeader[i]);
//        }
//
//    }
//
//    private void testDataCharByChar(String[] actualData, char[][] data) {
//
//        for (int i = 0; i < actualData.length; i++) {
//            testCharByChartLine(actualData[i], data[i]);
//        }
//
//    }
//
//    private void testCharByChartLine(String line, char[] data) {
//
//        for (int i = 0; i < line.length(); i++) {
//            Assert.assertEquals(line.charAt(i), data[i]);
//        }
//
//    }
}
