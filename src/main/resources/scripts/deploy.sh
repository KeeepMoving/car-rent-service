#!/bin/bash

if [ $# -eq 2 ]
  then
    mkdir ./$1 && tar -zxvf $2 -C $1 --strip-components 1
else
    echo "Missing environment argument. Usage: bin/deploy.sh car-rent-service *.tar.gz"
    exit -1
fi
