package dlsjss.problem;

import dlsjss.util.ExcelReader;
import ec.EvolutionState;
import ec.util.Parameter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Arrays;

public class Instance {
    public int[][] demands;
    public int[][] actualDemands;
    public int[][] routings;
    public double[][] processingTime;
    public double[] capacity;
    public double[] productionCosts;
    public int[] holdingCosts;
    public int[] backlogCosts;
    public int[] setupCosts;
    public int[] setupTimes;
    public int NPRODUCTS;
    public int NMACHINES;
    public int NPERIODS;
    public int NSCENARIO;
    public double capacityTightness;
    public int RANDOMSEED;
    public double optimum;
    public double optimum_deterministic;
    public double optimum_stochastic;
    public double[] solution;
    public double[] solution_deterministic;
    public double[] solution_stochastic;

    public Object clone()
    {
        try { return super.clone(); }
        catch (CloneNotSupportedException e)
        { throw new InternalError(); } // never happens
    }

    public void setup(EvolutionState state, int nProducts, int nMachines, int nPeriods, int RandomSeed, int nScenario, String path) throws IOException, InvalidFormatException {
        NPRODUCTS = nProducts;
        NMACHINES = nMachines;
        NPERIODS = nPeriods;
        RANDOMSEED = RandomSeed;
        NSCENARIO = nScenario;
        //this.capacityTightness = CapacityTightness;
        Parameter p = new Parameter("path-instances");
        String pathInstances = state.parameters.getString(p, (Parameter) null);
        p = new Parameter("coefficient-variant");
        double cv = state.parameters.getDouble(p, (Parameter) null);
        p = new Parameter("capacity-tightness");
        double ct = state.parameters.getDouble(p, (Parameter) null);
        //System.out.println("CV: "+cv);
        //System.out.println("CT: "+ct);
        // load input data from excel
        String FILE_PATH = pathInstances + "ct"+ct+"/" + nProducts+"x"+nMachines+"x"+nPeriods+".xlsx";
        demands = ExcelReader.readInputDataArrayInt(FILE_PATH, "demands");
        //System.out.println("Demand: " + "scenario" + nScenario + Arrays.deepToString(demands));
        actualDemands = new int[nProducts][nPeriods];
        for (int i =0; i<nPeriods; i++){
            String FILE_PATH_SCENARIO = path + nProducts + "x" + nMachines + "x" + nPeriods + "/cv"+cv+"/stage_" + i + "/scenario_" + nScenario + ".xlsx";
            int[] currentDemand = ExcelReader.readInputDataListInt(FILE_PATH_SCENARIO, "demands");
            for (int j =0; j<nProducts; j++){
                actualDemands[j][i] = currentDemand[j];
            }
        }
        //System.out.println("loaded Demand: " + "scenario" + nScenario + Arrays.deepToString(actualDemands));
        routings = ExcelReader.readInputDataArrayInt(FILE_PATH, "routings");
        processingTime = ExcelReader.readInputDataArrayDouble(FILE_PATH, "processing_time");
        capacity = ExcelReader.readInputDataListDouble(FILE_PATH, "period_length");
        String FILE_PATH_OPTIMUM = path + "perfect_information/ct" + ct + "/cv" + cv + "/" + nProducts + "x" + nMachines + "x" + nPeriods + "/results_progress_scenario" + nScenario + ".xlsx";
        solution = ExcelReader.readInputDataListDouble(FILE_PATH_OPTIMUM, "incumbent solution");
        // Retrieve the last value
        int lastIndex = solution.length;
        optimum = solution[lastIndex-1];
        // load static data
        int[] staticData = ExcelReader.readInputDataListInt(FILE_PATH, "static_parameters");
        setupTimes = new int[nProducts];
        setupCosts = new int[nProducts];
        productionCosts = new double[nProducts];
        holdingCosts = new int[nProducts];
        backlogCosts = new int[nProducts];
        // initialize setup times
        for (int i =0; i<nProducts; i++){
            setupTimes[i] = staticData[0]; // for all operations equal 10
        }

        // initialize setup costs
        for (int i =0; i<nProducts; i++){
            //setupCosts[i] = getRandomNumber(5,100,randomobj);
            setupCosts[i] = staticData[1];
        }

        // initialize production costs
        for (int i =0; i<nProducts; i++){
            productionCosts[i] = staticData[2];
        }

        // initialize holding costs
        for (int i =0; i<nProducts; i++){
            holdingCosts[i] = staticData[3];
        }

        // initialize holding costs
        for (int i =0; i<nProducts; i++){
            backlogCosts[i] = staticData[4];
        }
    }

    public void print(){
        System.out.println("random seed: " + RANDOMSEED);
        System.out.println("demands: " + Arrays.deepToString(demands));
        System.out.println("actual demands: " + Arrays.deepToString(actualDemands));
        System.out.println("routings: " + Arrays.deepToString(routings));
        System.out.println("processing times: " + Arrays.deepToString(processingTime));
        System.out.println("capacities: " + Arrays.toString(capacity));
        System.out.println("setup times: " + Arrays.toString(setupTimes));
        System.out.println("setup costs: " + Arrays.toString(setupCosts));
        System.out.println("production costs: " + Arrays.toString(productionCosts));
        System.out.println("holding costs: " + Arrays.toString(holdingCosts));
        System.out.println("Solution: "+optimum);
    }
}
