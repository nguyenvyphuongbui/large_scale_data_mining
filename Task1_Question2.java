import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class Task1_Question2 {

    public static void main(String[] args) throws IOException {
        // Specify input files for yes-class and no-class datasets
        String input_file_yes = "C:/IFN645/CapstoneAssignment/dataset/campaign_yes.arff";
        String input_file_no = "C:/IFN645/CapstoneAssignment/dataset/campaign_no.arff";

        // Specify output files for top 10 frequent size-3 patterns
        String output_fp_yes = "C:/IFN645/CapstoneAssignment/output/frequent_patterns_yes.txt";
        String output_fp_no = "C:/IFN645/CapstoneAssignment/output/frequent_patterns_no.txt";

        // Specify a file for the converted file
        String input_converted_yes = "C:/IFN645/CapstoneAssignment/dataset/campaign_yes_converted.txt";
        String input_converted_no = "C:/IFN645/CapstoneAssignment/dataset/campaign_no_converted.txt";

        // Create an object of TransactionDatabaseConverter for yes-class dataset
        TransactionDatabaseConverter converter_yes = new TransactionDatabaseConverter();
        converter_yes.convertARFFandReturnMap(input_file_yes, input_converted_yes, Integer.MAX_VALUE);

        // Create an object of TransactionDatabaseConverter for no-class dataset
        TransactionDatabaseConverter converter_no = new TransactionDatabaseConverter();
        converter_no.convertARFFandReturnMap(input_file_no, input_converted_no, Integer.MAX_VALUE);

        // Create an object of FP-Growth algorithm
        AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();

        // Set a maximum size for patterns
        algo_FPGrowth.setMaximumPatternLength(3);

        // Specify minimum support for campaign_yes
        double minsup_yes = 0.57;
        // Specify minimum support for campaign_no
        double minsup_no = 0.53;

        // Run FP-Growth algorithm to generate patterns for yes-class dataset
        algo_FPGrowth.runAlgorithm(input_converted_yes, output_fp_yes, minsup_yes);
        algo_FPGrowth.printStats();

        // Run FP-Growth algorithm to generate patterns for no-class dataset
        algo_FPGrowth.runAlgorithm(input_converted_no, output_fp_no, minsup_no);
        algo_FPGrowth.printStats();

        // Specify the final output files with original item names
        String final_output_fp_yes = "C:/IFN645/CapstoneAssignment/output/final_frequent_patterns_yes.txt";
        String final_output_fp_no = "C:/IFN645/CapstoneAssignment/output/final_frequent_patterns_no.txt";

        // Convert outputs to include the original names for the items
        ResultConverter output_converter = new ResultConverter();
        output_converter.convert(input_converted_yes, output_fp_yes, final_output_fp_yes, null);
        output_converter.convert(input_converted_no, output_fp_no, final_output_fp_no, null);

    }
}