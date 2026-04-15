import dao.AdminDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Admin;

public class RegisterPage extends JFrame implements ActionListener {

    JTextField nameField;
    JPasswordField passField;
    JButton registerButton;
    JButton backButton;
    JLabel loginLink;

    public RegisterPage() {

        // ===== Frame =====
        setTitle("Register");
        setSize(500, 380);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Main Panel =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(235, 240, 245));
        add(mainPanel);

        // ===== Title =====
        JLabel title = new JLabel("Create Account", JLabel.CENTER);
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

        // ===== Name Field =====
        card.add(createLabel("Name"));
        nameField = createTextField();
        card.add(nameField);

        // ===== Password Field =====
        card.add(createLabel("Password"));
        passField = new JPasswordField();
        styleTextField(passField);
        card.add(passField);

        // ===== Buttons Panel =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        registerButton = createButton("Register", new Color(40, 167, 69));
        backButton = createButton("Back", new Color(108, 117, 125));

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        card.add(buttonPanel);

        // ===== Wrapper =====
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(235, 240, 245));
        wrapper.add(card);

        mainPanel.add(wrapper, BorderLayout.CENTER);

        // ===== Login Link =====
        loginLink = new JLabel("Already have an account? Login");
        loginLink.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginLink.setForeground(new Color(0, 123, 255));
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.setHorizontalAlignment(JLabel.CENTER);
        loginLink.setBorder(new EmptyBorder(10, 0, 15, 0));

        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginLink.setText("<html><u>Already have an account? Login</u></html>");
            }

            public void mouseExited(MouseEvent e) {
                loginLink.setText("Already have an account? Login");
            }

            public void mouseClicked(MouseEvent e) {
                new LoginPage();
                dispose();
            }
        });

        mainPanel.add(loginLink, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== Styled Label =====
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    // ===== Styled TextField =====
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

    // ===== Styled Button =====
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

        if (e.getSource() == registerButton) {

            String name = nameField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (password.length() < 4) {
                JOptionPane.showMessageDialog(this, "Password must be at least 4 characters!");
                return;
            }

            boolean saved = saveUser(name, password);

            if (saved) {
                nameField.setText("");
                passField.setText("");
            }
        }

        if (e.getSource() == backButton) {
            new HomePage();
            dispose();
        }
    }

    // ===== Database Save =====
    public boolean saveUser(String name, String password) {

        Admin admin = new Admin(name, password);
        AdminDAO dao = new AdminDAO();

        boolean success = dao.register(admin);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registered successfully!");
            return true;
        }

        String errorMessage = dao.getLastErrorMessage();
        if (errorMessage == null || errorMessage.isBlank()) {
            errorMessage = "Registration failed.";
        }

        JOptionPane.showMessageDialog(this, errorMessage);
        return false;
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}