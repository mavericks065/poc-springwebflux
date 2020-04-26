#!/usr/bin/env bash

docker build --no-cache -f ./Dockerfile-postgres-test -t poc-webflux-test-postgres:latest .
