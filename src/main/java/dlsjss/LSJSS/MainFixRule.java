package dlsjss.LSJSS;

import dlsjss.LSJSS.fixRule.MainLotsizingFixRule;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import dlsjss.problem.Instance;

import java.io.IOException;

public class MainFixRule {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        long startTime = System.nanoTime();
        Instance currentInstance = new Instance();
        currentInstance.setup(6,6,5,10);
        double totalCosts = MainLotsizingFixRule.run(currentInstance);
        System.out.println(totalCosts);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in sec: "
                + elapsedTime*0.000000001);
        /*
        int[] randomSeeds = new int[5];
        randomSeeds[0] = 10;
        randomSeeds[1] = 58;
        randomSeeds[2] = 139;
        randomSeeds[3] = 214;
        randomSeeds[4] = 369;
        double totalresult = 0.0;
        for (int i=0; i<5; i++){
            double totalCosts = MainLotsizingFixRule.run(6,6,5, 0.46, randomSeeds[i]);
            System.out.println(totalCosts);
            totalresult += totalCosts;
        }
        System.out.println(totalresult/5);
         */
    }

}
