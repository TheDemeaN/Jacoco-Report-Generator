package codeCoverage;


import Utils.Config;

import java.io.IOException;

public class CcController {

    private static ExecutionDataClient executionDataClient = new ExecutionDataClient();

    public static void resetCodeCoverageTracer() {
        codeCoverageGeneration("cleanTPCsocket", 0) ;
    }

    public static void codeCoverageGeneration(String name, int runNumber){
        try{
            executionDataClient.requestRemoteDump();
            String separator = Config.Instance().fileSep;
            String reportFolderName =
                                        "coverageReport"                    + separator
                                        + name                              + separator
                                        + "run_"+String.valueOf(runNumber)  + separator;
            new ReportGenerator(reportFolderName);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
