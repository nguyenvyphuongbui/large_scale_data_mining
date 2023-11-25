import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class Task1_Question1 {

    public static void main(String[] args) throws IOException {
        // Specify input file for the original input file
        String input_file = "C:/IFN645/CapstoneAssignment/dataset/campaign.arff";
        // Specify output files
        String output_fp_Apriori = "C:/IFN645/CapstoneAssignment/output/campaign_Apriori.txt";
        String output_fp_Fpt = "C:/IFN645/CapstoneAssignment/output/campaign_Fpt.txt";

        // Specify a file for the converted file
        String input_converted = "C:/IFN645/CapstoneAssignment/dataset/campaign_converted.txt";

        // Create an object of TransactionDatabaseConverter
        TransactionDatabaseConverter converter = new TransactionDatabaseConverter();

        // Convert the ARFF file to text file
        converter.convertARFFandReturnMap(input_file, input_converted, Integer.MAX_VALUE);

        // Specify minimum supports
        double[] minsups = { 0.2, 0.3, 0.4 };

        for (double minsup : minsups) {
            // Create objects of pattern mining algorithms
            AlgoApriori algo_Apri = new AlgoApriori();
            AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();

            // Set a maximum size for patterns
            algo_Apri.setMaximumPatternLength(3);
            algo_FPGrowth.setMaximumPatternLength(3);

            // Run algorithms to generate patterns
            algo_Apri.runAlgorithm(minsup, input_converted, output_fp_Apriori);
            algo_Apri.printStats();

            algo_FPGrowth.runAlgorithm(input_converted, output_fp_Fpt, minsup);
            algo_FPGrowth.printStats();

            // Specify the final output files
            String final_output_fp_apri = "C:/IFN645/CapstoneAssignment/output/final_campaign_apriori_minSup"
                    + minsup + ".txt";
            String final_output_fp_fpt = "C:/IFN645/CapstoneAssignment/output/final_campaign_fpt_minSup"
                    + minsup + ".txt";

            // Convert outputs to include the original names for the items
            ResultConverter output_converter = new ResultConverter();

            output_converter.convert(input_converted, output_fp_Apriori, final_output_fp_apri, null);
            output_converter.convert(input_converted, output_fp_Fpt, final_output_fp_fpt, null);
        }

    }
}
