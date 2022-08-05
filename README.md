# JaCoCo Report Generator

**[JaCoCo](https://www.eclemma.org/jacoco/trunk/doc/agent.html)** uses class file instrumentation to record execution coverage data. Class files are instrumented on-the-fly using a so called Java agent. 

The **JaCoCo agent** collects execution information and dumps it on request or when the JVM exits. 

There are three different modes for the execution of data output. **The JaCoCo Report Generator extracts the report in real-time** through the TCP Socket Server, making it possible to connect to the JVM and retrieve execution data over the socket connection **without needing to stop the JVM**.


## Run as a Maven Project

If you haven't maven installed in your computer then check Apache Maven installation documentation **[here](https://maven.apache.org/install.html)**.

In your terminal execute the following commands:

* `cd /path/to/Jacoco_report_generation/`

* `mvn clean install`

* **Run** with the command example described [here](#argument-syntax-example).

## Command-Line:

You can run the program as a **Maven project** (mvn clean install), with an **IDE** or even export to a **runnable Jar**.

### **Important note:** 
In the project where the code coverage will be retrieved, the JaCoCo agent output must be in the **tcpserver** mode in the JVM options. We give an example to facilitate: `-javaagent:path\to\Jacoco\jacocoagent.jar=includes=*,output=tcpserver,port=8494,address=127.0.0.1`

The JaCoCo documentation can be found [here](https://www.eclemma.org/jacoco/trunk/doc/agent.html).

The *path to the JaCoCo agent* refers to the folder containing the jar for the JaCoCo agent, which you can download **[here](https://www.eclemma.org/jacoco/)**. And then run the project that you want to retrieve the code coverage. If your project is already in a runnable jar, then a simple syntax example is the following:
`java -javaagent:path\to\Jacoco\jacocoagent.jar=includes=*,output=tcpserver,port=8494,address=127.0.0.1 -jar youProjectRunnableJar.jar`

### Argument syntax:

```java
[options...] --cc-classes <Path to classes folder> --cc-source <Path to Java files folder>
```

| Options | Description | Mandatory |
| -----  | --------  | ---  | 
| `cc-classes` | **String.** Path to the location relative to the working directory where all **class files** seen by the agent. *Example:* `"~/pathToYourProject/target/classes/"`  | ✓ |
| `cc-source` | **String.** Path to the location relative to the working directory where all **java files** seen by the agent. *Example:* `"~/pathToYourProject/src/main/java/"`  | ✓ |
| `cc-socket-IP` | **String.** IP address or hostname of the server that it is hosting the REST service, in order to extract the code coverage report from the JaCoCo agent.  *Default value:* `"127.0.0.1"`  | _ |
| `cc-socket-port` | **String.** PORT of the server that it is hosting the REST service, in order to extract the code coverage report from the JaCoCo agent.   *Default value:* `8494` 
| `execution-run` | **Integer.** The number that will name the sub-folder, this way it is possible to save multiple files without intercepting the name. *Default value*: `0` | _ |*Default value:* `"8494"`  | _ |
| `folder-name` | **Boolean.** The name of the output's sub-folder. This way, when exporting code coverage simultaneously for multiple projects, the sub-folder may have a different name so that it will not intercept any path's name. *Default value:* `Coverage`  | _ |
| `reset` | **Boolean.** Reset the code coverage data. If set to true, then after generating the report the socket is reseted, and, therefore coverage data is also reseted. *Default value:* `true`  | _ |



### Argument syntax example:



Linux/macos:

```
java -jar ~/target/JaCoCo-report-generator-1.0.jar \
--cc-classes "/pathToProject/to/cover/target/classes" \
--cc-source "/pathToProject/to/cover/src/main/java" \
--cc-socket-IP "192.168.1.10" \
--folder-name "SubFolder_Coverage" \
--execution-run 1 \
--cc-socket-port "8499" \
--out "/path/to/output/folder/" 


```

Windows:

```
java -jar .\target\JaCoCo-report-generator-1.0.jar ^
--cc-classes "\pathToProject\to\cover\target\classes" ^
--cc-source "\pathToProject\to\cover\cover\src\main\java" ^
--cc-socket-IP "192.168.1.10" ^
--folder-name "SubFolder_Coverage" ^
--execution-run 1 ^
--cc-socket-port "8499" ^
--out "\path\to\output\folder\"


```
