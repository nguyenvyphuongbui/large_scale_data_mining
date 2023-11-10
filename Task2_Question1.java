import java.util.Random;
import weka.attributeSelection.GainRatioAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import java.text.DecimalFormat;

public class Task2_Question1 {
	public void doFilteredClassification(Instances data, AbstractClassifier classifier, ASEvaluation evaluator, ASSearch searcher, String algorithm) {
		try {
		AttributeSelectedClassifier asc = new AttributeSelectedClassifier();
		DecimalFormat df = new DecimalFormat("#.####"); //enables limiting the decimal places to four
		
		asc.setClassifier(classifier); //instantiate the underlying classifier in AttributeSelectedClassifier
		asc.setEvaluator(evaluator); //instantiate the GainRatioAttributeEval evaluator
		asc.setSearch(searcher); //instantiate the Ranker search method
		
		int numAttributes = (((Ranker) asc.getSearch()).getNumToSelect()); //fetch number of attributes for an algorithm
		asc.buildClassifier(data);
		
		Evaluation eval = new Evaluation(data);
		eval.crossValidateModel(asc, data, 10, new Random(1)); //default 10-fold cross validation with random seed set as 1
		
		System.out.println("AttributeSelectedClassifier using " + algorithm + " with  " + numAttributes + " attributes.");
		System.out.println("Correctly Classified Instances - " + (int) eval.correct());
		System.out.println("Classification Accuracy - " + df.format((eval.correct()/eval.numInstances())*100) + "%");
		} 
		catch (Exception e) { 
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		DataSource source;
		Task2_Question1 filters = new Task2_Question1();
		
		try {
		source = new DataSource("C:\\Users\\prans\\OneDrive - Queensland University of Technology\\Large Scale Data Mining\\Assignment 2\\COVID19.arff");
		Instances data = source.getDataSet();
		data.setClass(data.attribute("infection_risk"));
		
		//below are the four algorithms chosen from Weka Q1.1
		J48 j48 = new J48();
		PART part = new PART();
		OneR oner = new OneR();
		NaiveBayes nb = new NaiveBayes();
		
		//Optimal number of attributes determined are 10 and 3, setting them below respectively.
		Ranker r1 = new Ranker();
		r1.setNumToSelect(10);
		Ranker r2 = new Ranker();
		r2.setNumToSelect(3);
		
		filters.doFilteredClassification(data, j48, new GainRatioAttributeEval(), r1, "J48");
		filters.doFilteredClassification(data, part, new GainRatioAttributeEval(), r1, "PART");
		filters.doFilteredClassification(data, oner, new GainRatioAttributeEval(), r2, "OneR");
		filters.doFilteredClassification(data, nb, new GainRatioAttributeEval(), r2, "NaiveBayes");
		} 
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}