<jsp:useBean id="scoreBoard" class="matchStatistics.ScoreBoard" scope="page" />
    Config has been loaded: <%= scoreBoard.configIsLoaded() %> :)<br/>
<%
    if (!scoreBoard.loadPlayers()) {
        out.println("Failed to load players: " + scoreBoard.getException() + "<br/>");
    }

    if (!scoreBoard.loadMatches()) {
        out.println("Failed to load matches: " + scoreBoard.getException() + "<br/>");
    }
%>

Number of players loaded: <%= scoreBoard.getNumberOfPlayers() %><br/>
