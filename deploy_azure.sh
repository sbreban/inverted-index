#!/usr/bin/env bash

scp target/invertedIndex-1.0.jar sshbreban@sbreban-ssh.azurehdinsight.net
scp -r input sshbreban@sbreban-ssh.azurehdinsight.net:.
scp stopwords.txt sshbreban@sbreban-ssh.azurehdinsight.net:.
scp run-index.sh sshbreban@sbreban-ssh.azurehdinsight.net:.
