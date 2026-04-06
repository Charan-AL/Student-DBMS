
//Import AWT classes for layout and UI styling
import java.awt.*;
//Import event handling classes
import java.awt.event.*;
//Import Swing compenents (JFrame,JBUtton,JTextField,etc)
import javax.swing.*;

// Create a class HomePage that extends JFrame and implements ActionListener(to handle clicks)
public class HomePage extends JFrame implements ActionListener {

    JButton loginButton;
    JButton registerButton;

    public HomePage() {
        // Frame settings
        setTitle("Home Page");
        setSize(400, 250);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());// Layout

        // title label
        JLabel title = new JLabel("Student DBMS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // register button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // Add Panel to center
        add(panel, BorderLayout.CENTER);
        // Make Visible
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks here

        if (e.getSource() == loginButton)// for login
        {
            new LoginPage();
            dispose();

        }
        // if (e.getSource() == registerButton) {
        //     new RegisterPage();
        //     dispose();
        // }

    }

    public static void main(String[] args) {
        new HomePage();
    }
}
