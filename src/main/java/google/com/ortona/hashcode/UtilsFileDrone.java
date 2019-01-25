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
    private Map<Integer, Product> id2product;
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

    public int getRowsAmount() {
        return this.header[0];
    }

    public int getColumnsAmount() {
        return this.header[1];
    }

    public int getDronesAmount() {
        return this.header[2];
    }

    public int getTurnsAmount() {
        return this.header[3];
    }

    public int getMaxPayload() {
        return this.header[4];
    }

    public int getProductAmount() {
        return Integer.parseInt(this.file[1]);
    }

    public int[] getProductWeights() {
        String line = this.file[2];
        String[] split = splitString(line, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        return converted;
    }

    public int getWarehousesAmount() {
        return Integer.parseInt(this.file[3]);
    }

    public Map<Integer, Product> getId2product() {
        return id2product;
    }

    public void setId2product(Map<Integer, Product> id2product) {
        this.id2product = id2product;
    }

    public int getOrderAmountIndex() {
        int count = 0;

        // add header row
        count++;

        // add products amount row
        count++;

        // add products weights row
        count++;

        // add warehouses amount row
        count++;

        // add warehouses description rows
        int warehouseAmount = this.getWarehousesAmount();
        int warehouseDescriptionRows = warehouseAmount * 2;
        count = count + warehouseDescriptionRows;
        return count;
    }

    public int getOrdersAmount() {
        int index = this.getOrderAmountIndex();
        return Integer.parseInt(this.file[index]);
    }

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
        id2product = new HashMap<>();

        int[] weights = getProductWeights();

        for (int i = 0; i < amount; i++) {
            Product p = new Product(i, weights[i]);
            id2product.put(i, p);
            products.add(p);
        }

        // always finish with this.setProducts()
        this.setProducts(products);
        this.setId2product(id2product);
    }

    public void createWarehouses() {
        Integer amount = this.getWarehousesAmount();
        warehouses = new ArrayList<>();
        Integer index = 3;

        for (int i = 0; i < amount; i++) {
            Warehouse w = new Warehouse();

            // Set id
            w.setId(i);

            // Set coordinates
            index++;
            String[] coordinatesSplit = splitString(this.file[index], " ");
            int[] coordinates = convertArrayOfStringToArrayOfInt(coordinatesSplit);
            w.setRow(coordinates[0]);
            w.setColumn(coordinates[1]);

            // Set products
            index++;
            String[] productSplit = splitString(this.file[index], " ");
            int[] ps = convertArrayOfStringToArrayOfInt(productSplit);
            Map<Product, Integer> product2quantity = new HashMap<>();

            for (int j = 0; j < ps.length; j++) {
                if (ps[j] > 0) {
                    Product product = id2product.get(j);
                    product2quantity.put(product, ps[j]);
                }
            }

            w.setProduct2quantity(product2quantity);

            warehouses.add(w);
        }

        // always finish with this.setWarehouses()
        this.setWarehouses(warehouses);
    }

    public void createOrders() {
        orders = new ArrayList<>();
        int orderAmountIndex = this.getOrderAmountIndex();
        int amount = this.getOrdersAmount();
        int index = orderAmountIndex;

        for (int i = 0; i < amount; i++) {
            Order o = new Order();

            // Set id
            o.setId(i);

            // Set coordinates
            index++;
            String[] coordinatesSplit = splitString(this.file[index], " ");

            int[] coordinates = convertArrayOfStringToArrayOfInt(coordinatesSplit);
            o.setRow(coordinates[0]);
            o.setColumn(coordinates[1]);

            // Set products
            index++;
            Integer productsAmount = Integer.parseInt(this.file[index]);

            index++;
            String[] split = splitString(this.file[index], " ");

            Map<Product, Integer> products2quantity = new HashMap<>();

            for (int j = 0; j < productsAmount; j++) {

                Product p = id2product.get(split[j]);

                int quantity = 1;

                if (products2quantity.containsValue(p)) {
                    quantity = products2quantity.get(p);
                }

                products2quantity.put(p, quantity);

            }

            orders.add(o);
        }

        this.setOrders(orders);

    }

    public void createDrones() {
        int amount = this.getDronesAmount();
        drones = new ArrayList<>();
        int payload = this.getMaxPayload();

        for (int i = 0; i < amount; i++) {
            Drone d = new Drone();
            d.setId(i);
            d.setCapacity(payload);
            drones.add(d);
        }

        this.setDrones(drones);
    }

    public void createProblemContainer() {

        problemContainer = new ProblemContainer();

        problemContainer.setOrders(this.getOrders());
        problemContainer.setDrones(this.getDrones());
        problemContainer.setWarehouses(this.getWarehouses());
        problemContainer.setNumRows(this.getRowsAmount());
        problemContainer.setNumColumns(this.getColumnsAmount());
        problemContainer.setMaxInstant(this.getTurnsAmount());

        this.setProblemContainer(problemContainer);

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

            //LOGGER.info("Drones creation: start");
            createDrones();
            //LOGGER.info("Drones creation: done");

            //LOGGER.info("Orders creation: start");
            createOrders();
            //LOGGER.info("Orders creation: done");

            //LOGGER.info("Drones creation: start");
            createProblemContainer();
            //LOGGER.info("Drones creation: done");

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
