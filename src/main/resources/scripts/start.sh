#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Missing environment argument. Usage: bin/start.sh prod|uat|test|dev"
    exit -1
fi

export APP_GROUP="app"
export APP_NAME="car-rent-service"
export PROJECT_DIR="/home/$USER/${APP_GROUP}"
export BASE_DIR="${PROJECT_DIR}/${APP_NAME}"

export EXTERNAL_CONFIG_FILE="${BASE_DIR}/config/application-$1.yml"
export PID_FILE="${BASE_DIR}/crs.pid"

export JVM_OPTIONS="-Xms512M -Xmx512M -server -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -verbose:gc
    -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:log/gc_%t_%p.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log/pid<pid>.hprof -XX:CompileThreshold=100"
Could not resolve placeholder 'jwt.header' in value "${jwt.header}"
echo "Start car rent service..."

nohup java ${JVM_OPTIONS} -Dspring.config.location=${EXTERNAL_CONFIG_FILE} -Dspring.profiles.active=$1 -jar lib/car-rent-service-*.jar --logging.config=config/logback-spring.xml 2>>log/nohup.log 1>&2 &

echo $! > ${PID_FILE}