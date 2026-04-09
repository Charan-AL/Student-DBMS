import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dao.AdminDAO;
import model.Admin;

public class RegisterPage extends JFrame implements ActionListener {

	JTextField nameField;
	JPasswordField passField;
	JButton registerButton;
	JButton backButton;
	JLabel loginLink;

	public RegisterPage() {
		setTitle("Register Page");
		setSize(420, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		JLabel title = new JLabel("Student DBMS Registration", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		add(title, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

		formPanel.add(new JLabel("Name:"));
		nameField = new JTextField();
		formPanel.add(nameField);

		formPanel.add(new JLabel("Password:"));
		passField = new JPasswordField();
		formPanel.add(passField);

		add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(110, 35));
		registerButton.addActionListener(this);
		buttonPanel.add(registerButton);

		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(110, 35));
		backButton.addActionListener(this);
		buttonPanel.add(backButton);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		southPanel.add(buttonPanel);

		loginLink = new JLabel("<html>Already have an account? <u>Login here</u></html>");
		loginLink.setForeground(Color.BLUE.darker());
		loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginLink.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginLink.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
		loginLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginPage();
				dispose();
			}
		});

		southPanel.add(loginLink);

		add(southPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

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

	public boolean saveUser(String name, String password) {
		Admin admin = new Admin(name, password);
		AdminDAO dao = new AdminDAO();

		boolean success = dao.register(admin);

		if (success) {
			JOptionPane.showMessageDialog(this, "Admin registered successfully!");
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
