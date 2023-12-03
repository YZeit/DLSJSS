package dlsjss.main;

public class main {
    public static void main(String[] args) {

        int nJobs = 10;
        int nGenerations = 100;
        int nPopulation = 200;
        int nTrainSetSize = 10;
        int nValidationSetSize = 30;
        double cv = 0.2;    // Coefficient Variant
        double ct = 0.5;    // Capacity Tightness
        int[] nProducts = {6, 10, 20}; // here I can decide what type of instances I want to consider (also possible to consider different types in a single run)
        int[] nMachines = {6, 10, 5};  // here I can decide what type of instances I want to consider (also possible to consider different types in a single run)
        int[] nPeriods = {20, 20, 20};  // here I can decide what type of instances I want to consider (also possible to consider different types in a single run)
        boolean randTrain = true; // this is a parameter that can be chosen to randomly change the train instances after each generation (testphase, not sure if it is working)
        int nTrainBatch = 5;

        String pathToFiles = "G:/My Drive/LSJSS_uncertainty/results/GPHH/results99/";
        String pathToParams = "G:/My Drive/LSJSS_uncertainty/params/parameter_coevolve.params";
        String pathToInstances = "G:/My Drive/LSJSS_uncertainty/test instances/";
        String pathToInstancesTraining = "G:/My Drive/LSJSS_uncertainty/scenarios_training/";
        String pathToInstancesValidation = "G:/My Drive/LSJSS_uncertainty/scenarios/";

        Run.execute(pathToParams, pathToFiles, pathToInstances, pathToInstancesTraining, pathToInstancesValidation,
                nJobs, nGenerations, nPopulation, nProducts, nMachines, nPeriods, randTrain, nTrainSetSize, nTrainBatch,
                nValidationSetSize, cv, ct);
        System.out.println("successfully started run");

    }
}
