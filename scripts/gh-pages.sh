#!/bin/bash

echo 'Pushing results to GHE...'
cd gh-pages
	git init
	git config user.name "Travis-CI"
	git config user.email $FUNC_ID_USERNAME
	git add .
    git status
	git commit -m "code coverage and static analysis data"
	git push --force "git@github.ibm.com:GTD/actionable-flows.git" master:gh-pages
