<jsp:useBean id="scoreBoard" class="matchStatistics.ScoreBoard" scope="page" />
<jsp:useBean id="match" class="matchStatistics.Match" scope="page" />
<jsp:setProperty name="match" property="*" />
<%@ page import="matchStatistics.Player" %>
<%@ page import="matchStatistics.Match" %>
<%@ page import="matchStatistics.MatchStatistics" %>
<%@ page import="java.text.*" %>
<jsp:include page="config_loader.jsp" />
<%
    if (!scoreBoard.loadPlayers()) {
        out.println("Failed to load players: " + scoreBoard.getException() + "<br/>");
    }

    if (!scoreBoard.loadMatches()) {
        out.println("Failed to load matches: " + scoreBoard.getException() + "<br/>");
    }
%>

Number of players loaded: <%= scoreBoard.getNumberOfPlayers() %><br/>
Number of matches loaded: <%= scoreBoard.getNumberOfMatches() %><br/>

<%
if (match.getHomePlayer() != null && match.getAwayPlayer() != null) {
    out.println("Should add match between " + match.getHomePlayer().getName() + " and " + match.getAwayPlayer().getName() + " with the result: " + match.getHomeGoals() + " - " + match.getAwayGoals() + "<br/>");
    if (!scoreBoard.addMatch(match)) {
        out.println("Exception while adding match: " + scoreBoard.getException() + "<br/>");
        out.println("SQL: " + scoreBoard.sql + " <br/>");
    }

    match = new Match();
	response.sendRedirect("statistics.jsp");
} else if ((match.getHomePlayer() == null || match.getAwayPlayer() == null) && !(match.getHomePlayer() == null && match.getAwayPlayer() == null)) {
    out.println("Both players must be filled!");
}


%>
<br/>
<br/>
<h1>Add match results:</h1>
<form>
    <input type="text" name="homePlayerName">
    -
    <input type="text" name="awayPlayerName">
    <input type="text" name="homeGoals">
    -
    <input type="text" name="awayGoals">
    <input type="submit" value="Submit">
</form>
<br/>
<br/>

<table border="1">
    <tr>
        <th>
            
        </th>
        <th>
            Player
        </th>
        <th>
            Matches
        </th>
        <th>
            Wins
        </th>
        <th>
            Draws
        </th>
        <th>
            Losses
        </th>
        <th>
            Goals scored
        </th>
        <th>
            Goals conceded
        </th>
        <th>
            Points
        </th>
        <th>
            Points average
        </th>
        <th>
            Rating
        </th>
    </tr>
    <%
    for (MatchStatistics ms : scoreBoard.getMatchStatistics()) {
        %>
        <tr>
            <td>
                <img src = "<%= ms.getPlayer().getPicture() != null ? ms.getPlayer().getPicture() : "http://www.faithlineprotestants.org/wp-content/uploads/2010/12/facebook-default-no-profile-pic.jpg" %>" width="50" height="50"></img>
            </td>
            <td>
                <a href="<%= "playerstats.jsp?player=" + ms.getPlayer().getName() %>"><%= ms.getPlayer().getName() %></a>
            </td>
            <td>
                <%= ms.wins + ms.losses + ms.draws %>
            </td>
            <td>
                <%= ms.wins %>
            </td>
            <td>
                <%= ms.draws %>
            </td>
            <td>
                <%= ms.losses %>
            </td>
            <td>
                <%= ms.goalsScored %>
            </td>
            <td>
                <%= ms.goalsConceded %>
            </td>
            <td>
                <%= ms.points %>
            </td>
            <td>
                <%= new DecimalFormat("#.##").format(ms.avgPoints) %>
            </td>
            <td>
                <%= ms.getPlayer().getRating() %>
            </td>
        </tr>
        <%
    }
    %>
</table>

<br/>
<br/>

<table border="1">
    <%
    for (int i = 0; i < scoreBoard.getNumberOfMatches(); i++) {
        Match m = scoreBoard.getMatch(i);
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

<br/>
<br/>


