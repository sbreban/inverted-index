#!/bin/bash


# create input directory on HDFS
hadoop fs -rm -r input
hadoop fs -rm stopwords.txt

# put input files to HDFS
hdfs dfs -put input
hdfs dfs -put stopwords.txt

# run wordcount
hadoop jar invertedIndex-1.0.jar stopwords.txt input output

# print the input files
echo -e "\ninput file1.txt:"
hdfs dfs -cat input/file1.txt

echo -e "\ninput file2.txt:"
hdfs dfs -cat input/file2.txt

# print the output of wordcount
echo -e "\nwordcount output:"
hdfs dfs -cat output/part-r-00000
