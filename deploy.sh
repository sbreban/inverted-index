#!/bin/bash

sudo docker cp target/invertedIndex-1.0.jar hadoop-master:/root/
sudo docker cp input hadoop-master:/root/
sudo docker cp stopwords.txt hadoop-master:/root/
sudo docker cp run-index.sh hadoop-master:/root/
