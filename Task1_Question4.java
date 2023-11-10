import java.io.FileNotFoundException;
import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori_close.AlgoAprioriClose;
import ca.pfv.spmf.algorithms.frequentpatterns.charm.AlgoCharm_Bitset;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPClose;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPMax;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class Task1_Question4 {
    
    public static void main(String[] args) {
        String input_dataset = "C:/IFN645/CapstoneAssignment/dataset/campaign_converted.txt";
        String output_path = "C:/IFN645/CapstoneAssignment/output/";

        // Specify minimum support values
        double[] minsups = {0.2, 0.3, 0.4};

        for (double minsup : minsups) {
            String output_fp_Fpt = output_path + "Task1.4_fp_Fpt_minSup_" + minsup + ".txt";
            String output_fcp_Apriori = output_path + "Task1.4_fcp_Apriori_minSup_" + minsup + ".txt";
            String output_fcp_Fpt = output_path + "Task1.4_fcp_Fpt_minSup_" + minsup + ".txt";
            String output_fcp_Charm = output_path + "Task1.4_fcp_Charm_minSup_" + minsup + ".txt";

            AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();
            AlgoAprioriClose algo_AprioriClose = new AlgoAprioriClose();
            AlgoFPClose algo_FCP_Growth = new AlgoFPClose();
            AlgoCharm_Bitset algo_FCP_Charm = new AlgoCharm_Bitset();

            try {
                algo_FPGrowth.runAlgorithm(input_dataset, output_fp_Fpt, minsup);
                algo_FPGrowth.printStats();

                algo_AprioriClose.runAlgorithm(minsup, input_dataset, output_fcp_Apriori);
                algo_AprioriClose.printStats();

                algo_FCP_Growth.runAlgorithm(input_dataset, output_fcp_Fpt, minsup);
                algo_FCP_Growth.printStats();

                TransactionDatabase tdb = new TransactionDatabase();
                tdb.loadFile(input_dataset);

                algo_FCP_Charm.runAlgorithm(output_fcp_Charm, tdb, minsup, false, 10000);
                algo_FCP_Charm.printStats();

                String final_output_fp_Fpt = output_path + "Task1.4_final_fp_Fpt_minSup_" + minsup + ".txt";
                String final_output_fcp_Apriori = output_path + "Task1.4_final_fcp_Apriori_minSup_" + minsup + ".txt";
                String final_output_fcp_Fpt = output_path + "Task1.4_final_fcp_Fpt_minSup_" + minsup + ".txt";
                String final_output_fcp_Charm = output_path + "Task1.4_final_fcp_Charm_minSup_" + minsup + ".txt";

                ResultConverter output_converter = new ResultConverter();
                output_converter.convert(input_dataset, output_fp_Fpt, final_output_fp_Fpt, null);
                output_converter.convert(input_dataset, output_fcp_Apriori, final_output_fcp_Apriori, null);
                output_converter.convert(input_dataset, output_fcp_Fpt, final_output_fcp_Fpt, null);
                output_converter.convert(input_dataset, output_fcp_Charm, final_output_fcp_Charm, null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
