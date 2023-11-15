package dlsjss.LSJSS.ruleInput;

import dlsjss.problem.InstanceFixRule;
import dlsjss.util.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class MainInputRule {

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
        int nScenariosTraining = 5;
        int nScenariosValidation = 30;
        int[] PRODUCTS = {6, 10, 20};
        int[] MACHINES = {6, 10, 5};
        int[] PERIODS = {20, 20, 20};

        String pathToInstances = "G:/My Drive/LSJSS_uncertainty/test instances/ct0.5/";
        String pathScenariosTraining = "G:/My Drive/LSJSS_uncertainty/scenarios_training/";
        String pathScenariosValidation = "G:/My Drive/LSJSS_uncertainty/scenarios/";


        String pathRules = "D:/OneDrive/PhD Engineering and Management/02_projects_ongoing/A novel rolling horizon heuristic/Experiment/GPHH/old_exp/run8/individuals_validation.xlsx";

        String JSR = ExcelReader.readStringValue(pathRules, "JSS");
        String LSR = ExcelReader.readStringValue(pathRules, "LSS");

        System.out.println(JSR);
        System.out.println(LSR);

        double cv = 0.2;
        double ct = 0.5;



        for (int i = 0; i < PRODUCTS.length; i++) {
            double totalResults = 0;
            String pathToCurrentScenario = pathScenariosValidation + PRODUCTS[i] + "X" + MACHINES[i] + "x" + PERIODS[i] + "/cv" + cv + "/";
            for (int s = 0; s < nScenariosValidation; s++) {
                InstanceFixRule currentInstance = new InstanceFixRule();
                currentInstance.setup(PRODUCTS[i], MACHINES[i], PERIODS[i], s, pathToCurrentScenario, pathToInstances);
                double result = MainLotsizingRuleInput.run(currentInstance, JSR, LSR);
                totalResults += result;
            }
            System.out.println("average result: " + (totalResults/nScenariosValidation));
        }


    }
}
