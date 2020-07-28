#!/bin/bash

export APP_GROUP="app"
export APP_NAME="car-rent-service"

export BASE_DIR="/home/$USER/${APP_GROUP}/${APP_NAME}"
export PID_FILE="${BASE_DIR}/crs.pid"

PID=$(cat $PID_FILE)

echo "Killing pid = $PID"

kill -9 $PID