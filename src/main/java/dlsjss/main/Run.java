package dlsjss.main;

import ec.Evolve;


public class Run {
    public static void execute(String pathToParams, String pathToFiles, String pathToInstances, String pathToInstancesTraining, String pathToInstancesValidation, int numberOfJobs, int nGenerations, int nPopulation) {
        String[] runConfig = new String[] {
                Evolve.A_FILE, pathToParams,
                "-p", ("stat.file=$"+pathToFiles+"out.stat"),
                "-p", ("jobs="+numberOfJobs),
                "-p", ("generations="+nGenerations),
                "-p", ("pop.subpop.0.size="+nPopulation),
                "-p", ("pop.subpop.1.size="+nPopulation),
                "-p", ("path-results="+pathToFiles+nGenerations+"x"+nPopulation+"/"),
                "-p", ("path-instances="+pathToInstances),
                "-p", ("path-instances-training="+pathToInstancesTraining),
                "-p", ("path-instances-validation="+pathToInstancesValidation),
        };
        Evolve.main(runConfig);


    }
}