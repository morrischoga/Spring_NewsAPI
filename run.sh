#!/bin/bash
docker stop newsapi


git pull

docker build -t newsapi .

docker run -p 8080:8080 --name newsapi newsapi


