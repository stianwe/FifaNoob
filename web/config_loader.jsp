<jsp:useBean id="configLoader" class="matchStatistics.ConfigLoader" scope="page" />
<%
    boolean loadedConfig = configLoader.loadConfig("/var/lib/tomcat7/fifa_config2.txt");
%>
