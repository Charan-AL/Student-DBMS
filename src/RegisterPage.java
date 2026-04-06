import java.awt.*;
import javax.swing.*;

public class RegisterPage extends JFrame {

    public RegisterPage() {

        // Frame
        setTitle("Register Form");
        setSize(350, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== Header =====
        JLabel header = new JLabel("Student Registration", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        // ===== Form Panel =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10)); // increased rows

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel usnLabel = new JLabel("USN:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passLabel = new JLabel("Password:");
        JLabel phoneLabel = new JLabel("Phone:");

        // Fields
        JTextField nameField = new JTextField();
        JTextField usnField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField phoneField = new JTextField();

        // Buttons
        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");
        JButton loginBtn = new JButton("Already have account? Login");

        // Add components
        panel.add(nameLabel); panel.add(nameField);
        panel.add(usnLabel); panel.add(usnField);
        panel.add(emailLabel); panel.add(emailField);
        panel.add(passLabel); panel.add(passField);
        panel.add(phoneLabel); panel.add(phoneField);

        // Buttons row
        panel.add(backBtn);
        panel.add(registerBtn);

        // Login row
        panel.add(new JLabel("")); // empty space
        panel.add(loginBtn);

        add(panel, BorderLayout.CENTER);

        // ===== Register Action =====
        registerBtn.addActionListener(e -> {

            String name = nameField.getText().trim();
            String usn = usnField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passField.getPassword());
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || usn.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Invalid email!");
                return;
            }

            if (password.length() < 4) {
                JOptionPane.showMessageDialog(this, "Password must be at least 4 characters!");
                return;
            }

            if (!phone.isEmpty() && !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Phone must be digits only!");
                return;
            }

            saveUser(name, usn, email, password, phone);

            JOptionPane.showMessageDialog(this, "Registration Successful!");

            nameField.setText("");
            usnField.setText("");
            emailField.setText("");
            passField.setText("");
            phoneField.setText("");
        });

        // ===== Back Button =====
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage();
        });

        // ===== Login Redirect Button =====
        loginBtn.addActionListener(e -> {
            dispose();          // close register page
            new LoginPage();    // open login page
        });

        // Window settings
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Save user
    public void saveUser(String name, String usn, String email, String password, String phone) {
        System.out.println("User Saved:");
        System.out.println(name + " | " + usn + " | " + email + " | " + password + " | " + phone);
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}