import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        // Window properties
        setTitle("Game Login Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Core panel
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        // Title Label
        JLabel lblTitle = new JLabel("Player Authentication", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 8, 20, 8);
        contentPane.add(lblTitle, gbc);

        // Reset gridwidth
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 8, 8, 8);

        // Username Label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setForeground(new Color(79, 93, 117));
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblUsername, gbc);

        // Username Field
        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(txtUsername, gbc);

        // Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(new Color(79, 93, 117));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblPassword, gbc);

        // Password Field
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(txtPassword, gbc);

        // Login Button Panel
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(100, 35));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 8, 0, 8);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btnLogin, gbc);

        // Action Listener for Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Please enter both username and password.",
                            "Missing Fields",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Player player = playerService.login(username, password);
                if (player != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Login successful! Welcome, " + player.getUsername() + ".",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    MainMenuFrame menuFrame = new MainMenuFrame(player);
                    menuFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password!",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
