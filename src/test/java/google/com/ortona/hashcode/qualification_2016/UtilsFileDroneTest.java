/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.qualification_2016;

import google.com.ortona.hashcode.UtilsFileDrone;
import google.com.ortona.hashcode.qualification_2016.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UtilsFileDroneTest {

    private final String filePathExample = "example.in";
    private UtilsFileDrone fr = new UtilsFileDrone(filePathExample);
    // Header
    private int[] headerExample = fr.getHeader();
    private int[] actualHeaderExample = new int[]{100, 100, 3, 50, 500};
    // Products
    private int productsAmountExample = fr.getProductAmount();
    private int actualProductsAmountExample = 3;
    private List<Product> productsExample = fr.getProducts();
    private List<Product> actualProductsExample = new ArrayList<>();
    // Werehouses
    private int warehousesAmount = fr.getWerehousesAmount();
    private int actualWarehousesAmount = 2;

    // Example.in file
    @Test
    public void testHeaderExample() {
        testHeader(actualHeaderExample, headerExample);
    }

    @Test
    public void testProductAmountExample() {
        Assert.assertEquals(productsAmountExample, actualProductsAmountExample);
    }

    @Test
    public void testProductsExample() {

        actualProductsExample.add(new Product(0, 100));
        actualProductsExample.add(new Product(1, 5));
        actualProductsExample.add(new Product(2, 450));

        testProducts(actualProductsExample, productsExample);
    }

    @Test
    public void testWerehouseAmount() {
        Assert.assertEquals(warehousesAmount, actualWarehousesAmount);
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

        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    private void testProducts(List<Product> products, List<Product> actualProducts) {

        for (int i = 0; i < products.size(); i++) {
            Assert.assertEquals(products.get(i), actualProducts.get(i));
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
