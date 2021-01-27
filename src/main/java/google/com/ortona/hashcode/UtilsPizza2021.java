package google.com.ortona.hashcode;

import google.com.ortona.hashcode.y_2021.pizza.model.Pizza;
import google.com.ortona.hashcode.y_2021.pizza.model.ProblemContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsPizza2021 {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] firstRow;
    private int pizzasAvailable;
    private int team_2_size;
    private int team_3_size;
    private int team_4_size;
    private ArrayList<Pizza> pizzas;
    private google.com.ortona.hashcode.y_2021.pizza.model.ProblemContainer problemContainer;

    // 2. generate setters and getters for header and data

    public int[] getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int[] firstRow) {
        this.firstRow = firstRow;
    }

    public int getPizzasAvailable() {
        return pizzasAvailable;
    }

    public void setPizzasAvailable(int pizzasAvailable) {
        this.pizzasAvailable = pizzasAvailable;
    }

    public int getTeam_2_size() {
        return team_2_size;
    }

    public void setTeam_2_size(int team_2_size) {
        this.team_2_size = team_2_size;
    }

    public int getTeam_3_size() {
        return team_3_size;
    }

    public void setTeam_3_size(int team_3_size) {
        this.team_3_size = team_3_size;
    }

    public int getTeam_4_size() {
        return team_4_size;
    }

    public void setTeam_4_size(int team_4_size) {
        this.team_4_size = team_4_size;
    }

    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] firstLineSplit = splitString(firstLine, " ");
        int[] firstLineConverted = convertArrayOfStringToArrayOfInt(firstLineSplit);

        this.setPizzasAvailable(firstLineConverted[0]);
        this.setTeam_2_size(firstLineConverted[1]);
        this.setTeam_3_size(firstLineConverted[2]);
        this.setTeam_4_size(firstLineConverted[3]);
    }

    public void createData() {
        String[] file = this.getFile();
        String[] dataRaw = cloneArrayOfString(file, 1, file.length);
        pizzas = new ArrayList<>();

        for (int i=0; i< dataRaw.length-1; i++){
            String[] parsed = splitString(dataRaw[i], " ");
            String[] ingredients = cloneArrayOfString(parsed, 1, parsed.length);
            Pizza p = new Pizza();
            p.setId(i);
            p.setIngredientList(Arrays.asList(ingredients.clone()));
            pizzas.add(p);
        }

        // always finish with this.setData()
        this.setPizzas(pizzas);
    }


    public void createProblemContainer() {

        Map<Integer, Integer> teams = new HashMap<>();

        teams.put(2, this.getTeam_2_size());
        teams.put(3, this.getTeam_3_size());
        teams.put(4, this.getTeam_4_size());

        problemContainer = new ProblemContainer(teams, this.getPizzas());
        this.setProblemContainer(problemContainer);
    }


    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/y_2021/pizza/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsPizza2021(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Data creation: start");
            createData();
            //LOGGER.info("Data creation: done");

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