import dao.AdminDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginButton;
    JButton backButton;
    JLabel registerLink;

    public LoginPage() {

        // ===== Frame =====
        setTitle("Login");
        setSize(500, 380);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Main Panel =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(235, 240, 245));
        add(mainPanel);

        // ===== Title =====
        JLabel title = new JLabel("Welcome Back", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // ===== Card Panel =====
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(5, 1, 10, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(25, 40, 25, 40)
        ));

        // ===== Username =====
        card.add(createLabel("Username"));
        userField = createTextField();
        card.add(userField);

        // ===== Password =====
        card.add(createLabel("Password"));
        passField = new JPasswordField();
        styleTextField(passField);
        card.add(passField);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        loginButton = createButton("Login", new Color(0, 123, 255));
        backButton = createButton("Back", new Color(108, 117, 125));

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        card.add(buttonPanel);

        // ===== Wrapper =====
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(235, 240, 245));
        wrapper.add(card);

        mainPanel.add(wrapper, BorderLayout.CENTER);

        // ===== Register Link =====
        registerLink = new JLabel("New user? Register here");
        registerLink.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        registerLink.setForeground(new Color(0, 123, 255));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.setHorizontalAlignment(JLabel.CENTER);
        registerLink.setBorder(new EmptyBorder(10, 0, 15, 0));

        registerLink.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerLink.setText("<html><u>New user? Register here</u></html>");
            }

            public void mouseExited(MouseEvent e) {
                registerLink.setText("New user? Register here");
            }

            public void mouseClicked(MouseEvent e) {
                new RegisterPage();
                dispose();
            }
        });

        mainPanel.add(registerLink, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== Label =====
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    // ===== TextField =====
    private JTextField createTextField() {
        JTextField field = new JTextField();
        styleTextField(field);
        return field;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)
        ));
    }

    // ===== Button =====
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        btn.addActionListener(this);
        return btn;
    }

    // ===== Actions =====
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
                return;
            }

            AdminDAO dao = new AdminDAO();
            boolean valid = dao.login(username, password);

            if (valid) {
                JOptionPane.showMessageDialog(this, "Login successful for: " + username);
                // TODO: navigate to the next page after successful login
            } else {
                String error = dao.getLastErrorMessage();
                if (error != null && !error.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Login failed: ");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            }
        }

        if (e.getSource() == backButton) {
            new HomePage();
            dispose();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}