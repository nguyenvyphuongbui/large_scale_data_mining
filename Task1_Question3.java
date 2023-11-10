import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPMax;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class Task1_Question3 {

    public void doMaxFp(String input_dataset, String output_path, double minsup, String className) {
        // Specify output files based on class name
        String output = output_path + "Task1.3_" + className + "_fpMax.txt";
        String final_output = output_path + "Task1.3_" + className + "_final_fpMax.txt";

        try {
            // create an object of AlgoFPMax
            AlgoFPMax algo_FpMax = new AlgoFPMax();

            algo_FpMax.runAlgorithm(input_dataset, output, minsup);
            algo_FpMax.printStats();

            // Convert outputs to include item names
            convert_output(input_dataset, output, final_output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convert_output(String input_dataset, String output, String final_output) {
        try {
            // Convert outputs to include item names
            ResultConverter output_converter = new ResultConverter();
            output_converter.convert(input_dataset, output, final_output, null);

            // Read the generated itemsets
            List<String> itemsets = Files.readAllLines(Paths.get(final_output));

            // Sort the itemsets based on support (frequency) in descending order
            Collections.sort(itemsets, new Comparator<String>() {
                @Override
                public int compare(String itemset1, String itemset2) {
                    // Extract support from the itemset (assuming the support is the last value in the line)
                    double support1 = Double.parseDouble(itemset1.substring(itemset1.lastIndexOf(' ') + 1));
                    double support2 = Double.parseDouble(itemset2.substring(itemset2.lastIndexOf(' ') + 1));
                    return Double.compare(support2, support1);
                }
            });

            // Print all maximal frequent itemsets
            for (String itemset : itemsets) {
                System.out.println("Maximal Frequent Itemset: " + itemset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	// Specify input datasets
        String input_dataset_yes = "C:/IFN645/CapstoneAssignment/dataset/campaign_yes_converted.txt";
        String input_dataset_no = "C:/IFN645/CapstoneAssignment/dataset/campaign_no_converted.txt";
        // Specify output path
        String output_path = "C:/IFN645/CapstoneAssignment/output/";
        // Specify minimum support for yes and no classes
        double minsup_yes = 0.5; // Minimum support for the "yes" class
        double minsup_no = 0.535; // Minimum support for the "no" class

        // Call method doFpMax() to generate maximal patterns for both datasets
        Task1_Question3 pattern_mining = new Task1_Question3();
        pattern_mining.doMaxFp(input_dataset_yes, output_path, minsup_yes, "yes");
        pattern_mining.doMaxFp(input_dataset_no, output_path, minsup_no, "no");
    }
}