#!/bin/bash
SRC_PATH="./target/site/jacoco-ut/"
DST_PATH="./gh-pages/coverage/"
LOG_PREFIX="coverage:"

# calculate total coverage percentage
REPORT_FILE="${SRC_PATH}/jacoco.csv"
NUM_MISSED=`awk -F "\"*,\"*" '{sum+=$4}END{print sum}' $REPORT_FILE`
NUM_COVERED=`awk -F "\"*,\"*" '{sum+=$5}END{print sum}' $REPORT_FILE`
let TOTAL=NUM_COVERED+NUM_MISSED
let PCT_COVERED=100*NUM_COVERED/TOTAL
echo "$LOG_PREFIX overall code coverage is ${PCT_COVERED}%"

# badge creation
BADGE_FILENAME="code-coverage-badge.svg"
BADGE_URL_PREFIX="https://img.shields.io/badge/coverage"
PCT_SYMBOL="%25"
COLOR="red"
EXTENSION=".svg"
if [ "$PCT_COVERED" -gt 90 ]
then
  COLOR="brightgreen"
elif [ "$PCT_COVERED" -gt 65 ]
then
  COLOR="yellow"
fi

BADGE_URL="${BADGE_URL_PREFIX}-${PCT_COVERED}${PCT_SYMBOL}-${COLOR}${EXTENSION}"
echo "$LOG_PREFIX badge url is $BADGE_URL"

# create results
echo "$LOG_PREFIX creating results at $DST_PATH"
mkdir -p $DST_PATH
rsync -a $SRC_PATH $DST_PATH
curl $BADGE_URL > ${DST_PATH}${BADGE_FILENAME}
