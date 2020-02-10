package google.com.ortona.hashcode;

import google.com.ortona.hashcode.preparation_2020.model.ProblemContainer;
import google.com.ortona.hashcode.final_2014.model.Street;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsFilePizzaPreparation2020 {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private ProblemContainer problemContainer;

    private List<Integer> data;

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

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public int[] getHeader() {
        return header;
    }

    public List<Integer> getData() {
        return data;
    }


//3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createData() {
        String[] file = this.getFile();
        String[] dataRaw = cloneArrayOfString(file, 1, file.length);
        int[] rawNumbers = convertArrayOfStringToArrayOfInt(dataRaw);
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0 ; i<rawNumbers.length; i++){
            numbers.add(rawNumbers[i]);
        }

        // always finish with this.setData()
        this.setData(numbers);
    }

    public int getGoal() {
        return this.getHeader()[0];
    }

    public void createProblemContainer() {

        problemContainer = new ProblemContainer();
        problemContainer.setGoal(this.getGoal());
        problemContainer.setNumbers(this.getData());

        this.setProblemContainer(problemContainer);
    }


    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/preparation_2020";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFilePizzaPreparation2020(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            createHeader();
            createData();

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
