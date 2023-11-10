import java.io.IOException;

import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.AlgoTopKClassRules;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.Database;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class Task1_Question5 {
	
	public void topK_classRules(String input_dataset, String output_path, double minconf, int top_k, int[] itemToBeUsedAsConsequent) {
		//Specify output files
		String output = output_path + "Task1.5_topK_classRules.txt" ;
		String final_output = output_path + "Task1.5_final_topK_classRules.txt" ;
		
		// To generate rules, we need to create a database from the input dataset
		Database db = new Database(); 
		try {
			db.loadFile(input_dataset);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Create an object of rule mining algorithm
		AlgoTopKClassRules topK_classRules = new AlgoTopKClassRules(); 
		
		// Generate association rules 
		topK_classRules.runAlgorithm(top_k, minconf, db, itemToBeUsedAsConsequent);
		topK_classRules.printStats();
		try {
			topK_classRules.writeResultTofile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Convert outputs to include the original names for the items
		ResultConverter output_converter = new ResultConverter();
		try {
			output_converter.convert(input_dataset, output, final_output, null);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		//Specify input dataset
		String input_dataset = "C:/IFN645/CapstoneAssignment/dataset/campaign_converted.txt";
		//Specify output files
		String output_path = "C:/IFN645/CapstoneAssignment/output/";
		
		// Specify minimum confidence
		double minconf = 0.3;
		// Top-k 
		int top_k = 30;
		// the item to be used as consequent for generating rules
		int[] itemToBeUsedAsConsequent = new int[] {49}; // item 49 for yes
		//int[] itemToBeUsedAsConsequent = new int[] {11}; // item 11 for no
		
		Task1_Question5 generateRules = new Task1_Question5();
		generateRules.topK_classRules(input_dataset, output_path, minconf, top_k, itemToBeUsedAsConsequent);
	}
}
