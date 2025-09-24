#!/bin/bash

cd /code

gradle_cmd="${GRADLECMD:-Build}"

run_and_prompt() {
  echo ""
  echo "==> ${gradle_cmd}ing"
  eval "./gradlew ${gradle_cmd,,}"
  echo ""
  echo ""
  if [[ "$INTERACTIVE" =~ ^[nNFf0].*$ ]]; then
    echo ""
    echo "Exiting. Thanks for ${gradle_cmd}ing!"
    exit 0
  fi

  read -p "Press any key to re-${gradle_cmd}, 'B' to build, 'C' to clean 'Q' to quit: " -n 1 -r
  if [[ $REPLY =~ ^[Qq]$ ]]; then
  	echo ""
    echo "Exiting. Thanks for ${gradle_cmd}ing!"
    exit 0
  elif [[ $REPLY =~ ^[bB]$ ]]; then
  	gradle_cmd="Build"
  elif [[ $REPLY =~ ^[cC]$ ]]; then
  	gradle_cmd="Clean"
  fi
  run_and_prompt
}

run_and_prompt