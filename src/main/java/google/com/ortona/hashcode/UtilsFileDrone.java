package google.com.ortona.hashcode;

import google.com.ortona.hashcode.qualification_2016.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsFileDrone {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private List<Product> products;
    private List<Order> orders;
    private List<Warehouse> warehouses;
    private List<Drone> drones;
    private ProblemContainer problemContainer;

    // 2. generate setters and getters for header and data
    public int[] getHeader() {
        return header;
    }

    public void setHeader(int[] header) {
        this.header = header;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }

    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    public int getRowsAmount(){
        return this.header[0];
    }

    public int getColumnsAmount(){
        return this.header[1];
    }

    public int getDronesAmount(){
        return this.header[2];
    }

    public int getTurnsAmount(){
        return this.header[3];
    }

    public int getMaxPayload(){
        return this.header[4];
    }

    public int getProductAmount() { return Integer.parseInt(this.file[1]); }

    public int[] getProductWeights() {
        String line = this.file[2];
        String[] split = splitString(line, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        return converted;
    }

    public int getWerehousesAmount() { return Integer.parseInt(this.file[3]); }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createProducts() {

        Integer amount = this.getProductAmount();
        products = new ArrayList<>();
        int[] weights = getProductWeights();

        for (int i = 0; i < amount; i++) {
            products.add(new Product(i, weights[i]));
        }

        // always finish with this.setProducts()
        this.setProducts(products);
    }

    public void createWarehouses() {
        Integer amount = this.getWerehousesAmount();
        warehouses = new ArrayList<>();
        Integer fileIndex = 3;

        for (int i = 0; i < amount; i++) {
            Warehouse w = new Warehouse();
            Integer coordinatesIndex = fileIndex + 1;
            Integer productsIndex = fileIndex + 2;

            // Set coordinates
            String[] coordinatesSplit = splitString(this.file[coordinatesIndex], " ");
            int[] coordinates = convertArrayOfStringToArrayOfInt(coordinatesSplit);
            w.setId(i);
            w.setRow(coordinates[0]);
            w.setColumn(coordinates[1]);

            // Set products
            String[] productSplit = splitString(this.file[productsIndex], " ");
            int[] ps = convertArrayOfStringToArrayOfInt(productSplit);
            Map<Product, Integer> product2quantity = new HashMap<>();

            for (int j = 0; j < ps.length; j++){
                if (ps[j] > 0) {
                    //Product prod = products.stream().filter(p -> p.getId() == j);
                    // find product
                    // add to map Product, quantity (that is j)
                }
            }
            
            w.setProduct2quantity(product2quantity);

            warehouses.add(w);
        }

        // always finish with this.setWarehouses()
        this.setWarehouses(warehouses);
    }

    public void createOrders() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createDrones() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }







    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/qualification_2016/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFileDrone(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            //LOGGER.info("Header creation: start");
            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Products creation: start");
            createProducts();
            //LOGGER.info("Products creation: done");

            //LOGGER.info("Warehouses creation: start");
            createWarehouses();
            //LOGGER.info("Warehouses creation: done");

//
//            //LOGGER.info("Orders creation: start");
//            createOrders();
//            //LOGGER.info("Orders creation: done");
//
//            //LOGGER.info("Drones creation: start");
//            createDrones();
//            //LOGGER.info("Drones creation: done");

//            //LOGGER.info("Drones creation: start");
//            createProblemContainer();
//            //LOGGER.info("Drones creation: done");



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

    public char[][] convertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

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

}
