import dao.AdminDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginButton, backButton;
    JLabel registerLink;

    public LoginPage() {

        // Frame
        setTitle("Admin Login");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(240, 248, 255));
        bgPanel.setLayout(new GridBagLayout());
        add(bgPanel);

        // Card Panel
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 250));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        // Title
        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setHorizontalAlignment(JLabel.CENTER);

        userField = new JTextField();
        styleField(userField);

        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setHorizontalAlignment(JLabel.CENTER);

        passField = new JPasswordField();
        styleField(passField);

        // Buttons
        loginButton = new JButton("Login");
        styleButton(loginButton, new Color(70, 130, 180));

        backButton = new JButton("Back");
        styleButton(backButton, new Color(180, 70, 70));

        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(loginButton);
        btnPanel.add(backButton);

        // Register link
        registerLink = new JLabel("<html><u>New user? Register here</u></html>");
        registerLink.setForeground(new Color(30, 100, 200));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new RegisterPage();
                dispose();
            }
        });

        // Add components
        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        card.add(userLabel);
        card.add(userField);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        card.add(passLabel);
        card.add(passField);
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        card.add(btnPanel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        card.add(registerLink);

        bgPanel.add(card);

        setVisible(true);
    }

    // ===== Field Styling =====
    private void styleField(JTextField field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    // ===== Button Styling =====
    private void styleButton(JButton button, Color color) {
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }

    // ===== LOGIN LOGIC =====
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            AdminDAO dao = new AdminDAO();
            boolean isValid = dao.login(username, password);

            if (isValid) {
                JOptionPane.showMessageDialog(this, "Login Successful!");

                // 👉 Open dashboard here
                // new DashboardPage();

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == backButton) {
            new HomePage();
            dispose();
        }
    }
}