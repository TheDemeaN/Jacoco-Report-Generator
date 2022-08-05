package main;
import Utils.Config;
import codeCoverage.CcController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        checkArgs(new ArrayList(Arrays.asList(args)));
        CcController.codeCoverageGeneration(Config.Instance().folderName,  Config.Instance().iteration);
    }


    private static void checkArgs(ArrayList<String> args){
        try
        {
            Map<String, String> argsMap = new LinkedHashMap<>();
            String lastArg = null;
            for(String arg : args)
            {
                if(arg.startsWith("--"))
                {
                    lastArg = arg;
                    argsMap.put(lastArg, "");
                }
                else
                {
                    if(lastArg != null)
                        argsMap.put(lastArg, arg);
                }
            }
            new Config(argsMap);
        }
        catch(Exception e)
        {
            System.out.println("Invalid arguments!");
            System.out.println("Argument syntax: [options...] --cc-classes <Path to classes folder> --cc-source <Path to Java files folder>");
            System.out.println("Available options:");
            System.out.println("\t--out <Path to the output folder where the report files will be save>, default folder is this project's folder");
            System.out.println("\t--cc-socket-IP <Jacoco tcpserver IP>, default IP is 127.0.0.1");
            System.out.println("\t--cc-socket-port <Jacoco tcpserver port>, default port is 8494");
            System.out.println("\t--execution-run <Integer that will name the sub-folder, this way it is possible to save multiple files without intercepting the name>, default is 0");
            System.out.println("\t--reset <true or false. Reset the code coverage data. If set to true, then after generating the report the coverage data is reseted.> default is true");
            System.out.println("\t--folder-name <The name of the output's sub-folder, this way for multiple projects exporting the code coverage the sub-folder may have a different name without intercepting the path's name>");
            System.out.println("\n\tExample: java -jar .\\target\\JaCoCo-report-generator-1.0.jar --cc-classes path/to/your/project/target/classes --cc-source path/to/your/project/main/java");

            System.out.println("ERROR: " + e.getMessage());
            System.exit(0);
        }

        if(Config.Instance().out != null)
        {
//            try
//            {
//                PrintStream output = new PrintStream(new File(Config.Instance().out));
//                System.setOut(output);
//                System.setErr(output);
//            }
//            catch(Exception e)
//            {
//                System.err.println("Could not redirect output to file " + Config.Instance().out);
//            }
        }
    }
}
