#!/bin/bash

hadoop fs -mkdir /user
hadoop fs -mkdir /user/root
hadoop fs -rm -r input
hadoop fs -rm stopwords.txt

hdfs dfs -put input
hdfs dfs -put stopwords.txt

hadoop jar invertedIndex-1.0.jar stopwords.txt input output

hdfs dfs -cat output/part-r-00000
