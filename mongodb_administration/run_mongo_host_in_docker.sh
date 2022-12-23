#!/bin/bash

docker stop my_mongo_host &>/dev/null && docker rm my_mongo_host &>/dev/null || true
docker build -t mongo_host .
docker run --name my_mongo_host -t -d mongo_host
docker exec -it my_mongo_host /bin/bash
