/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode.qualification_2016;

import google.com.ortona.hashcode.UtilsFileDrone;
import google.com.ortona.hashcode.qualification_2016.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private Map<Integer, Product> id2product = fr.getId2product();
    // Warehouses
    private int warehousesAmount = fr.getWarehousesAmount();
    private int actualWarehousesAmount = 2;
    private List<Warehouse> warehouses = fr.getWarehouses();
    private List<Warehouse> actualWarehouses = new ArrayList<>();
    // Orders
    private int ordersAmount = fr.getOrdersAmount();
    private int actualOrdersAmount = 3;
    private List<Order> orders = fr.getOrders();
    private List<Order> actualOrders = new ArrayList<>();
    // Drones
    private int dronesAmount = fr.getDronesAmount();
    private int actualDronesAmount = 3;
    private int dronePayload = fr.getMaxPayload();
    private int actualDronePayload = 500;
    private List<Drone> drones = fr.getDrones();

    // Problem Container
    private ProblemContainer problemContainer = fr.getProblemContainer();


    // Example.in file

    // Header
    @Test
    public void testHeaderExample() {
        testHeader(actualHeaderExample, headerExample);
    }

    // Products

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

    // Warehouses

    @Test
    public void testWarehouseAmount() {
        Assert.assertEquals(warehousesAmount, actualWarehousesAmount);
    }

    @Test
    public void testWarehouse() {

        Warehouse w0 = new Warehouse();
        w0.setId(0);
        w0.setRow(0);
        w0.setColumn(0);
        Map<Product, Integer> w0product2quantity = new HashMap<>();
        w0product2quantity.put(id2product.get(0), 5);
        w0product2quantity.put(id2product.get(1), 1);
        w0.setProduct2quantity(w0product2quantity);
        actualWarehouses.add(w0);

        Warehouse w1 = new Warehouse();
        w1.setId(1);
        w1.setRow(5);
        w1.setColumn(5);
        Map<Product, Integer> w1product2quantity = new HashMap<>();
        w1product2quantity.put(id2product.get(1), 10);
        w1product2quantity.put(id2product.get(2), 2);
        w0.setProduct2quantity(w1product2quantity);
        actualWarehouses.add(w1);

        Assert.assertEquals(warehouses, actualWarehouses);
    }

    // Orders

    @Test
    public void testOrderAmount() {
        Assert.assertEquals(ordersAmount, actualOrdersAmount);
    }

    @Test
    public void testOrders() {

        Order o1 = new Order();
        o1.setId(0);
        o1.setRow(1);
        o1.setColumn(1);
        Map<Product, Integer> o0product2quantity = new HashMap<>();
        o0product2quantity.put(id2product.get(2), 1);
        o0product2quantity.put(id2product.get(0), 1);
        o1.setProducts2quantity(o0product2quantity);
        actualOrders.add(o1);


        Order o2 = new Order();
        o2.setId(1);
        o2.setRow(3);
        o2.setColumn(3);
        Map<Product, Integer> o2product2quantity = new HashMap<>();
        o2product2quantity.put(id2product.get(0), 1);
        o2.setProducts2quantity(o2product2quantity);
        actualOrders.add(o2);

        Order o3 = new Order();
        o3.setId(2);
        o3.setRow(5);
        o3.setColumn(6);
        Map<Product, Integer> o3product2quantity = new HashMap<>();
        o3product2quantity.put(id2product.get(2), 3);
        o3.setProducts2quantity(o3product2quantity);
        actualOrders.add(o3);

        Assert.assertEquals(orders, actualOrders);
    }

    // Drone

    @Test
    public void testDronesAmount() {
        Assert.assertEquals(dronesAmount, actualDronesAmount);
    }

    @Test
    public void testDronesMaxPayload() {
        Assert.assertEquals(dronePayload, actualDronePayload);
    }

    // Problem Container
    @Test
    public void testProblemContainer() {

        Assert.assertEquals(orders, problemContainer.getOrders());
        Assert.assertEquals(drones, problemContainer.getDrones());
        Assert.assertEquals(warehouses, problemContainer.getWarehouses());
        Assert.assertEquals(fr.getRowsAmount(), problemContainer.getNumRows());
        Assert.assertEquals(fr.getColumnsAmount(), problemContainer.getNumColumns());
        Assert.assertEquals(fr.getTurnsAmount(), problemContainer.getMaxInstant());
    }

    // ============================ utils
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
