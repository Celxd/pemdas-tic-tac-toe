import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        playerService = new PlayerService();

        // Fetch latest stats from the database to ensure correctness
        Player latestPlayer = playerService.getPlayerById(player.getId());
        if (latestPlayer == null) {
            latestPlayer = player; // Fallback to passed object if DB error
        }

        // Window properties
        setTitle("My Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(360, 340);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Core panel
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 12, 8, 12);

        // Title
        JLabel lblTitle = new JLabel("Personal Statistics", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 8, 20, 8);
        contentPane.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 12, 8, 12);

        // Show stats rows
        addStatRow(contentPane, gbc, 1, "Username:", latestPlayer.getUsername());
        addStatRow(contentPane, gbc, 2, "Wins:", String.valueOf(latestPlayer.getWins()));
        addStatRow(contentPane, gbc, 3, "Losses:", String.valueOf(latestPlayer.getLosses()));
        addStatRow(contentPane, gbc, 4, "Draws:", String.valueOf(latestPlayer.getDraws()));
        addStatRow(contentPane, gbc, 5, "Total Score:", String.valueOf(latestPlayer.getScore()) + " pts");

        // Close Button
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClose.setBackground(new Color(52, 152, 219));
        btnClose.setForeground(Color.BLACK);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> dispose());
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 8, 0, 8);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        btnClose.setPreferredSize(new Dimension(90, 30));
        contentPane.add(btnClose, gbc);
    }

    private void addStatRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, String valueText) {
        JLabel lblName = new JLabel(labelText);
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblName.setForeground(new Color(127, 140, 141));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblName, gbc);

        JLabel lblVal = new JLabel(valueText);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblVal.setForeground(new Color(44, 62, 80));
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblVal, gbc);
    }
}
