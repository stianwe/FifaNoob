<jsp:useBean id="scoreBoard2" class="matchStatistics.ScoreBoard" scope="page" />
<jsp:useBean id="match2" class="matchStatistics.Match" scope="page" />
<jsp:setProperty name="match2" property="*" />
<%@ page import="matchStatistics.Player" %>
<%@ page import="matchStatistics.Match" %>
<%@ page import="matchStatistics.MatchStatistics" %>
<jsp:include page="config_loader.jsp" />

<table border="1">
    <%
    scoreBoard2.loadPlayers();
    scoreBoard2.loadMatches();
    for (int i = 0; i < scoreBoard2.getNumberOfMatches(); i++) {
        Match m = scoreBoard2.getMatch(i);
        String playerName = request.getParameter("player");
        if (!m.getHomePlayer().getName().equalsIgnoreCase(playerName) && !m.getAwayPlayer().getName().equalsIgnoreCase(playerName)) {
            continue;
        }
        %>
        <tr>
            <td><%= m.getHomePlayer().getName() %></td>
            <td><%= m.getAwayPlayer().getName() %></td>
            <td><%= m.getHomeGoals() %></td>
            <td><%= m.getAwayGoals() %></td>
        </tr>
        <%
    }
    %>
</table>




