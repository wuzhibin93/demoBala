#!/usr/bin/env bash

python /usr/local/foresrally.py
esrally race --pipeline=benchmark-only --target-hosts=127.0.0.1:9200 --track-path=/usr/local/esrally/benchmark/track/enadmin --user-tag="data:1000000,car:1gheap"
