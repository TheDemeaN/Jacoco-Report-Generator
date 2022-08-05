package codeCoverage;

import Utils.Config;
import org.jacoco.core.data.ExecutionDataWriter;
import org.jacoco.core.runtime.RemoteControlReader;
import org.jacoco.core.runtime.RemoteControlWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ExecutionDataClient {

    private static final String DESTFILE = Config.Instance().currentDir + "jacoco-client.exec";
    private static final String ADDRESS = Config.Instance().cc_Socket_IP;
    private static final int PORT = Config.Instance().cc_Socket_PORT;


    public void requestRemoteDump() throws IOException {
        final FileOutputStream localFile = new FileOutputStream(DESTFILE);
        final ExecutionDataWriter localWriter = new ExecutionDataWriter(localFile);
        // Open a socket to the coverage agent:
        final Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
        final RemoteControlWriter writer = new RemoteControlWriter(
                socket.getOutputStream());
        final RemoteControlReader reader = new RemoteControlReader(
                socket.getInputStream());
        reader.setSessionInfoVisitor(localWriter);
        reader.setExecutionDataVisitor(localWriter);

        // Send a dump command and read the response:
        writer.visitDumpCommand(true, Config.Instance().reset);
        reader.read();

        socket.close();
        localFile.close();
        //System.out.println("ok");
    }


    public ExecutionDataClient() {}
}
