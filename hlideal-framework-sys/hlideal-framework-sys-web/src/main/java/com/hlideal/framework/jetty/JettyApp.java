package com.hlideal.framework.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

public class JettyApp {

  //  private static final String PORT = "port";
  //  private static final String WEB_DIR = "web";
  //  private static final String LOG_DIR = "log";
  //  private static final String TEMP_DIR = "temp";
  //  private static final String CONTEXT_PATH = "context";
  //  private static final String HOST = "host";
  //  private static final Map<String, String> param = new HashMap<>();
  //  private static Logger logger = LoggerFactory.getLogger(JettyApp.class);


    public static void main(String... anArgs) throws Exception {
    	
    	String webappDirLocation = "src/main/webapp/";
    	//String webappDirLocation = "D:\\zx\\";
        
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8888";
        }

        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext root = new WebAppContext();

        root.setContextPath("");
        //root.setDescriptor("D:\\zx\\WEB-INF\\web.xml");
        root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
 
        //Parent loader priority is a class loader setting that Jetty accepts.
        //By default Jetty will behave like most web containers in that it will
        //allow your application to replace non-server libraries that are part of the
        //container. Setting parent loader priority to true changes this behavior.
        //Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.setParentLoaderPriority(true);
        
        
        server.setHandler(root);
        
        server.start();
        server.join();   
/*
        if (anArgs.length == 0) {
            param.put(PORT, "9999");
            param.put(WEB_DIR, "src/main/webapp");
            param.put(LOG_DIR, "logs");
            param.put(TEMP_DIR, "temp");
            param.put(CONTEXT_PATH, "manage");
            param.put(HOST, "localhost");
        }


        for (String arg : anArgs) {
            System.out.println(arg);
            if (!StringUtils.isEmpty(arg) && arg.contains("=")) {
                String[] t = arg.trim().split("=");
                param.put(t[0], t[1]);
            }
        }

        initParam();

        unzipSelf();

        new JettyApp().start();
        */
    }


   
}