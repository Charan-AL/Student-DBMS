import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

	JTextField userField;
	JPasswordField passField;
	JButton loginButton;
	JButton backButton;
	JLabel registerLink;

	public LoginPage() {
		setTitle("Login Page");
		setSize(420, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		JLabel title = new JLabel("Student DBMS Login", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		add(title, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

		formPanel.add(new JLabel("Username:"));
		userField = new JTextField();
		formPanel.add(userField);

		formPanel.add(new JLabel("Password:"));
		passField = new JPasswordField();
		formPanel.add(passField);

		add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(110, 35));
		loginButton.addActionListener(this);
		buttonPanel.add(loginButton);

		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(110, 35));
		backButton.addActionListener(this);
		buttonPanel.add(backButton);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		southPanel.add(buttonPanel);

		registerLink = new JLabel("<html>New user? Don\'t have an account yet? <u>Register here</u></html>");
		registerLink.setForeground(Color.BLUE.darker());
		registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerLink.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerLink.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
		registerLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterPage();
				dispose();
			}
		});

		southPanel.add(registerLink);

		add(southPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String username = userField.getText().trim();
			String password = new String(passField.getPassword()).trim();

			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter both username and password.");
			} else {
				JOptionPane.showMessageDialog(this, "Login successful for: " + username);
			}
		}

		if (e.getSource() == backButton) {
			new HomePage();
			dispose();
		}
	}
}
