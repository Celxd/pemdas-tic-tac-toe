import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        // Window properties
        setTitle("Game Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        // Core panel
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(25, 35, 25, 35));
        contentPane.setLayout(new GridLayout(6, 1, 0, 15));
        setContentPane(contentPane);

        // Greeting
        JLabel lblGreeting = new JLabel("Welcome, " + currentPlayer.getUsername() + "!", JLabel.CENTER);
        lblGreeting.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblGreeting.setForeground(new Color(44, 62, 80));
        contentPane.add(lblGreeting);

        // Start Game Button
        btnStartGame = createMenuButton("Start Game", new Color(46, 204, 113));
        contentPane.add(btnStartGame);

        // Statistics Button
        btnStatistics = createMenuButton("View My Statistics", new Color(52, 152, 219));
        contentPane.add(btnStatistics);

        // Top Scorers Button
        btnTopScorers = createMenuButton("View Top 5 Scorers", new Color(155, 89, 182));
        contentPane.add(btnTopScorers);

        // Exit Button
        btnExit = createMenuButton("Exit Game", new Color(231, 76, 60));
        contentPane.add(btnExit);

        // Add action listeners
        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }

    private JButton createMenuButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(bg);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return btn;
    }
}
