package Utils;

import java.util.Map;

public final class Config
{
    private static  Config  instance = null;
    public static   Config Instance() {return instance;}

    public final    String  fileSep              = System.getProperty("file.separator");
    public final    String  currentDir           = System.getProperty("user.dir") + fileSep;
    public          String  pathtowrite          = currentDir;
    public final    String  out;
    public final    String  ccClassesDirectory;
    public final    String  ccSourceDirectory;
    public final    String  cc_Socket_IP;
    public          String  folderName;
    public          int     iteration;
    public final    boolean reset;
    public final    int     cc_Socket_PORT;
    
    public Config(Map<String, String> args) throws Exception
    {
        out = args.get("--out");
        if(out != null)
            pathtowrite = out;

        folderName          = args.getOrDefault("--folder-name","Coverage");
        iteration           = Integer.parseInt(args.getOrDefault("--execution-run","0"));
        reset               = Boolean.parseBoolean(args.getOrDefault("--reset","true"));
        ccClassesDirectory  = args.get("--cc-classes");
        ccSourceDirectory   = args.get("--cc-source");
        cc_Socket_IP        = args.getOrDefault("--cc-socket-IP", "127.0.0.1");
        cc_Socket_PORT      = Integer.parseInt(args.getOrDefault("--cc-socket-port", "8494"));

        if(ccSourceDirectory==null || ccClassesDirectory==null)
            throw new Exception("Arguments --cc-classes and --cc-source are mandatory.");
        instance = this;
    }
}
