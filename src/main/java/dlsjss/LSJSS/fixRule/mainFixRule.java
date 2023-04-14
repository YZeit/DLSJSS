package dlsjss.LSJSS.fixRule;

import dlsjss.problem.InstanceFixRule;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import dlsjss.problem.Instance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class mainFixRule {

    public static double calculateStandardDeviation(double[] array) {

        // get the sum of array
        double sum = 0.0;
        for (double i : array) {
            sum += i;
        }

        // get the mean of array
        int length = array.length;
        double mean = sum / length;

        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        int nScenariosTraining = 10;
        int nScenariosValidation = 10;
        int[] PRODUCTS = {6, 6, 6, 10, 10, 10, 20, 20, 20};
        int[] MACHINES = {6, 6, 6, 10, 10, 10, 5, 5, 5};
        int[] PERIODS = {5, 10, 20, 5, 10, 20, 5, 10, 20};
        String[] LSS = {"DS", "E", "GR", "LUC", "AC", "R"};
        String[] JSS = {"SPT", "FIFO", "MTWR", "WINQ", "PT+WINQ", "R"};
        String pathScenariosTraining = "G:/My Drive/LSJSS_uncertainty/scenarios_training/";
        String pathScenariosValidation = "G:/My Drive/LSJSS_uncertainty/scenarios/";
        String pathInstances = "G:/My Drive/LSJSS_uncertainty/test instances/";
        // workbook absolut mean
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetTraining = workbook.createSheet("training");
        XSSFSheet sheetTesting = workbook.createSheet("testing");

        // workbook gap mean
        //XSSFWorkbook workbookGap = new XSSFWorkbook();
        //XSSFSheet sheetTrainingGap = workbookGap.createSheet("training");
        //XSSFSheet sheetTestingGap = workbookGap.createSheet("testing");

        // workbook gap std
        //XSSFWorkbook workbookGapStd = new XSSFWorkbook();
        //XSSFSheet sheetTrainingGapStd = workbookGapStd.createSheet("training");
        //XSSFSheet sheetTestingGapStd = workbookGapStd.createSheet("testing");

        // workbook ececution time
        XSSFWorkbook workbookExecutionTime = new XSSFWorkbook();
        XSSFSheet sheetTrainingExecutionTime = workbookExecutionTime.createSheet("training");
        XSSFSheet sheetTestingExecutionTime = workbookExecutionTime.createSheet("testing");

        int rowCount = 0;
        Row titleRowTraining = sheetTraining.createRow(rowCount);
        Row titleRowTesting = sheetTesting.createRow(rowCount);
        //Row titleRowTrainingGap = sheetTrainingGap.createRow(rowCount);
        //Row titleRowTestingGap = sheetTestingGap.createRow(rowCount);
        //Row titleRowTrainingGapStd = sheetTrainingGapStd.createRow(rowCount);
        //Row titleRowTestingGapStd = sheetTestingGapStd.createRow(rowCount);
        Row titleRowTrainingExecutionTime = sheetTrainingExecutionTime.createRow(rowCount);
        Row titleRowTestingExecutionTime = sheetTestingExecutionTime.createRow(rowCount);
        rowCount++;
        InstanceFixRule currentInstance = new InstanceFixRule();
        for (int j=0; j<JSS.length; j++) {
            for (int l = 0; l < LSS.length; l++) {
                Row rowTraining = sheetTraining.createRow(rowCount);
                Row rowTesting = sheetTesting.createRow(rowCount);
                Cell cellTrainingIndex = rowTraining.createCell(0);
                cellTrainingIndex.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndex = rowTesting.createCell(0);
                cellTestingIndex.setCellValue(JSS[j] + " / " + LSS[l]);
                //Row rowTrainingGap = sheetTrainingGap.createRow(rowCount);
                //Row rowTestingGap = sheetTestingGap.createRow(rowCount);
                //Cell cellTrainingIndexGap = rowTrainingGap.createCell(0);
                //cellTrainingIndexGap.setCellValue(JSS[j] + " / " + LSS[l]);
                //Cell cellTestingIndexGap = rowTestingGap.createCell(0);
                //cellTestingIndexGap.setCellValue(JSS[j] + " / " + LSS[l]);
                //Row rowTrainingGapStd = sheetTrainingGapStd.createRow(rowCount);
                //Row rowTestingGapStd = sheetTestingGapStd.createRow(rowCount);
                //Cell cellTrainingIndexGapStd = rowTrainingGapStd.createCell(0);
                //cellTrainingIndexGapStd.setCellValue(JSS[j] + " / " + LSS[l]);
                //Cell cellTestingIndexGapStd = rowTestingGapStd.createCell(0);
                //cellTestingIndexGapStd.setCellValue(JSS[j] + " / " + LSS[l]);
                Row rowTrainingExecutionTime = sheetTrainingExecutionTime.createRow(rowCount);
                Row rowTestingExecutionTime = sheetTestingExecutionTime.createRow(rowCount);
                Cell cellTrainingIndexExecutionTime = rowTrainingExecutionTime.createCell(0);
                cellTrainingIndexExecutionTime.setCellValue(JSS[j] + " / " + LSS[l]);
                //Cell cellTestingIndexExecutionTime = rowTestingGapStd.createCell(0);
                //cellTestingIndexExecutionTime.setCellValue(JSS[j] + " / " + LSS[l]);

                for (int i = 0; i < PRODUCTS.length; i++) {
                    //training
                    Cell cellTitleTraining = titleRowTraining.createCell(i+1);
                    cellTitleTraining.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    //Cell cellTitleTrainingGap = titleRowTrainingGap.createCell(i+1);
                    //cellTitleTrainingGap.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    //Cell cellTitleTrainingGapStd = titleRowTrainingGapStd.createCell(i+1);
                    //cellTitleTrainingGapStd.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    Cell cellTitleTrainingExecutionTime = titleRowTrainingExecutionTime.createCell(i+1);
                    cellTitleTrainingExecutionTime.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    double totalCostsTraining = 0.0;
                    //double totalGapTraining = 0.0;
                    //double[] gapTraining = new double[nScenariosTraining];
                    double totalElapsedTime = 0.0;
                    String pathToCurrentScenario = pathScenariosTraining + PRODUCTS[i]+"X"+MACHINES[i]+"x"+PERIODS[i]+"/cv0.2/";
                    for (int s = 0; s < nScenariosTraining; s++) {
                        currentInstance.setup(PRODUCTS[i], MACHINES[i], PERIODS[i], 10, s, pathToCurrentScenario, pathInstances);
                        long startTime = System.nanoTime();
                        double result = MainLotsizingFixRule.run(currentInstance, j, l);
                        long elapsedTime = System.nanoTime() - startTime;
                        totalElapsedTime += elapsedTime*0.000000001;
                        totalCostsTraining += result;
                        //totalGapTraining += (result/currentInstance.optimum)-1;
                        //gapTraining[s] = (result/currentInstance.optimum)-1;
                    }
                    // calculate standard deviation
                    //double stdTraining = calculateStandardDeviation(gapTraining);
                    //resultsTraining[i][j*6+l] = totalCostsTraining / RANDOM_SEEDS_TRAINING.length;
                    Cell cellTraining = rowTraining.createCell(i+1);
                    cellTraining.setCellValue(totalCostsTraining / nScenariosTraining);
                    //Cell cellTrainingGap = rowTrainingGap.createCell(i+1);
                    //cellTrainingGap.setCellValue(totalGapTraining / nScenariosTraining);
                    //Cell cellTrainingGapStd = rowTrainingGapStd.createCell(i+1);
                    //cellTrainingGapStd.setCellValue(stdTraining);
                    Cell cellTrainingExecutionTime = rowTrainingExecutionTime.createCell(i+1);
                    cellTrainingExecutionTime.setCellValue(totalElapsedTime / nScenariosTraining);

                    // testing
                    Cell cellTitleTesting = titleRowTesting.createCell(i+1);
                    cellTitleTesting.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    //Cell cellTitleTestingGap = titleRowTestingGap.createCell(i+1);
                    //cellTitleTestingGap.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    //Cell cellTitleTestingGapStd = titleRowTestingGapStd.createCell(i+1);
                    //cellTitleTestingGapStd.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    Cell cellTitleTestingExecutionTime = titleRowTestingExecutionTime.createCell(i+1);
                    cellTitleTestingExecutionTime.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    double totalCostsTesting = 0.0;
                    //double totalGapTesting = 0.0;
                    //double[] gapTesting = new double[nScenariosValidation];
                    totalElapsedTime = 0.0;
                    String pathToCurrentScenarioValidation = pathScenariosValidation + PRODUCTS[i]+"X"+MACHINES[i]+"x"+PERIODS[i]+"/cv0.2/";
                    for (int s = 0; s < nScenariosValidation; s++) {
                        System.out.println("Validation");
                        System.out.println("Problem: " + PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                        System.out.println("JSS: " + JSS[j]);
                        System.out.println("LSS: " + LSS[l]);
                        System.out.println("Scenario: " + s);
                        currentInstance.setup(PRODUCTS[i], MACHINES[i], PERIODS[i], 10, s, pathToCurrentScenarioValidation, pathInstances);
                        long startTime = System.nanoTime();
                        double result = MainLotsizingFixRule.run(currentInstance, j, l);
                        System.out.println("Result: "+result);
                        long elapsedTime = System.nanoTime() - startTime;
                        totalElapsedTime += elapsedTime*0.000000001;
                        totalCostsTesting += result;
                        //totalGapTesting += (result/currentInstance.optimum)-1;
                        //gapTesting[s] = (result/currentInstance.optimum)-1;
                    }
                    // calculate standard deviation
                    //double stdTesting = calculateStandardDeviation(gapTesting);
                    //resultsTesting[i][j*6+l] = totalCostsTesting / RANDOM_SEEDS_TESTING.length;
                    Cell cellTesting = rowTesting.createCell(i+1);
                    cellTesting.setCellValue(totalCostsTesting / nScenariosValidation);
                    //Cell cellTestingGap = rowTestingGap.createCell(i+1);
                    //cellTestingGap.setCellValue(totalGapTesting / nScenariosValidation);
                    //Cell cellTestingGapStd = rowTestingGapStd.createCell(i+1);
                    //cellTestingGapStd.setCellValue(stdTesting);
                    Cell cellTestingExecutionTime = rowTestingExecutionTime.createCell(i+1);
                    cellTestingExecutionTime.setCellValue(totalElapsedTime / nScenariosValidation);
                }
                rowCount++;
            }
        }
        //System.out.println("results training: " + Arrays.deepToString(resultsTraining));
        //System.out.println("results training: " + Arrays.deepToString(resultsTesting));

        try {
            //Write the workbook in file system
            FileOutputStream outPath = new FileOutputStream(new File("C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/fixed rules/results.xlsx"));
            workbook.write(outPath);
            outPath.close();
            //FileOutputStream outPathGap = new FileOutputStream(new File("C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/fixed rules/results_gap.xlsx"));
            //workbookGap.write(outPathGap);
            //outPathGap.close();
            //FileOutputStream outPathGapStd = new FileOutputStream(new File("C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/fixed rules/results_gap_std.xlsx"));
            //workbookGapStd.write(outPathGapStd);
            //outPathGapStd.close();
            FileOutputStream outPathExecutionTime = new FileOutputStream(new File("C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/fixed rules/results_execution_time.xlsx"));
            workbookExecutionTime.write(outPathExecutionTime);
            outPathExecutionTime.close();
            //System.out.println("schedule.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

