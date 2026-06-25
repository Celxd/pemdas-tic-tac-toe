import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerService {

    public Player login(String username, String password) {
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uname = rs.getString("username");
                    int wins = rs.getInt("wins");
                    int losses = rs.getInt("losses");
                    int draws = rs.getInt("draws");
                    int score = rs.getInt("score");
                    return new Player(id, uname, wins, losses, draws, score);
                }
            }
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
        }
        return null;
    }

    public void updateStatistics(Player player, String result) {
        int additionalScore = 0;
        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {
            additionalScore = 10;
            sql = "UPDATE players SET wins = wins + 1, score = score + ? WHERE id = ?";

            player.setWins(player.getWins() + 1);
            player.setScore(player.getScore() + additionalScore);

        } else if (result.equalsIgnoreCase("LOSE")) {
            additionalScore = 0;
            sql = "UPDATE players SET losses = losses + 1, score = score + ? WHERE id = ?";
            
            player.setLosses(player.getLosses() + 1);

        } else if (result.equalsIgnoreCase("DRAW")) {
            additionalScore = 3;
            sql = "UPDATE players SET draws = draws + 1, score = score + ? WHERE id = ?";
            
            player.setDraws(player.getDraws() + 1);
            player.setScore(player.getScore() + additionalScore);

        }

        if (sql.isEmpty()) {
            return;
        }

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, additionalScore);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Update statistics error: " + e.getMessage());
        }
    }

    public ArrayList<Player> getTopFiveScorers() {
        ArrayList<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY score DESC, wins DESC LIMIT 5";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");
                list.add(new Player(id, username, wins, losses, draws, score));
            }
        } catch (Exception e) {
            System.err.println("Error getting top scorers: " + e.getMessage());
        }
        return list;
    }

    public Player getPlayerById(int playerId) {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    int wins = rs.getInt("wins");
                    int losses = rs.getInt("losses");
                    int draws = rs.getInt("draws");
                    int score = rs.getInt("score");
                    return new Player(playerId, username, wins, losses, draws, score);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching player by ID: " + e.getMessage());
        }
        return null;
    }
}
