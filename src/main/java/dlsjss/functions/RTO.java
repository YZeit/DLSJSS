package dlsjss.functions;

import dlsjss.main.DoubleData;
import dlsjss.main.LSJSS_GPHH;
import ec.*;
import ec.gp.*;

public class RTO extends GPNode
{
    public String toString() { return "RTO"; }

    public int expectedChildren() { return 0; }

    public void eval(final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem)
    {
        DoubleData rd = ((DoubleData)(input));
        rd.x = ((LSJSS_GPHH)problem).currentRTO;
    }
}

