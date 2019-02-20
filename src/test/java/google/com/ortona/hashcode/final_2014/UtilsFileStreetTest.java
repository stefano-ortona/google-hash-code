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
    public void testCars() {
        Assert.assertEquals(fr.getCarAmount(), fr.getCars().size());
        for (int i = 0; i < fr.getCars().size(); i++) {
            Car c = fr.getCars().get(i);
            Junction j = new Junction();
            j.setId(fr.getInitialJunction());
            Assert.assertEquals(i, c.getId());
            Assert.assertEquals(c.getCurrent(), j);
        }
    }

    @Test
    public void testJunctions() {

        junctionsActual = new ArrayList<>();

        Junction j0 = new Junction();
        j0.setId(0);
        j0.setLat(48.8582);
        j0.setLng(2.2945);
        junctionsActual.add(j0);

        Junction j1 = new Junction();
        j1.setId(1);
        j1.setLat(50.0);
        j1.setLng(3.09);
        junctionsActual.add(j1);

        Junction j2 = new Junction();
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
    public void testStreets() {
        streetsActual = new ArrayList<>();

        //0 1 1 30 250
        Street s0 = new Street();
        s0.setStart(fr.getId2junction().get(0));
        s0.setEnd(fr.getId2junction().get(1));
        s0.setBidirectional(false);
        s0.setTimeCost(30);
        s0.setLength(250);
        streetsActual.add(s0);

        //1 2 2 45 200
        Street s1 = new Street();
        s1.setStart(fr.getId2junction().get(1));
        s1.setEnd(fr.getId2junction().get(2));
        s1.setBidirectional(true);
        s1.setTimeCost(45);
        s1.setLength(200);
        streetsActual.add(s1);

        Assert.assertEquals(fr.getStreetAmount(), fr.getStreets().size());
        for (int i = 0; i < fr.getStreets().size(); i++) {
            Street actual = streetsActual.get(i);
            Street street = streetsExample.get(i);
            Assert.assertEquals(actual.getStart(), street.getStart());
            Assert.assertEquals(actual.getEnd(), street.getEnd());
            Assert.assertEquals(actual.isBidirectional(), street.isBidirectional());
            Assert.assertEquals(actual.getTimeCost(), street.getTimeCost(), 0.001);
            Assert.assertEquals(actual.getLength(), street.getLength(), 0.001);
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
