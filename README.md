# Wowza Streaming Engine Plugin Builder

A Gradle and Docker-based development environment for building and testing custom Java modules that extend Wowza Streaming Engine™.

## Overview

The **Wowza Streaming Engine Plugin Builder** provides a modern, containerized development environment for building and testing custom Java modules that extend the functionality of the [Wowza Streaming Engine™ media server software](https://www.wowza.com/products/streaming-engine).

This project includes a streamlined Gradle-based build system and a Docker Compose setup that runs Wowza Streaming Engine in a local container. The approach allows developers to build, package, and deploy [custom Wowza Streaming Engine extensions](https://www.wowza.com/docs/use-wowza-streaming-engine-java-modules#use-custom-modules) quickly and consistently across platforms.

## Features

* **Gradle build system** — Compile, package, and manage dependencies for your Wowza Streaming Engine Java modules with ease.
* **Docker Compose deployment** — Spin up a fully configured Wowza Streaming Engine container for local testing and iteration.
* **Modular development** — Extend Wowza Streaming Engine functionality using custom Java classes and interfaces.
* **Cross-platform setup** — Works seamlessly on Windows, Linux, and macOS.

## Prerequisites

Before you begin, make sure you have:

* A Wowza Streaming Engine license key.
* Install and run [Docker Desktop](https://www.docker.com/products/docker-desktop/), which includes the Docker Engine and the Docker Compose plugin.

## Installation

Clone the repository and change to that directory:

```bash
git clone git@github.com:WowzaMediaSystems/wse-plugin-builder.git
cd wse-plugin-builder
```

Update the **WSE_LICENSE_KEY** variable in the docker-compose.yaml file with your Wowza Streaming Engine license key:

```bash
export WSE_LICENSE_KEY=[your-license-key]
```

## Usage

### Build the Docker image

Make sure Docker Desktop and Docker Engine are running. Then, initialize the project and build the Docker container. Run the following from the root of your project:

```bash
./build_builder.sh
```

This builds a new Docker image named `wse-builder:local` using the Dockerfile in this repository. The image provides a build environment for compiling custom Wowza Streaming Engine modules.

### Compile the module to a .jar

Next, compile the module so it can be used as a plugin with Wowza Streaming Engine. An example module, **MyFirstPlugin**, is included in this repository for testing and demonstration purposes. Run the following from the root of your project:

```bash
./build.sh ./code/wse-plugin-my-first-plugin
```

This process generates the following plugin .jar file, which can be deployed and run with Wowza Streaming Engine:

`code/wse-plugin-my-first-plugin/build/libs/wse-plugin-my-first-plugin-1.0.0.jar`

The example plugin includes:

* [Server Listener](https://github.com/WowzaMediaSystems/wse-plugin-builder/blob/main/code/wse-plugin-my-first-plugin/src/main/java/com/wowza/wms/plugin/myFirstPlugin/MyFirstServerListener.java): `com.wowza.wms.plugin.myFirstPlugin.MyFirstServerListener` — Starts automatically when the Wowza Streaming Engine server launches.
* [Application Module](https://github.com/WowzaMediaSystems/wse-plugin-builder/blob/main/code/wse-plugin-my-first-plugin/src/main/java/com/wowza/wms/plugin/myFirstPlugin/MyFirstModule.java): `com.wowza.wms.plugin.myFirstPlugin.MyFirstModule` — Initializes when an application starts and the first stream connects to it.

Both components simply log a startup message to the console:

```text
**********************************************
* Welcome to my first Server Listener v1.0.0 *
**********************************************
```

```text
********************************************************
* Welcome to my first Application Module Plugin v1.0.0 *
********************************************************
```

You can modify these sample modules by adding any custom logic inside the example methods we provide. For more, see our [Custom Java module examples](https://www.wowza.com/docs/basic-java-code-examples-for-wowza-media-server).

### Test your module

To test the newly created module, run:

```bash
docker compose up
```

This starts the Wowza Streaming Engine trial Docker image and maps the `lib.addon` directory to the newly created .jar file. With a successful setup, you can see the following in the combined Docker service logs in your terminal:

```text
wse-1      | INFO server comment - Server.startShutdownHook: Start server shutdown hook
wse-1      | INFO server comment - **********************************************
wse-1      | INFO server comment - * Welcome to my first Server Listener v1.0.0 *
wse-1      | INFO server comment - **********************************************
wse-1      | INFO server comment - StatsManager:startManager() Enabled=true
wse-1      | INFO server comment - Wowza Streaming Engine is started!
```

To learn more about our Docker Compose workflows, see:

* [Get Started With Wowza Streaming Engine](https://www.wowza.com/wse-get-started)
* [Set up Wowza Streaming Engine using a Docker Compose deployment](https://www.wowza.com/docs/set-up-wowza-streaming-engine-using-a-docker-compose-deployment)

## More resources

Wowza Media Systems™ provides developers with a platform to create streaming applications and solutions. See [Wowza Developer Tools](https://www.wowza.com/developer) to learn more about our APIs and SDK.

You can also view our [official Developer API and SDK documentation](https://www.wowza.com/docs/wowza-developer-apis-and-sdks) and these resources:

* [Wowza Streaming Engine Server-Side Java API reference](https://www.wowza.com/resources/serverapi/)
* [Use Wowza Streaming Engine Java modules](https://www.wowza.com/docs/use-wowza-streaming-engine-java-modules)
* [Use custom Java modules](https://www.wowza.com/docs/use-wowza-streaming-engine-java-modules#use-custom-modules3)
* [Module and plugin examples hosted on GitHub](https://www.wowza.com/docs/wowza-streaming-engine-java-module-examples)
* [Custom Java module examples](https://www.wowza.com/docs/basic-java-code-examples-for-wowza-media-server)
* [How to extend Wowza Streaming Engine using the Wowza IDE](https://www.wowza.com/docs/how-to-extend-wowza-streaming-engine-using-the-wowza-ide)

## Contact

For questions or comments, contact [Wowza Media Systems, LLC](https://www.wowza.com/contact).

## License

This code is distributed under the [Wowza Public License](/LICENSE.txt).
