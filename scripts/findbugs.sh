#!/bin/bash
SRC_PATH="./target/findbugs/"
DST_PATH="./gh-pages/findbugs/"
LOG_PREFIX="findbugs:"

# get number of high warnings
REPORT_FILE="${SRC_PATH}findbugsXml.html"
LINE_NUM=`awk '/High Priority Warnings/{ print NR; exit }' $REPORT_FILE`
LINE_NUM=$((LINE_NUM+1))
LINE_SUBSTRING=`sed "${LINE_NUM}q;d" $REPORT_FILE | cut -d'>' -f2`
HIGH_WARN_COUNT=0
if [[ $LINE_SUBSTRING = *[!\ ]* ]]
then
  HIGH_WARN_COUNT=`echo $LINE_SUBSTRING | cut -d'<' -f1`
fi
echo "findbugs: found $HIGH_WARN_COUNT high warnings."

# badge creation
BADGE_FILENAME="static-analysis-badge.svg"
BADGE_URL_PREFIX="https://img.shields.io/badge/static%20analysis"
STATUS="passing"
COLOR="brightgreen"
EXTENSION=".svg"
if [ "$HIGH_WARN_COUNT" -gt 0 ]
then
  STATUS="warnings"
  COLOR="yellow"
fi

BADGE_URL="${BADGE_URL_PREFIX}-$STATUS-${COLOR}${EXTENSION}"
echo "$LOG_PREFIX badge url is $BADGE_URL"

# create results
echo "$LOG_PREFIX creating results at $DST_PATH"
mkdir -p $DST_PATH
rsync -a $SRC_PATH $DST_PATH
curl $BADGE_URL > ${DST_PATH}${BADGE_FILENAME}
