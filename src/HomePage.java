import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HomePage extends JFrame implements ActionListener {

    JButton loginButton;
    JButton registerButton;

    public HomePage() {

        // ===== Frame =====
        setTitle("Student DBMS");
        setSize(500, 350);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Main Panel =====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(235, 240, 245));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ===== Title =====
        JLabel title = new JLabel("Student DBMS", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(40, 40, 40));
        title.setBorder(new EmptyBorder(25, 0, 5, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // ===== Card Panel =====
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new GridLayout(3, 1, 15, 20));

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(40, 50, 40, 50)
        ));

        // Subtitle
        JLabel subtitle = new JLabel("Welcome! Choose an option", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(new Color(120, 120, 120));
        card.add(subtitle);

        // ===== Buttons =====
        loginButton = createStyledButton("Login", new Color(0, 123, 255));
        registerButton = createStyledButton("Register", new Color(40, 167, 69));

        card.add(loginButton);
        card.add(registerButton);

        // ===== Center Wrapper =====
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(235, 240, 245));
        wrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
        wrapper.add(card);

        mainPanel.add(wrapper, BorderLayout.CENTER);

        setVisible(true);
    }

    // ===== Custom Button Styling =====
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);

        button.setOpaque(true);              // Important for color visibility
        button.setBorderPainted(false);

        button.setPreferredSize(new Dimension(180, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        button.addActionListener(this);
        return button;
    }

    // ===== Button Actions =====
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            new LoginPage();   // Make sure this class exists
            dispose();
        }

        if (e.getSource() == registerButton) {
            new RegisterPage(); // Make sure this class exists
            dispose();
        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new HomePage();
    }
}