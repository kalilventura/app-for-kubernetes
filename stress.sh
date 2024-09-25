#!/bin/bash

for in in {1..10000}; do
    start_time=$(date +%s.%N)
    curl localhost:8080/v1/pizzas
    end_time=$(date +%s.%N)
    response_time=$(echo "$end_time - $start_time" | bc)
    echo "Request $in took $response_time seconds"
    sleep $1
done