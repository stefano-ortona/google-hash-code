/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.final_2014;

import google.com.ortona.hashcode.UtilsFileStreet;
import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.Street;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UtilsFileStreetTest {

    static private final String filePathExample = "example.in";
    static private UtilsFileStreet fr = new UtilsFileStreet(filePathExample);
    // Header
    static private int[] headerExample = fr.getHeader();
    static private int[] actualHeaderExample = new int[]{3, 2, 3000, 2, 0};
    // Junction
    static private List<Junction> junctionsExample = fr.getJunctions();
    static private List<Junction> junctionsActual;
    // Streets
    static private List<Street> streetsExample = fr.getStreets();
    static private List<Street> streetsActual;


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

        junctionsActual = new ArrayList<>();

        Junction j0  = new Junction();
        j0.setId(0);
        j0.setLat(48.8582);
        j0.setLng(2.2945);
        junctionsActual.add(j0);

        Junction j1  = new Junction();
        j1.setId(1);
        j1.setLat(50.0);
        j1.setLng(3.09);
        junctionsActual.add(j1);

        Junction j2  = new Junction();
        j2.setId(2);
        j2.setLat(51.424242);
        j2.setLng(3.02);
        junctionsActual.add(j2);

        Assert.assertEquals(fr.getJunctionAmount(), fr.getJunctions().size());
        for (int i = 0; i < fr.getJunctions().size(); i++) {
            Assert.assertEquals(i, fr.getJunctions().get(i).getId());
            Junction actual = junctionsActual.get(i);
            Junction junction = junctionsExample.get(i);
            Assert.assertEquals(actual, junction);
            Assert.assertEquals(actual.getLat(), junction.getLat(), 0.001);
            Assert.assertEquals(actual.getLng(), junction.getLng(), 0.001);
        }
    }

    @Test
    public void testStreets(){
        streetsActual = new ArrayList<>();

//        private Junction start;
//        private Junction end;
//        private double length; // contribute to the score
//        private double timeCost;
//        private boolean isBidirectional;
//        private boolean isVisited;
//
//        Street s0 = new Street();
//        s0.setStart(new Junction(fr.getId2junction()));
//        s0.setStart(new Junction());
//
//
//        01130250 //Streetfromfirstjunctiontosecondjunction.
//        12245200 //Streetfromsecondjunctiontothirdjunction.
        Assert.assertEquals(fr.getStreetAmount(), fr.getStreets().size());
        for (int i = 0; i < fr.getStreets().size(); i++) {
            //Assert.assertEquals(i, fr.getStreets().get(i));
        }
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
