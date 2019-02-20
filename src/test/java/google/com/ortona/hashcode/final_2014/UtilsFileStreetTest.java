/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.final_2014;

import google.com.ortona.hashcode.UtilsFileStreet;
import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import org.junit.Assert;
import org.junit.Test;


public class UtilsFileStreetTest {

    private final String filePathExample = "example.in";
    private UtilsFileStreet fr = new UtilsFileStreet(filePathExample);
    // Header
    private int[] headerExample = fr.getHeader();
    private int[] actualHeaderExample = new int[]{3, 2, 3000, 2, 0};
    // Cars


    // Problem Container
    //private ProblemContainer problemContainer = fr.getProblemContainer();


    // Example.in file

    // Header
    @Test
    public void testHeaderExample() {

        testHeader(actualHeaderExample, headerExample);
        Assert.assertEquals(fr.getJunctionAmount(), actualHeaderExample[0]);
        Assert.assertEquals(fr.getStreetAmount(), actualHeaderExample[1]);
        Assert.assertEquals(fr.getAllowedTime(), actualHeaderExample[2]);
        Assert.assertEquals(fr.getCarAmount(), actualHeaderExample[3]);
        Assert.assertEquals(fr.getInitialJunction(), actualHeaderExample[4]);
    }

    @Test
    public void testCars(){
        Assert.assertEquals(fr.getCarAmount(), fr.getCars().size());
        for (int i = 0; i < fr.getCars().size(); i++) {
            Car c = fr.getCars().get(i);
            Junction  j =new Junction();
            j.setId(fr.getInitialJunction());
            Assert.assertEquals(i, c.getId());
            Assert.assertEquals(c.getCurrent(), j);

        }
    }

    @Test
    public void testJunctions(){
        Assert.assertEquals(fr.getJunctionAmount(), fr.getJunctions().size());
        for (int i = 0; i < fr.getJunctions().size(); i++) {
            Assert.assertEquals(i, fr.getJunctions().get(i).getId());
        }
    }

    @Test
    public void testStreets(){
        Assert.assertEquals(fr.getStreetAmount(), fr.getStreets().size());
//        for (int i = 0; i < fr.getJunctions().size(); i++) {
//            Assert.assertEquals(i, fr.getJunctions().get(i).getId());
//        }
    }

    // ============================ utils
    private void testHeader(int[] actualHeader, int[] header) {

        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
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
