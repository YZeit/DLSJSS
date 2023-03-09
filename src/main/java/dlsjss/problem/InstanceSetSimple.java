package dlsjss.problem;

import ec.EvolutionState;
import ec.util.Parameter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class InstanceSetSimple {
    public static final String P_SIZE = "size";
    public static final String P_RANDOMSEED = "randomseed";
    public static final String P_PRODUCTS = "products";
    public static final String P_MACHINES = "machines";
    public static final String P_PERIODS = "periods";

    public int size;
    public int nProducts;
    public int nMachines;
    public int nPeriods;
    public double capacityTightness;
    public int[] randomSeeds;
    public Instance[] instances;

    public Object clone()
    {
        try { return super.clone(); }
        catch (CloneNotSupportedException e)
        { throw new InternalError(); } // never happens
    }

    public void setup(final EvolutionState state, final Parameter base, String set) throws IOException, InvalidFormatException {
        Parameter p;

        // get size of training/validation set
        p = base.push(P_SIZE);
        size = state.parameters.getInt(p,null,1);
        if (size==0) // uh oh
            state.output.fatal("training/validation set size must be >0.\n",base.push(P_SIZE));
        CoevolutionState CCstate = (CoevolutionState) state;


        // get number of Products from the parameters
        p = new Parameter(P_PRODUCTS);
        if (state.parameters.exists(p, null))
        {
            nProducts = state.parameters.getInt(p, null, 1);  // 0 would be UDEFINED
            if (nProducts <= 0)
                state.output.fatal("If defined, the number of Products must be an integer >= 1.", p, null);
        }

        // get number of Machines from the parameters
        p = new Parameter(P_MACHINES);
        if (state.parameters.exists(p, null))
        {
            nMachines = state.parameters.getInt(p, null, 1);  // 0 would be UDEFINED
            if (nMachines <= 0)
                state.output.fatal("If defined, the number of Machines must be an integer >= 1.", p, null);
        }

        // get number of Periods from the parameters
        p = new Parameter(P_PERIODS);
        if (state.parameters.exists(p, null))
        {
            nPeriods = state.parameters.getInt(p, null, 1);  // 0 would be UDEFINED
            if (nPeriods <= 0)
                state.output.fatal("If defined, the number of Periods must be an integer >= 1.", p, null);
        }

        randomSeeds = new int[size];
        // assign random seeds to the instances
        if (set == "training") {
            CCstate.trainingSet.instances = new Instance[size];
            for (int x = 0; x < size; x++) {
                p = base.push(P_RANDOMSEED).push("" + x);
                randomSeeds[x] = state.parameters.getInt(p, null, 0);
                CCstate.trainingSet.instances[x] = new Instance();
                CCstate.trainingSet.instances[x].setup(nProducts, nMachines, nPeriods, randomSeeds[x]);
            }
        }
        if (set == "validation") {
            CCstate.validationSet.instances = new Instance[size];
            for (int x = 0; x < size; x++) {
                p = base.push(P_RANDOMSEED).push("" + x);
                randomSeeds[x] = state.parameters.getInt(p, null, 0);
                CCstate.validationSet.instances[x] = new Instance();
                CCstate.validationSet.instances[x].setup(nProducts, nMachines, nPeriods, randomSeeds[x]);
            }
        }
        if (set != "training" && set != "validation"){
            CCstate.output.fatal("Set must be either training or validation", p, null);
            }
    }
}
