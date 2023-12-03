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
        int nScenariosValidation = 30;
        double cv = 0.5;
        double ct = 0.5;
        int[] PRODUCTS = {6, 10, 20};
        int[] MACHINES = {6, 10, 5};
        int[] PERIODS = {20, 20, 20};
        String[] LSS = {"DS", "E", "GR", "LUC", "AC", "R"};
        String[] JSS = {"SPT", "FIFO", "MTWR", "WINQ", "PT+WINQ", "R"};
        String pathScenariosTraining = "G:/My Drive/LSJSS_uncertainty/scenarios_training/";
        String pathScenariosValidation = "G:/My Drive/LSJSS_uncertainty/scenarios/";
        String pathInstances = "G:/My Drive/LSJSS_uncertainty/test instances/ct"+ct+"/";
        // workbook absolut mean
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetTraining = workbook.createSheet("training");
        XSSFSheet sheetTesting = workbook.createSheet("testing");
        // workbook std
        XSSFWorkbook workbookStd = new XSSFWorkbook();
        XSSFSheet sheetTrainingStd = workbookStd.createSheet("training");
        XSSFSheet sheetTestingStd = workbookStd.createSheet("testing");
        // workbook min
        XSSFWorkbook workbookMin = new XSSFWorkbook();
        XSSFSheet sheetTrainingMin = workbookMin.createSheet("training");
        XSSFSheet sheetTestingMin = workbookMin.createSheet("testing");
        // workbook max
        XSSFWorkbook workbookMax = new XSSFWorkbook();
        XSSFSheet sheetTrainingMax = workbookMax.createSheet("training");
        XSSFSheet sheetTestingMax = workbookMax.createSheet("testing");
        // workbook ececution time
        XSSFWorkbook workbookExecutionTime = new XSSFWorkbook();
        XSSFSheet sheetTrainingExecutionTime = workbookExecutionTime.createSheet("training");
        XSSFSheet sheetTestingExecutionTime = workbookExecutionTime.createSheet("testing");

        int rowCount = 0;
        // mean
        Row titleRowTraining = sheetTraining.createRow(rowCount);
        Row titleRowTesting = sheetTesting.createRow(rowCount);
        // std
        Row titleRowTrainingStd = sheetTrainingStd.createRow(rowCount);
        Row titleRowTestingStd = sheetTestingStd.createRow(rowCount);
        // min
        Row titleRowTrainingMin = sheetTrainingMin.createRow(rowCount);
        Row titleRowTestingMin = sheetTestingMin.createRow(rowCount);
        // max
        Row titleRowTrainingMax = sheetTrainingMax.createRow(rowCount);
        Row titleRowTestingMax = sheetTestingMax.createRow(rowCount);
        // execution time
        Row titleRowTrainingExecutionTime = sheetTrainingExecutionTime.createRow(rowCount);
        Row titleRowTestingExecutionTime = sheetTestingExecutionTime.createRow(rowCount);

        rowCount++;
        InstanceFixRule currentInstance = new InstanceFixRule();
        for (int j=0; j<JSS.length; j++) {
            for (int l = 0; l < LSS.length; l++) {
                // mean
                Row rowTraining = sheetTraining.createRow(rowCount);
                Row rowTesting = sheetTesting.createRow(rowCount);
                Cell cellTrainingIndex = rowTraining.createCell(0);
                cellTrainingIndex.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndex = rowTesting.createCell(0);
                cellTestingIndex.setCellValue(JSS[j] + " / " + LSS[l]);
                // std
                Row rowTrainingStd = sheetTrainingStd.createRow(rowCount);
                Row rowTestingStd = sheetTestingStd.createRow(rowCount);
                Cell cellTrainingIndexStd = rowTrainingStd.createCell(0);
                cellTrainingIndexStd.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndexStd = rowTestingStd.createCell(0);
                cellTestingIndexStd.setCellValue(JSS[j] + " / " + LSS[l]);
                // min
                Row rowTrainingMin = sheetTrainingMin.createRow(rowCount);
                Row rowTestingMin = sheetTestingMin.createRow(rowCount);
                Cell cellTrainingIndexMin = rowTrainingMin.createCell(0);
                cellTrainingIndexMin.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndexMin = rowTestingMin.createCell(0);
                cellTestingIndexMin.setCellValue(JSS[j] + " / " + LSS[l]);
                // max
                Row rowTrainingMax = sheetTrainingMax.createRow(rowCount);
                Row rowTestingMax = sheetTestingMax.createRow(rowCount);
                Cell cellTrainingIndexMax = rowTrainingMax.createCell(0);
                cellTrainingIndexMax.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndexMax = rowTestingMax.createCell(0);
                cellTestingIndexMax.setCellValue(JSS[j] + " / " + LSS[l]);
                // execution time
                Row rowTrainingExecutionTime = sheetTrainingExecutionTime.createRow(rowCount);
                Row rowTestingExecutionTime = sheetTestingExecutionTime.createRow(rowCount);
                Cell cellTrainingIndexExecutionTime = rowTrainingExecutionTime.createCell(0);
                cellTrainingIndexExecutionTime.setCellValue(JSS[j] + " / " + LSS[l]);
                Cell cellTestingIndexExecutionTime = rowTestingExecutionTime.createCell(0);
                cellTestingIndexExecutionTime.setCellValue(JSS[j] + " / " + LSS[l]);

                for (int i = 0; i < PRODUCTS.length; i++) {
                    System.out.println("instance " + PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);

                    //training
                    // mean
                    Cell cellTitleTraining = titleRowTraining.createCell(i+1);
                    cellTitleTraining.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // std
                    Cell cellTitleTrainingStd = titleRowTrainingStd.createCell(i+1);
                    cellTitleTrainingStd.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // min
                    Cell cellTitleTrainingMin = titleRowTrainingMin.createCell(i+1);
                    cellTitleTrainingMin.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // max
                    Cell cellTitleTrainingMax = titleRowTrainingMax.createCell(i+1);
                    cellTitleTrainingMax.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // execution time
                    Cell cellTitleTrainingExecutionTime = titleRowTrainingExecutionTime.createCell(i+1);
                    cellTitleTrainingExecutionTime.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);

                    double totalCostsTraining = 0.0;
                    double stdCostsTraining = 0.0;
                    double minCostsTraining = 100000000000000.00;
                    double maxCostsTraining = 0.0;
                    double totalElapsedTime = 0.0;
                    double[] trainingList = new double[nScenariosTraining];
                    String pathToCurrentScenario = pathScenariosTraining + PRODUCTS[i]+"X"+MACHINES[i]+"x"+PERIODS[i]+"/cv"+cv+"/";
                    for (int s = 0; s < nScenariosTraining; s++) {
                        System.out.println("training scenario " + s);
                        currentInstance.setup(PRODUCTS[i], MACHINES[i], PERIODS[i], s, pathToCurrentScenario, pathInstances);
                        long startTime = System.nanoTime();
                        double result = MainLotsizingFixRule.run(currentInstance, j, l);
                        long elapsedTime = System.nanoTime() - startTime;
                        totalElapsedTime += elapsedTime*0.000000001;
                        totalCostsTraining += result;
                        trainingList[s] = result;
                        // min
                        if (result < minCostsTraining){
                            minCostsTraining = result;
                        }
                        // max
                        if (result > maxCostsTraining){
                            maxCostsTraining = result;
                        }
                    }
                    // calculate standard deviation
                    stdCostsTraining = calculateStandardDeviation(trainingList);

                    // mean
                    Cell cellTraining = rowTraining.createCell(i+1);
                    cellTraining.setCellValue(totalCostsTraining / nScenariosTraining);
                    // std
                    Cell cellTrainingStd = rowTrainingStd.createCell(i+1);
                    cellTrainingStd.setCellValue(stdCostsTraining);
                    // min
                    Cell cellTrainingMin = rowTrainingMin.createCell(i+1);
                    cellTrainingMin.setCellValue(minCostsTraining);
                    // max
                    Cell cellTrainingMax = rowTrainingMax.createCell(i+1);
                    cellTrainingMax.setCellValue(maxCostsTraining);
                    // execution time
                    Cell cellTrainingExecutionTime = rowTrainingExecutionTime.createCell(i+1);
                    cellTrainingExecutionTime.setCellValue(totalElapsedTime / nScenariosTraining);


                    // testing
                    // mean
                    Cell cellTitleTesting = titleRowTesting.createCell(i+1);
                    cellTitleTesting.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // std
                    Cell cellTitleTestingStd = titleRowTestingStd.createCell(i+1);
                    cellTitleTestingStd.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // min
                    Cell cellTitleTestingMin = titleRowTestingMin.createCell(i+1);
                    cellTitleTestingMin.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // max
                    Cell cellTitleTestingMax = titleRowTestingMax.createCell(i+1);
                    cellTitleTestingMax.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);
                    // execution time
                    Cell cellTitleTestingExecutionTime = titleRowTestingExecutionTime.createCell(i+1);
                    cellTitleTestingExecutionTime.setCellValue(PRODUCTS[i] + "x" + MACHINES[i] + "x" + PERIODS[i]);

                    double totalCostsTesting = 0.0;
                    double stdCostsTesting = 0.0;
                    double minCostsTesting = 100000000000000.00;
                    double maxCostsTesting = 0.0;
                    totalElapsedTime = 0.0;
                    double[] testingList = new double[nScenariosValidation];
                    String pathToCurrentScenarioValidation = pathScenariosValidation + PRODUCTS[i]+"X"+MACHINES[i]+"x"+PERIODS[i]+"/cv"+cv+"/";
                    String pathToOptimum = pathScenariosValidation + "perfect_information/";
                    for (int s = 0; s < nScenariosValidation; s++) {
                        System.out.println("validation scenario " + s);
                        currentInstance.setup(PRODUCTS[i], MACHINES[i], PERIODS[i], s, pathToCurrentScenarioValidation, pathInstances);
                        long startTime = System.nanoTime();
                        double result = MainLotsizingFixRule.run(currentInstance, j, l);
                        long elapsedTime = System.nanoTime() - startTime;
                        totalElapsedTime += elapsedTime*0.000000001;
                        totalCostsTesting += result;
                        testingList[s] = result;
                        // min
                        if (result < minCostsTesting){
                            minCostsTesting = result;
                        }
                        // max
                        if (result > maxCostsTesting){
                            maxCostsTesting = result;
                        }
                    }
                    // calculate standard deviation
                    stdCostsTesting = calculateStandardDeviation(testingList);

                    // mean
                    Cell cellTesting = rowTesting.createCell(i+1);
                    cellTesting.setCellValue(totalCostsTesting / nScenariosValidation);
                    // std
                    Cell cellTestingStd = rowTestingStd.createCell(i+1);
                    cellTestingStd.setCellValue(stdCostsTesting);
                    // min
                    Cell cellTestingMin = rowTestingMin.createCell(i+1);
                    cellTestingMin.setCellValue(minCostsTesting);
                    // max
                    Cell cellTestingMax = rowTestingMax.createCell(i+1);
                    cellTestingMax.setCellValue(maxCostsTesting);
                    // execution time
                    Cell cellTestingExecutionTime = rowTestingExecutionTime.createCell(i+1);
                    cellTestingExecutionTime.setCellValue(totalElapsedTime / nScenariosValidation);


                }
                rowCount++;
            }
        }

        try {
            //Write the workbook in file system
            // mean
            FileOutputStream outPath = new FileOutputStream("G:/My Drive/LSJSS_uncertainty/fixed rules/ct"+ct+"/cv"+cv+"/validationset_size_"+nScenariosValidation+"/results.xlsx");
            workbook.write(outPath);
            outPath.close();
            // std
            FileOutputStream outPathStd = new FileOutputStream("G:/My Drive/LSJSS_uncertainty/fixed rules/ct"+ct+"/cv"+cv+"/validationset_size_"+nScenariosValidation+"/results_std.xlsx");
            workbookStd.write(outPathStd);
            outPathStd.close();
            // min
            FileOutputStream outPathMin = new FileOutputStream("G:/My Drive/LSJSS_uncertainty/fixed rules/ct"+ct+"/cv"+cv+"/validationset_size_"+nScenariosValidation+"/results_min.xlsx");
            workbookMin.write(outPathMin);
            outPathMin.close();
            // max
            FileOutputStream outPathMax = new FileOutputStream("G:/My Drive/LSJSS_uncertainty/fixed rules/ct"+ct+"/cv"+cv+"/validationset_size_"+nScenariosValidation+"/results_max.xlsx");
            workbookMax.write(outPathMax);
            outPathMax.close();
            // execution time
            FileOutputStream outPathExecutionTime = new FileOutputStream("G:/My Drive/LSJSS_uncertainty/fixed rules/ct"+ct+"/cv"+cv+"/validationset_size_"+nScenariosValidation+"/results_execution_time.xlsx");
            workbookExecutionTime.write(outPathExecutionTime);
            outPathExecutionTime.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

