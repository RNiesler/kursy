import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BaseballElimination {

    private static class TeamEntity {
        int index;
        int wins;
        int losses;
        int remaining;
        int[] games;

        TeamEntity(int index, int wins, int losses, int remaining, int[] games) {
            this.index = index;
            this.wins = wins;
            this.losses = losses;
            this.remaining = remaining;
            this.games = games;
        }

    }

    private static class FFCalculation {
        FordFulkerson ff;
        FlowNetwork graph;
    }

    private HashMap<String, FFCalculation> cache = new HashMap<>();

    private HashMap<String, TeamEntity> teams = new HashMap<>();

    /**
     * create a baseball division from given filename in format specified below
     */
    public BaseballElimination(String filename) {
        In in = new In(filename);
        if (!in.hasNextLine()) {
            throw new IllegalArgumentException("Invalid format of input file");
        }
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String name = in.readString();
            int wins = in.readInt();
            int losses = in.readInt();
            int remaining = in.readInt();
            int[] games = new int[size];
            for (int j = 0; j < size; j++) {
                games[j] = in.readInt();
            }
            teams.put(name, new TeamEntity(i, wins, losses, remaining, games));

        }
    }

    /**
     * @return number of teams
     */
    public int numberOfTeams() {
        return teams.size();
    }

    /**
     * @return all teams
     */
    public Iterable<String> teams() {
        return teams.keySet();
    }

    /**
     * @return number of wins for given team
     */
    public int wins(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("No such team");
        }
        return teams.get(team).wins;
    }

    /**
     * @return number of losses for given team
     */
    public int losses(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("No such team");
        }
        return teams.get(team).losses;
    }

    /**
     * @return number of remaining games for given team
     */
    public int remaining(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("No such team");
        }
        return teams.get(team).remaining;
    }

    /**
     * @return number of remaining games between team1 and team2
     */
    public int against(String team1, String team2) {
        if (!teams.containsKey(team1)) {
            throw new IllegalArgumentException("No such team");
        }
        if (!teams.containsKey(team2)) {
            throw new IllegalArgumentException("No such team");
        }
        int i2 = teams.get(team2).index;
        return teams.get(team1).games[i2];
    }

    /**
     * @return is given team eliminated?
     */
    public boolean isEliminated(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("No such team");
        }
        FFCalculation calculation;
        if (cache.containsKey(team)) {
            calculation = cache.get(team);
        } else {
            calculation = new FFCalculation();
            calculation.graph = graphFor(teams.get(team));
            calculation.ff = new FordFulkerson(calculation.graph, getS(), getT());
            cache.put(team, calculation);
        }

        for (FlowEdge edge : calculation.graph.adj(getS())) {
            if (edge.capacity() > edge.flow()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return subset R of teams that eliminates given team; null if not eliminated
     */
    public Iterable<String> certificateOfElimination(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("No such team");
        }
        if (isEliminated(team)) {
            FFCalculation calculation = cache.get(team);
            LinkedList<String> r = new LinkedList<>();
            for (Map.Entry<String, TeamEntity> entry : teams.entrySet()) {
                if (calculation.ff.inCut(entry.getValue().index)) {
                    r.add(entry.getKey());
                }
            }
            return r;
        } else {
            return null;
        }
    }

    private FlowNetwork graphFor(TeamEntity teamEntity) {
        final int n = teams.size();
        final int vertices = 2 + (n * n - n) / 2 + n; // s + games (n^2-n)/2 + teams(n) + t
        final FlowNetwork graph = new FlowNetwork(vertices);

        for (TeamEntity team : teams.values()) {
            for (int i = team.index + 1; i < teams.size(); i++) {
                if (team.games[i] != 0) {
                    int gameVertex = getGameVertexInd(team.index, i);
                    // add edge from s to game vertex
                    graph.addEdge(new FlowEdge(getS(), gameVertex, team.games[i]));
                    // edges from game vertex to each team vertex
                    graph.addEdge(new FlowEdge(gameVertex, team.index, Double.POSITIVE_INFINITY));
                    graph.addEdge(new FlowEdge(gameVertex, i, Double.POSITIVE_INFINITY));
                }
            }
        }
        // edges from team vertex to t
        int maxWins = teamEntity.wins + teamEntity.remaining;
        graph.addEdge(new FlowEdge(teamEntity.index, getT(), teamEntity.remaining));
        teams.values().stream()
                .filter(entry -> !entry.equals(teamEntity))
                .forEach(entry -> {
                    graph.addEdge(new FlowEdge(entry.index, getT(), Math.max(0, maxWins - entry.wins)));
                });
        return graph;
    }


    private int getS() {
        return teams.size();
    }

    private int getT() {
        return teams.size() + 1;
    }

    private int getGameVertexInd(int v, int w) {
        if (w < v) {
            throw new RuntimeException("v must be smaller than w");
        }
        int ind = teams.size() + 1; // team vertices + s + t offset
        for (int i = 0; i < v; i++) {
            ind += teams.size() - i - 1;
        }
        ind += w - v;
        return ind;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
