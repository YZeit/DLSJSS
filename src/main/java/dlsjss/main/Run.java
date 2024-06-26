package dlsjss.main;

import ec.Evolve;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;


public class Run {

    public static String[] concatWithStream(String[] array1, String[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(size -> (String[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }

    public static void execute(String pathToParams, String pathToFiles, String pathToInstances,
                               String pathToInstancesTraining, String pathToInstancesValidation,
                               int numberOfJobs, int nGenerations, int nPopulation, int[] nProducts,
                               int[] nMachines, int[] nPeriods, boolean randTrain, int nTrainSetSize,
                               int nTrainingBatch, int nValidationSetSize, double cv, double ct) {
        int nTrainSetVariety = nProducts.length;
        int nValidationSetVariety = nProducts.length;
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
                "-p", ("products="+nProducts),
                "-p", ("machines="+nMachines),
                "-p", ("periods="+nPeriods),
                "-p", ("problem-size="+nProducts.length),
                "-p", ("trainingset.instance-size="+nTrainSetVariety),
                "-p", ("validationset.instance-size="+nValidationSetVariety),
                "-p", ("random-training-instance-selection="+randTrain),
                "-p", ("training-batch-size="+nTrainingBatch),
                "-p", ("trainingset.scenariosize="+nTrainSetSize),
                "-p", ("validationset.scenariosize="+nValidationSetSize),
                "-p", ("coefficient-variant="+cv),
                "-p", ("capacity-tightness="+ct),
        };
        String[] trainSetConfig = new String[nTrainSetVariety*2*6];
        for (int i=0; i<nTrainSetVariety; i++) {
            trainSetConfig[i*2*6] = "-p";
            trainSetConfig[i*2*6+1] = ("trainingset.products-list." + i + "=" + nProducts[i]);
            trainSetConfig[i*2*6+2] = "-p";
            trainSetConfig[i*2*6+3] = ("trainingset.machines-list." + i + "=" + nMachines[i]);
            trainSetConfig[i*2*6+4] = "-p";
            trainSetConfig[i*2*6+5] = ("trainingset.periods-list." + i + "=" + nPeriods[i]);
            trainSetConfig[i*2*6+6] = "-p";
            trainSetConfig[i*2*6+7] = ("validationset.products-list." + i + "=" + nProducts[i]);
            trainSetConfig[i*2*6+8] = "-p";
            trainSetConfig[i*2*6+9] = ("validationset.machines-list." + i + "=" + nMachines[i]);
            trainSetConfig[i*2*6+10] = "-p";
            trainSetConfig[i*2*6+11] = ("validationset.periods-list." + i + "=" + nPeriods[i]);
        }
        String[] runConfigFinal = concatWithStream(runConfig, trainSetConfig);
        System.out.println(Arrays.toString(runConfigFinal));
        Evolve.main(runConfigFinal);
    }
}