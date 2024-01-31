#!/bin/bash

# Переміститись у каталог eureka-server
# shellcheck disable=SC2164
cd ./eureka-server/
./gradlew clean
./gradlew bootJar
docker build -t eureka:v1 .
echo "Додаток eureka-server готовий у Docker-образі з тегом eureka:v1"

# shellcheck disable=SC2164
cd ../api-gateway/
./gradlew clean
./gradlew bootJar
docker build -t api-gateway:v1 .
echo "Додаток api-gateway готовий у Docker-образі з тегом api-gateway:v1"

# shellcheck disable=SC2164
cd ../authorization-server-api/
./gradlew clean
./gradlew bootJar
docker build -t spring-boot-authorize:latest .
echo "Додаток authorization-server-api готовий у Docker-образі з тегом authorization-server-api:latest"

# shellcheck disable=SC2164
cd ../VideoStreamingMicroservice/
./gradlew clean
./gradlew bootJar
docker build -t video-streaming:v1 .
echo "Додаток video-streaming готовий у Docker-образі з тегом video-streaming:v1"# shellcheck disable=SC2164

cd ../elastic-search-video/
./gradlew clean
./gradlew bootJar
docker build -t elasticsearch:v1 .
echo "Додаток elasticsearch готовий у Docker-образі з тегом elasticsearch:v1"

cd ../FileMetadataStorage/
./gradlew clean
./gradlew bootJar
docker build -t file-metadata:v1 .
echo "Додаток file-metadata готовий у Docker-образі з тегом file-metadata:v1"

cd ../
docker-compose up -d
