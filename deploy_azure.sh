#!/bin/bash

scp target/invertedIndex-1.0.jar $1:.
scp -r input $1:.
scp stopwords.txt $1:.
scp run-index.sh $1:.
