#!/bin/bash

if [ -z "$1" ]; then
	echo "Error: need to specify where the code is."
	exit 0
fi

gradle_cmd="${2:-Build}"

echo "${gradle_cmd}ing: `realpath $1`"
docker run -it -v `realpath $1`:/code -e GRADLECMD=$gradle_cmd wse-builder:local