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

public class test {

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
        String pathScenariosTraining = "C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/scenarios_training/";
        String pathScenariosValidation = "C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/scenarios/";
        String pathInstances = "C:/Users/José Rui Figueira/IntelliJProjects/LSJSSP_under_uncertainty/test instances/";


        String pathToCurrentScenario = pathScenariosValidation + 6+"X"+6+"x"+20+"/cv0.2/";
        InstanceFixRule currentInstance = new InstanceFixRule();
        currentInstance.setup(6, 6, 20, 10, 0, pathToCurrentScenario, pathInstances);
        double result = MainLotsizingFixRule.run(currentInstance, 0, 1);
        System.out.println(result);

    }
}




