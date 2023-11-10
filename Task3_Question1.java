import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stemmers.LovinsStemmer;
import weka.core.stopwords.Rainbow;
import weka.core.tokenizers.AlphabeticTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.core.SelectedTag;

public class Task3_Question1 {
    public void doFilteredClassification(Instances data, Classifier classifier) {
        try {
            // Sets the class index of the dataset
            data.setClassIndex(1);
            // Create a StringToWordVector filter
            StringToWordVector swFilter = new StringToWordVector();
            // Specify range of attributes to act on. We want to work on the entire list of words
            swFilter.setAttributeIndices("first-last");
            // Configure the filter
            swFilter.setIDFTransform(true);
            swFilter.setTFTransform(true);
            swFilter.setNormalizeDocLength(
                    new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
            swFilter.setOutputWordCounts(true);
            swFilter.setStemmer(new LovinsStemmer());
            swFilter.setStopwordsHandler(new Rainbow());
            swFilter.setTokenizer(new AlphabeticTokenizer());
            swFilter.setWordsToKeep(100);
            // Create a FilteredClassifier object
            FilteredClassifier filter_classifier = new FilteredClassifier();
            // Set the filter to the filtered classifier
            filter_classifier.setFilter(swFilter);
            filter_classifier.setClassifier(classifier);
            long startTime = System.currentTimeMillis();
            filter_classifier.buildClassifier(data);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            // Evaluation
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(filter_classifier, data, 10, new Random(1));
            System.out.println("Done");
            System.out.println("Classifier: " + classifier.getClass().getSimpleName());
            System.out.println("Correctly Classified Instances: " + eval.correct());
            System.out.println("Accuracy: " + eval.pctCorrect() + "%");
            System.out.println("Time taken: " + elapsedTime + " milliseconds");
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println("===== Evaluating on filtered (training) dataset done =====");
        } catch (Exception e) {
            System.out.println("Error in ...");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("C:\\Users\\prans\\OneDrive - Queensland University of Technology\\Large Scale Data Mining\\Assignment 2\\News.arff");
        Instances data = source.getDataSet();
        Classifier[] classifiers = {
            new IBk(),
            new SMO(),
            new J48(),
            new HoeffdingTree()
        };
        Task3_Question1 tc = new Task3_Question1();
        for (Classifier classifier : classifiers) {
            tc.doFilteredClassification(data, classifier);
            System.out.println("=================================================");
        }
    }
}