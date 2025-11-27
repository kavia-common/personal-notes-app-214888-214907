#!/bin/bash
cd /home/kavia/workspace/code-generation/personal-notes-app-214888-214907/android_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

