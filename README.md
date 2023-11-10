# large_scale_data_mining
# Task 1: Association mining in Java 
# A bank has conducted a marketing campaign via phone calls to promote their new products including a term-deposit product. After the campaign, the bank wants to analyse the data collected in the campaign in order to get a better understanding to their customers. 
# 1. Dataset
# Download the datasets campaign.arff, campaign_no.arff, and campaign_yes.arff from the Canvas. campaign.arff is the entire dataset 
# collected in the campaign which consists of 44,209 records. Each record in the dataset is about one customer described by 11 attributes. After this marketing campaign, a customer could subscribe the term-deposit product or not, indicated by attribute ‘subscribed’. This attribute divides customers into two classes, i.e., yes-class and noclass. campaign_no.arff, and campaign_yes.arff are two subsets of the entire dataset containing customers in each of the two classes, respectively. 
# To help you understand the data, the following table provides you with the description to each of the attributes. 
# Attribute Description
# age Customer age
# job Customer job type
# marital Customer marital status 
# education Customer education status
# default_credit Whether customer has credit in default or not
# balance Customer bank account balance
# housing Whether customer has housing/home loan or not
# loan Whether customer has personal loan or not
# call_duration Phone contact duration in seconds, e.g., 500-1k indicates 
# that the phone talk with the customer lasts 500 to 1000 seconds.
# past_marketing Outcome of last marketing to this customer, e.g., failure, indicates that the last marketing was unsuccessful to this customer. 
# subscribed Whether customer subscribes the term-deposit product or not.
# In the context of this task, an item is an attribute with a specific value, e.g., age=40s, and a pattern is a set of items, e.g., {default_credit=no, loan=no, age=21-30s} is a size-3 pattern. The patterns generated from the bank dataset describe the characteristics or behaviour of the customers in that dataset. After the marketing campaign, the bank wants to derive some information from the dataset. Specifically, they want to know the popular patterns in each customer class and also want to know the associations between customer behaviour and each class, i.e., the association rules that represent the implications from customer attributes to one of the classes. As a data analyst, you are required to develop a Java program that can generate patterns and association rules from the datasets. 
# 2. Questions
# 1) Generate frequent patterns from the entire dataset (i.e., campaign.arff) using two frequent pattern mining algorithms and compare their performance in terms of time efficiency. You can use different minimum supports, e.g., 0.2, 0.3, and 0.4, to do the comparison. Show your comparison result. 
# 2) Choose a frequent pattern mining algorithm based on the comparison in 1), state the reason to choose this algorithm. Then use the chosen algorithm to generate the top 10 most frequent size-3 patterns from the yes-class dataset and no-class dataset, separately, then compare the generated patterns from the two datasets. Do the customers in the two classes share any common characteristics? Are there any different characteristics between the customers in the two classes? List the similar characteristics and differences if there are any. 
# Specify the minimum support that you have used for this question. 
# 3) Generate the top 5 most frequent maximum patterns from yes-class and no class datasets separately, identify any similarity or differences between the two classes in terms of the maximum patterns. List the similar characteristics and differences if there are any. 
# Specify the minimum support that you have used for this question. 
# 4) Use three algorithms to generate frequent closed patterns from the entire dataset and compare their time efficiency. You can use three different minimum supports, 0.2, 0.3, and 0.4, to do the comparison. Show your comparison result. 
# 5) Using the entire dataset, generate the top 30 most frequent association rules with subscribed=yes as the consequent and also the top 30 most frequent association rules with subscribed=no as the consequent. Specify the minimum support and confidence that you have used. Observe the rules and identify any redundant rules in each set of the rules. You can round the confidence value to two decimal places. If there exist redundant rules, list them and state why you think they are redundant. 
