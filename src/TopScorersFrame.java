import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        // Window properties
        setTitle("Top 5 Scorers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Core panel
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 15));
        setContentPane(contentPane);

        // Title Label
        JLabel lblTitle = new JLabel("Top 5 Leaderboard", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        String[] columns = { "Username", "Wins", "Losses", "Draws", "Score" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();
        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                p.getUsername(),
                p.getWins(),
                p.getLosses(),
                p.getDraws(),
                p.getScore()
            });
        }

        // Table setup and styling
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(new Color(220, 224, 230));
        table.setSelectionBackground(new Color(52, 152, 219, 50));
        table.setSelectionForeground(Color.BLACK);

        // Style table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(52, 152, 219));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 247, 250));
        
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClose.setBackground(new Color(155, 89, 182));
        btnClose.setForeground(Color.BLACK);
        btnClose.setFocusPainted(false);
        btnClose.setPreferredSize(new Dimension(90, 32));
        btnClose.addActionListener(e -> dispose());
        bottomPanel.add(btnClose);
        
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }
}
