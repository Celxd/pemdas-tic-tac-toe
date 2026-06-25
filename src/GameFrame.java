import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        // Window properties
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        // Core panel
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout(0, 15));
        setContentPane(contentPane);

        // Top Status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(245, 247, 250));
        statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblStatus = new JLabel("Your Turn: Player (X) vs Computer (O)");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(new Color(44, 62, 80));
        statusPanel.add(lblStatus);
        contentPane.add(statusPanel, BorderLayout.NORTH);

        // Center Grid Board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 10, 10));
        boardPanel.setBackground(new Color(220, 224, 230));
        boardPanel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2));

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 48));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }
        contentPane.add(boardPanel, BorderLayout.CENTER);

        // Bottom panel for back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 247, 250));
        JButton btnBack = new JButton("Forfeit & Back to Menu");
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnBack.setBackground(new Color(149, 165, 166));
        btnBack.setForeground(Color.BLACK);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to forfeit? This counts as a loss.", 
                "Forfeit Game", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                finishGame("LOSE");
            }
        });
        bottomPanel.add(btnBack);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handlePlayerMove(int index) {
        if (gameLogic.makeMove(index, 'X')) {
            // Update button UI
            buttons[index].setText("X");
            buttons[index].setForeground(new Color(41, 128, 185));
            buttons[index].setBackground(new Color(235, 245, 251));
            buttons[index].setEnabled(false);

            if (gameLogic.checkWinner('X')) {
                finishGame("WIN");
                return;
            }

            if (gameLogic.isDraw()) {
                finishGame("DRAW");
                return;
            }

            // Computer move
            lblStatus.setText("Computer is thinking...");
            setBoardEnabled(false);

            Timer timer = new Timer(500, e -> {
                int compIndex = gameLogic.computerMove();
                if (compIndex != -1) {
                    gameLogic.makeMove(compIndex, 'O');
                    buttons[compIndex].setText("O");
                    buttons[compIndex].setForeground(new Color(231, 76, 60));
                    buttons[compIndex].setBackground(new Color(253, 237, 236));
                    buttons[compIndex].setEnabled(false);

                    if (gameLogic.checkWinner('O')) {
                        finishGame("LOSE");
                        return;
                    }

                    if (gameLogic.isDraw()) {
                        finishGame("DRAW");
                        return;
                    }
                }
                lblStatus.setText("Your Turn: Player (X) vs Computer (O)");
                setBoardEnabled(true);
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void setBoardEnabled(boolean enabled) {
        char[] board = gameLogic.getBoard();
        for (int i = 0; i < buttons.length; i++) {
            if (board[i] == ' ') {
                buttons[i].setEnabled(enabled);
            }
        }
    }

    private void finishGame(String result) {
        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }

        playerService.updateStatistics(currentPlayer, result);
        
        String dialogMsg;
        if (result.equalsIgnoreCase("WIN")) {
            dialogMsg = "Congratulations! You won the game (+10 points)!";
        } else if (result.equalsIgnoreCase("LOSE")) {
            dialogMsg = "Game Over! You lost (+0 points).";
        } else {
            dialogMsg = "It's a draw (+3 points)!";
        }

        JOptionPane.showMessageDialog(this, dialogMsg, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
