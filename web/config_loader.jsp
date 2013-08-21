<jsp:useBean id="configLoader" class="matchStatistics.ConfigLoader" scope="page" />
<%
    if (configLoader.configHasBeenLoaded()) {
        out.println("Config has already been loaded... Not doing it again.<br/>");
    } else {
        boolean loadedConfig = configLoader.loadConfig("/var/lib/tomcat7/fifa_config.txt");
        out.println("Loaded configs: " + loadedConfig + "<br/>");
        if (!loadedConfig) {
            out.println("Exception: " + configLoader.getException() + "<br/>");
        }
    }
%>
