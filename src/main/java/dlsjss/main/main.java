package dlsjss.main;

public class main {
    public static void main(String[] args) {
        String pathToFiles = "C:/Users/DLSJSS/results/";
        String pathToParams = "C:/Users/DLSJSS/params/parameter_coevolve.params";
        String pathToInstances = "C:/Users/DLSJSS/test instances/";
        String pathToInstancesTraining = "C:/Users/DLSJSS/scenarios_training/";
        String pathToInstancesValidation = "C:/Users/DLSJSS/scenarios/";

        int nJobs = 10;
        int nGenerations = 200;
        int nPopulation = 10;
        int[] nProducts = {6, 6, 6, 10, 10, 10, 20, 20, 20};
        int[] nMachines = {6, 6, 6, 10, 10 ,10, 5, 5, 5};
        int[] nPeriods = {5, 10, 20, 5, 10, 20, 5, 10, 20};
        int nTrainSetVariety = nProducts.length;
        int nValidationSetVariety = nProducts.length;
        boolean randTrain = false;
        int nTrainSetSize = 5;
        int nValidationSetSize = 10;

        Run.execute(pathToParams, pathToFiles, pathToInstances, pathToInstancesTraining, pathToInstancesValidation,
                nJobs, nGenerations, nPopulation, nProducts, nMachines, nPeriods, nTrainSetVariety,
                nValidationSetVariety, randTrain, nTrainSetSize, nValidationSetSize);
        System.out.println("successfully started run");


    }
}
