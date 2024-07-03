#!/bin/bash
docker stop newsapi

mvn clean package

docker build -t newsapi .

docker run -p 8080:8080 newsapi


