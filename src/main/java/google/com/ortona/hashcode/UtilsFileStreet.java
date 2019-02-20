package google.com.ortona.hashcode;

import google.com.ortona.hashcode.final_2014.model.Car;
import google.com.ortona.hashcode.final_2014.model.Junction;
import google.com.ortona.hashcode.final_2014.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsFileStreet {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private List<Car> cars;
    private Map<Integer, Car> id2car;
    private List<Street> streets;
    private Map<Integer, Street> id2street;
    private List<Junction> junctions;
    private Map<Integer, Junction> id2junction;


    private ProblemContainer problemContainer;

    private char[][] data;

    // 2. generate setters and getters for header and data
    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    public void setHeader(int[] header) {
        this.header = header;
    }

    public void setData(char[][] data) {
        this.data = data;
    }

    public int[] getHeader() {
        return header;
    }

    public char[][] getData() {
        return data;
    }


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<Integer, Car> getId2car() {
        return id2car;
    }

    public void setId2car(Map<Integer, Car> id2car) {
        this.id2car = id2car;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public Map<Integer, Street> getId2street() {
        return id2street;
    }

    public void setId2street(Map<Integer, Street> id2street) {
        this.id2street = id2street;
    }

    public List<Junction> getJunctions() {
        return junctions;
    }

    public void setJunctions(List<Junction> junctions) {
        this.junctions = junctions;
    }

    public Map<Integer, Junction> getId2junction() {
        return id2junction;
    }

    public void setId2junction(Map<Integer, Junction> id2junction) {
        this.id2junction = id2junction;
    }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    //○ N denotes the number of junctions in the city
    public int getJunctionAmount() {
        return this.getHeader()[0];
    }

    //○ M denotes the number of streets in the city
    public int getStreetAmount() {
        return this.getHeader()[1];
    }

    //○ T denotes the virtual time in seconds allowed for the car itineraries
    public int getAllowedTime() {
        return this.getHeader()[2];
    }

    //○ C denotes the number of cars in the fleet
    public int getCarAmount() {
        return this.getHeader()[3];
    }

    //○ S denotes the junction at which all the cars are located initially
    public int getInitialJunction() {
        return this.getHeader()[4];
    }

    // Car
    public void createCars() {
        cars = new ArrayList<>();
        id2car = new HashMap<>();

        for (int i = 0; i < this.getCarAmount(); i++) {
            Car c = new Car();
            c.setId(i);
            c.moveToJunction(id2junction.get(this.getInitialJunction()));
            cars.add(c);
            id2car.put(i, c);
        }

        this.setCars(cars);
        this.setId2car(id2car);
    }

    // Junction
    public void createJunctions() {
        String[] file = this.getFile();
        String[] dataRaw = cloneArrayOfString(file, 1, 1 + this.getJunctionAmount());

        junctions = new ArrayList<>();
        id2junction = new HashMap<>();

        for (int i = 0; i < this.getJunctionAmount(); i++) {
            Junction junction = new Junction();
            junction.setId(i);

            // coordinates
            String[] coordinatesSplit = splitString(dataRaw[i], " ");
            junction.setLat(Double.parseDouble(coordinatesSplit[0]));
            junction.setLng(Double.parseDouble(coordinatesSplit[1]));

            junctions.add(junction);
            id2junction.put(i, junction);
        }

        this.setJunctions(junctions);
        this.setId2junction(id2junction);
    }

    // Street
    public void createStreets() {
        String[] file = this.getFile();
        String[] dataRaw = cloneArrayOfString(file, 1 + this.getJunctionAmount(), this.getFile().length);
        streets = new ArrayList<>();
        id2street = new HashMap<>();

        for (int i = 0; i < this.getStreetAmount(); i++) {
            Street street = new Street();
            //street.setId(i);
            String[] split = splitString(dataRaw[i], " ");
            int[] converted = convertArrayOfStringToArrayOfInt(split);

            Junction start = this.id2junction.get(converted[0]);
            street.setStart(start);
            start.getOutgoingStreets().add(street);

            Junction end = this.id2junction.get(converted[1]);
            street.setEnd(end);

            boolean isBidirectional = (converted[2] == 2);
            street.setBidirectional(isBidirectional);

            double timeCost = converted[3];
            street.setTimeCost(timeCost);

            double length =  converted[4];
            street.setLength(length);

            streets.add(street);
            id2street.put(i, street);
        }

        this.setStreets(streets);
        this.setId2street(id2street);
    }

    public void createProblemContainer() {

        problemContainer = new ProblemContainer();
        problemContainer.setTotTime(this.getAllowedTime());
        problemContainer.setAllCars(this.getCars());
        problemContainer.setAllJunctions(this.getJunctions());

        this.setProblemContainer(problemContainer);
    }


    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/final_2014/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFileStreet(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            createHeader();

            createJunctions();

            createStreets();

            createCars();

            createProblemContainer();

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
