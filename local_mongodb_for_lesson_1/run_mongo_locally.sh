#!/bin/bash

docker run -d --name mongo -v $(pwd)/databases:/databases mongo:5.0.13
docker exec -it mongo mongoimport --drop --db sample_mflix --collection comments --file /databases/sample_mflix/comments.json
docker exec -it mongo mongoimport --drop --db sample_mflix --collection movies --file /databases/sample_mflix/movies.json
docker exec -it mongo mongosh

