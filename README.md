# Wowza Plugin Builder
The **Wowza Plugin Builder** container is used to build modles for [Wowza Streaming Engine™ media server software](https://www.wowza.com/products/streaming-engine).


## Usage
### Initialization
First the container needs to be built.  Run the following script to create the `wse-builder ` docker image:
```
./local_build.sh
```
This will create an docker image `wse-builder:local` that can be used to build custom WSE modules.

### Compile Module to .jar
To compile a WSE plugin to be used with WSE, running the follow code with the directory that contains the WSE plug java code.  This will create a .jar file that can be used with WSE.
Provided in this repo is MyFirstPlugin as an example you can test with.
```
./local_run.sh //code/wse-plugin-my-first-plugin
```

This creates the WSE plugin .jar: `code/wse-plugin-my-first-plugin/build/libs/wse-plugin-my-first-plugin-1.0.0.jar` which can be run with WSE


This plugin contains a ServerListener `com.wowza.wms.plugin.myFirstPlugin.MyFirstServerListener` that starts when WSE starts, and a Application Module `com.wowza.wms.plugin.myFirstPlugin.MyFirstModule` that starts when a stream is connected.
Both will simple log a message to the console when started.

### Test
To test the newly created module, you can run:
```
docker compose up
```
Which will start the WSE trial docker image, map the lib.addon directory to the newly created jar file.
See [Get Started With Wowza Streaming Engine](https://www.wowza.com/wse-get-started) for more details on running WSE with docker and docker compose

## Other Resources
[Wowza Streaming Engine Server-Side API Reference](https://www.wowza.com/resources/serverapi/)

[How to extend Wowza Streaming Engine using the Wowza IDE](https://www.wowza.com/docs/how-to-extend-wowza-streaming-engine-using-the-wowza-ide)

Wowza Media Systems™ provides developers with a platform to create streaming applications and solutions. See [Wowza Developer Tools](https://www.wowza.com/developer) to learn more about our APIs and SDK.

## Contact
[Wowza Media Systems, LLC](https://www.wowza.com/contact)

## License
This code is distributed under the [Wowza Public License](/LICENSE.txt).
