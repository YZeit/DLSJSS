package dlsjss.main;

import ec.Evolve;


public class Main {
    public static void main(String[] args) {
        String pathToFiles = "D:/IntelliJProjects/LSJSSP_under_uncertainty/results/";
        String pathToParams = "D:/IntelliJProjects/LSJSSP_under_uncertainty/params/parameter_coevolve.params";
        String pathToInstances = "D:/OneDrive/PhD Engineering and Management/02_projects_ongoing/A novel rolling horizon heuristic/Experiment/test instances/";
        String pathToInstancesTraining = "D:/OneDrive/PhD Engineering and Management/02_projects_ongoing/A novel rolling horizon heuristic/Experiment/scenarios_training/rs10/6x6x5/cv0,5/";
        String pathToInstancesValidation = "D:/OneDrive/PhD Engineering and Management/02_projects_ongoing/A novel rolling horizon heuristic/Experiment/scenarios/rs10/6x6x5/cv0,5/";

        int nJobs = 1;
        int nGenerations = 200;
        int nPopulation = 500;

        Run.execute(pathToParams, pathToFiles, pathToInstances, pathToInstancesTraining, pathToInstancesValidation, nJobs, nGenerations, nPopulation);
        System.out.println("successfully started run");


    }
}