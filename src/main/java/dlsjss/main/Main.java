package dlsjss.main;

import ec.Evolve;


public class Main {
    public static void main(String[] args) {
        String pathToFiles = "D:/IntelliJProjects/LSJSSP_under_uncertainty/DLSJSS/results/";
        String pathToParams = "D:/IntelliJProjects/LSJSSP_under_uncertainty/DLSJSS/params/parameter_coevolve.params";
        String pathToInstances = "D:/IntelliJProjects/LSJSSP_under_uncertainty/DLSJSS/data/";
        int nJobs = 10;
        int nGenerations = 200;
        int nPopulation = 500;

        Run.execute(pathToParams, pathToFiles, pathToInstances, nJobs, nGenerations, nPopulation);
        System.out.println("successfully started run");


    }
}