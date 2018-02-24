package google.com.ortona.hashcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import google.com.ortona.hashcode.data_center.logic.model.*;

public class UtilsFileServer {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private List<Server> servers;
    private List<Slot> unavailableSlots;

    // 2. generate setters and getters for header and data
    public void setHeader(int[] header) {
        this.header = header;
    }

    public int[] getHeader() {
        return header;
    }

    public List<Slot> getUnavailableSlots() {
        return unavailableSlots;
    }

    public void setUnavailableSlots(List<Slot> unavailableSlots) {
        this.unavailableSlots = unavailableSlots;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createUnavailableSlots() {
        String[] file = this.getFile();
        int unavailableSlotSize = this.getHeader()[2]; // index 2 in header
        String[] dataRaw = cloneArrayOfString(file, unavailableSlotSize, file.length);

        List<Slot> slots = convertArrayOfStringToListOfSlot(dataRaw);

        // always finish with this.setData()
        this.setUnavailableSlots(slots);
    }

    public void createServers() {
        String[] file = this.getFile();
        int unavailableSlotSize = this.getHeader()[2]; // index 2 in header
        String[] dataRaw = cloneArrayOfString(file, unavailableSlotSize + 1, file.length);

        List<Server> servers = convertArrayOfStringToListOfServers(dataRaw);

        this.setServers(servers);
    }







    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFileServer(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            //LOGGER.info("Header creation: start");
            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Unavailable slot creation: start");
            createUnavailableSlots();
            //LOGGER.info("Unavailable slot creation: done");

            //LOGGER.info("Server creation: start");
            createServers();
            //LOGGER.info("Server creation: done");


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

    public char[][] cconvertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

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

    public List<Server> convertArrayOfStringToListOfServers(String[] dataRaw) {


        List<Server> result = new ArrayList<>() ;

        for (int i = 0; i < dataRaw.length; i++) {
            result.add(convertStringToServer(dataRaw[i], i));
        }
        return result;
    }

    public Server convertStringToServer(String string, int index) {

        String[] split = splitString(string, " ");
        return new Server(index, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public List<Slot> convertArrayOfStringToListOfSlot(String[] dataRaw) {

        List<Slot> result = new ArrayList<>() ;

        for (int i = 0; i < dataRaw.length; i++) {
            result.add(convertStringToSlot(dataRaw[i]));
        }
        return result;
    }

    public Slot convertStringToSlot(String string) {

        String[] split = splitString(string, " ");
        return new Slot(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

}
