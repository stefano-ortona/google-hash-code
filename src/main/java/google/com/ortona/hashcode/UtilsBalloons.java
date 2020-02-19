package google.com.ortona.hashcode;

import google.com.ortona.hashcode.final_2015.model.Baloon;
import google.com.ortona.hashcode.final_2015.model.Pair;
import google.com.ortona.hashcode.final_2015.model.ProblemContainer;
import google.com.ortona.hashcode.final_2015.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilsBalloons {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] firstRow;
    private int[] secondRow;
    private int row;
    private int columns;
    private int heights;
    private int targetCellAmount;
    private int coveredRadius;
    private int availableBalloons;
    private int turns;
    private int initialCellX;
    private int initialCellY;
    boolean[][] grid;
    List<Pair[][]> winds;
    ProblemContainer problemContainer;
    List< Baloon > baloons;

    // 2. generate setters and getters for header and data

    public int[] getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int[] firstRow) {
        this.firstRow = firstRow;
    }

    public int[] getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(int[] secondRow) {
        this.secondRow = secondRow;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getHeights() {
        return heights;
    }

    public void setHeights(int heights) {
        this.heights = heights;
    }

    public int getTargetCellAmount() {
        return targetCellAmount;
    }

    public void setTargetCellAmount(int targetCellAmount) {
        this.targetCellAmount = targetCellAmount;
    }

    public int getCoveredRadius() {
        return coveredRadius;
    }

    public void setCoveredRadius(int coveredRadius) {
        this.coveredRadius = coveredRadius;
    }

    public int getAvailableBalloons() {
        return availableBalloons;
    }

    public void setAvailableBalloons(int availableBalloons) {
        this.availableBalloons = availableBalloons;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getInitialCellX() {
        return initialCellX;
    }

    public void setInitialCellX(int initialCellX) {
        this.initialCellX = initialCellX;
    }

    public int getInitialCellY() {
        return initialCellY;
    }

    public void setInitialCellY(int initialCellY) {
        this.initialCellY = initialCellY;
    }


    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }

    public List<Pair[][]> getWinds() {
        return winds;
    }

    public void setWinds(List<Pair[][]> winds) {
        this.winds = winds;
    }

    public List<Baloon> getBaloons() {
        return baloons;
    }

    public void setBaloons(List<Baloon> baloons) {
        this.baloons = baloons;
    }

    //3. define logic of createHeader() and createData()

    public void createModels() {
        String firstLine = getFirstLineOfFile();
        String[] firstLineSplit = splitString(firstLine, " ");
        int[] firstLineConverted = convertArrayOfStringToArrayOfInt(firstLineSplit);

        // First row
        this.setRow(firstLineConverted[0]);
        this.setColumns(firstLineConverted[1]);
        this.setHeights(firstLineConverted[2]);

        // Second row
        String secondLine = this.file[1];
        String[] secondLineSplit = splitString(secondLine, " ");
        int[] secondLineConverted = convertArrayOfStringToArrayOfInt(secondLineSplit);

        this.setTargetCellAmount(secondLineConverted[0]);
        this.setCoveredRadius(secondLineConverted[1]);
        this.setAvailableBalloons(secondLineConverted[2]);
        this.setTurns(secondLineConverted[3]);

        // Third row
        String thirdRow = this.file[2];
        String[] thirdRowSplit = splitString(thirdRow, " ");
        int[] thirdRowConverted = convertArrayOfStringToArrayOfInt(thirdRowSplit);

        this.setInitialCellX(thirdRowConverted[0]);
        this.setInitialCellY(thirdRowConverted[1]);

        // Init Grid
        grid = new boolean[this.getRow()][this.getColumns()];
        for (int r = 0; r < this.getRow(); r ++){
            for (int c = 0; c < this.getColumns(); c ++){
                // Init all to false
                grid[r][c] = false;
            }
        }
        // mark target cells to true
        for (int i = 0; i<this.getTargetCellAmount(); i++){
            // 3 fixed row
            String currentRow = this.file[2 + 1 + i];
            String[] currentRowSplit = splitString(currentRow, " ");
            int[] currentRowConverted = convertArrayOfStringToArrayOfInt(currentRowSplit);
            // Set to true
            grid[currentRowConverted[0]][currentRowConverted[1]] = true;
        }
        this.setGrid(grid);

        // Init winds - List<Pair[][]> winds;
        int index = 2 + this.getTargetCellAmount();
        winds = new ArrayList<>();

        for (int j = 0; j< this.getHeights(); j++){
            Pair[][] matrix = new Pair[this.getRow()][this.getColumns()];
            for (int k = 0; k < this.getRow(); k++){
                index++;
                String currentWind = this.file[index];
                String[] currentWindSplit = splitString(currentWind, " ");
                int[] currentWindConverted = convertArrayOfStringToArrayOfInt(currentWindSplit);

                for (int p = 0; p < currentWindConverted.length; p++){
                    Pair pa = new Pair(currentWindConverted[p], currentWindConverted[p + 1]);
                    int columnIndex = p / 2;
                    matrix[k][columnIndex] = pa;
//                    System.out.println(columnIndex + " x");
//                    System.out.println(k + " y");
//                    System.out.println(pa.toString());
//                    System.out.println("-");
                    p++;
                }
            }

            //System.out.println("------- Layer " + j);
            winds.add(matrix);
        }
        this.setWinds(winds);

        //System.out.println(this.getWinds().get());

        // Init balloons
        baloons = new ArrayList<>();
        for (int b = 0; b < this.getAvailableBalloons(); b ++){
            //int id, int row, int column, int height
            Baloon bal = new Baloon(b, this.getInitialCellX(), this.getInitialCellY(), 0);
            baloons.add(bal);
        }

        this.setBaloons(baloons);

    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    public void createProblemContainer() {
        Status status = new  Status(this.getBaloons(), this.getGrid(), this.getHeights(), this.getTurns(), this.getWinds(), this.getCoveredRadius());
        problemContainer = new ProblemContainer(status);
        this.setProblemContainer(problemContainer);
    }


    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/final_2015/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsBalloons(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            createModels();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Data creation: start");
            createProblemContainer();
            //LOGGER.info("Data creation: done");
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