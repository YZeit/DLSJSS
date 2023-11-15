package dlsjss.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;


public class ExpressionEvaluator {

    public static double executeLSR(double currentAHC, double currentBSV, double currentSC, double currentCF, double currentNPRC,
                                  double currentHCC, double currentNPRE, double currentCU, double currentADSHC, double currentAPDR,
                                  double currentNI, double currentLPH, double currentCP, double currentHC, double currentAPD,
                                  double currentAPDK, double currentTBO, double currentSHC, double currentSHCN, double currentC,
                                  double currentCLS, double currentPLE, double currentRCC, double currentADSHCE, double currentBC,
                                  String currentRule) {
        // Define the expression to parse and evaluate
        String expression = currentRule;

        double result = 0.0;

        try {
            expression = expression.replaceAll("min", "%%%");
            expression = expression.replaceAll("max", "&&&");
            // Create a new expression using the ExpressionBuilder
            Expression exp = new ExpressionBuilder(expression)
                    .variable("AHC")
                    .variable("BSV")
                    .variable("SC")
                    .variable("CF")
                    .variable("NPRC")
                    .variable("HCC")
                    .variable("NPRE")
                    .variable("CU")
                    .variable("ADSHC")
                    .variable("APDR")
                    .variable("NI")
                    .variable("LPH")
                    .variable("CP")
                    .variable("HC")
                    .variable("APD")
                    .variable("APDK")
                    .variable("TBO")
                    .variable("SHC")
                    .variable("SHCN")
                    .variable("C")
                    .variable("CLS")
                    .variable("PLE")
                    .variable("RCC")
                    .variable("ADSHCE")
                    .variable("BC")
                    .function(ifte)
                    .operator(max)
                    .operator(min)
                    .operator(protectedDiv)
                    .build();

            // Set the variables and their values
            exp.setVariable("AHC", currentAHC);
            exp.setVariable("BSV", currentBSV);
            exp.setVariable("SC", currentSC);
            exp.setVariable("CF", currentCF);
            exp.setVariable("NPRC", currentNPRC);
            exp.setVariable("HCC", currentHCC);
            exp.setVariable("NPRE", currentNPRE);
            exp.setVariable("CU", currentCU);
            exp.setVariable("ADSHC", currentADSHC);
            exp.setVariable("APDR", currentAPDR);
            exp.setVariable("NI", currentNI);
            exp.setVariable("LPH", currentLPH);
            exp.setVariable("CP", currentCP);
            exp.setVariable("HC", currentHC);
            exp.setVariable("APD", currentAPD);
            exp.setVariable("APDK", currentAPDK);
            exp.setVariable("TBO", currentTBO);
            exp.setVariable("SHC", currentSHC);
            exp.setVariable("SHCN", currentSHCN);
            exp.setVariable("C", currentC);
            exp.setVariable("CLS", currentCLS);
            exp.setVariable("PLE", currentPLE);
            exp.setVariable("RCC", currentRCC);
            exp.setVariable("ADSHCE", currentADSHCE);
            exp.setVariable("BC", currentBC);


            // Check if the expression is valid
            ValidationResult validationResult = exp.validate();
            if (!validationResult.isValid()) {
                System.out.println("Error parsing expression: " + validationResult.getErrors());
            }

            // Evaluate the expression
            result = exp.evaluate();
        } catch (UnknownFunctionOrVariableException e) {
            System.out.println("Error evaluating expression: Unknown variable or function");
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
        return result;
    }




    public static double executeJSR(double currentPT, double currentRT, double currentRPT, double currentRNO, double currentDD,
                               double currentRTO, double currentPTN, double currentSL, double currentWT, double currentAPTQ,
                               double currentNJQ, double currentWINQ, double currentCT, String currentRule) {
        // Define the expression to parse and evaluate
        String expression = currentRule;

        double result = 0.0;

        try {
            expression = expression.replaceAll("min", "%%%");
            expression = expression.replaceAll("max", "&&&");
            // Create a new expression using the ExpressionBuilder
            Expression exp = new ExpressionBuilder(expression)
                    .variable("PT")
                    .variable("RT")
                    .variable("RPT")
                    .variable("RNO")
                    .variable("DD")
                    .variable("RTO")
                    .variable("PTN")
                    .variable("SL")
                    .variable("WT")
                    .variable("APTQ")
                    .variable("NJQ")
                    .variable("WINQ")
                    .variable("CT")
                    .function(ifte)
                    .operator(max)
                    .operator(min)
                    .operator(protectedDiv)
                    .build();

            // Set the variables and their values
            exp.setVariable("PT", currentPT);
            exp.setVariable("RT", currentRT);
            exp.setVariable("RPT", currentRPT);
            exp.setVariable("RNO", currentRNO);
            exp.setVariable("DD", currentDD);
            exp.setVariable("RTO", currentRTO);
            exp.setVariable("PTN", currentPTN);
            exp.setVariable("SL", currentSL);
            exp.setVariable("WT", currentWT);
            exp.setVariable("APTQ", currentAPTQ);
            exp.setVariable("NJQ", currentNJQ);
            exp.setVariable("WINQ", currentWINQ);
            exp.setVariable("CT", currentCT);

            // Check if the expression is valid
            ValidationResult validationResult = exp.validate();
            if (!validationResult.isValid()) {
                System.out.println("Error parsing expression: " + validationResult.getErrors());
            }

            // Evaluate the expression
            result = exp.evaluate();
        } catch (UnknownFunctionOrVariableException e) {
            System.out.println("Error evaluating expression: Unknown variable or function");
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
        return result;
    }

    // Define your custom function
    static Function ifte = new Function("ifte", 3) {
        @Override
        public double apply(double... args) {
            // Implement your custom function logic here
            double condition = args[0];
            double returnIfTrue = args[1];
            double returnIfNotTrue = args[2];
            double returnValue = 0.0;
            if (condition>0) {
                returnValue = returnIfTrue;}
            else {
                returnValue = returnIfNotTrue;
            }
            return returnValue;
        }
    };

    static Operator max = new Operator("&&&", 2, true, 500) {
        @Override
        public double apply(double... args) {
            // Implement your custom function logic here
            return Math.max(args[0], args[1]);
        }
    };

    static Operator min = new Operator("%%%", 2, true, 500) {
        @Override
        public double apply(double... args) {
            // Implement your custom function logic here
            return Math.min(args[0], args[1]);
        }
    };

    static Operator protectedDiv = new Operator("/", 2, true, 500) {
        @Override
        public double apply(double... args) {
            // Implement your custom function logic here
            double returnValue = 0.0;
            if (args[1] == 0){
                returnValue = 1;
            } else {
                returnValue = args[0] / args[1];
            }
            return returnValue;
        }
    };

}
