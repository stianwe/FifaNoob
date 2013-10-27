<jsp:useBean id="scoreBoard" class="matchStatistics.ScoreBoard" scope="page" />
<jsp:useBean id="match" class="matchStatistics.Match" scope="page" />
<jsp:setProperty name="match" property="*" />
<%@ page import="matchStatistics.Player" %>
<%@ page import="matchStatistics.Match" %>
<%@ page import="matchStatistics.MatchStatistics" %>

<table border="1">
    <%
    for (int i = 0; i < scoreBoard.getNumberOfMatches(); i++) {
        Match m = scoreBoard.getMatch(i);
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




