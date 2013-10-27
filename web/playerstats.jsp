<jsp:useBean id="scoreBoard" class="matchStatistics.ScoreBoard" scope="page" />
<jsp:useBean id="match" class="matchStatistics.Match" scope="page" />
<jsp:setProperty name="match" property="*" />

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
    </tr>
    <%
    String playerName = request.getParameter("player");
    for (MatchStatistics ms : scoreBoard.getMatchStatistics()) {
        if (!ms.getPlayer().getName().equalsIgnoreCase(playerName)) {
            continue;
        }
        %>
        <tr>
            <td>
                <img src = "<%= ms.getPlayer().getPicture() != null ? ms.getPlayer().getPicture() : "http://www.faithlineprotestants.org/wp-content/uploads/2010/12/facebook-default-no-profile-pic.jpg" %>" width="50" height="50"></img>
            </td>
            <td>
                <%= ms.getPlayer().getName() %>
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
        </tr>
        <%
    }
    %>
</table>


