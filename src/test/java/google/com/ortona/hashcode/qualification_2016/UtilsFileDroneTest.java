/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.qualification_2016;

import google.com.ortona.hashcode.UtilsFileDrone;
import org.junit.Assert;
import org.junit.Test;


public class UtilsFileDroneTest {

    private final String filePathExample = "example.in";
    private UtilsFileDrone fr = new UtilsFileDrone(filePathExample);
    // Header
    private int[] headerExample = fr.getHeader();
    private int[] actualHeaderExample = new int[]{100, 100, 3, 50, 500};

    // Example.in file

    @Test
    public void testHeaderExample() {
        testHeader(actualHeaderExample, headerExample);

    }




//

//    private char[][] dataExample = fr.getData();
//
//    private String[] actualDataExample = new String[]{"TTTTT", "TMMMT", "TTTTT"};
//
//    private final String filePathSmall = "small.in";
//    private UtilsFile fr2 = new UtilsFile(filePathSmall);
//    private int[] headerSmall = fr2.getHeader();
//    private char[][] dataSmall = fr2.getData();
//    private int[] actualHeaderSmall = new int[]{6, 7, 1, 5};
//    private String[] actualDataSmall = new String[]{"TMMMTTT", "MMMMTMM", "TTMTTMT", "TMMTMMM", "TTTTTTM", "TTTTTTM"};
//

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

    // utils

    private void testDataLineByLine(String[] actualData, char[][] data) {

        // line by line
        for (int i = 0; i < actualData.length; i++) {
            String d = fr.convertArrayOfChartToString(data[i]);
            Assert.assertEquals(d, actualData[i]);
        }

    }

    private void testHeader(int[] actualHeader, int[] header) {

        System.out.println(actualHeader);

        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
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
