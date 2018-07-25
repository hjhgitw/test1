package view.users;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import controllers.users.UserController;
import model.User;

public class Form extends JDialog {

	private static final long serialVersionUID = 1L;

	private static Form form = new Form();

	private Long id = null;
	private JTextField jtfName;
	private JTextField jtfLogin;

	private JButton jbSave;
	private JButton jbCancel;

	public Form() {
		createForms();
		createButtons();
		registerListeners();
		configure();
	}

	public Long getId() {
		return id;
	}

	public static void setId(Long id) {
		form.id = id;
	}

	public static void setJtfName(String jtfName) {
		form.jtfName.setText(jtfName);
	}

	public static void setJtfLogin(String jtfLogin) {
		form.jtfLogin.setText(jtfLogin);
	}

	private void configure() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(this.getRootPane());
	}

	private void createForms() {
		JPanel jpForm = new JPanel(new GridLayout(2, 1, 0, 5));

		jpForm.setBorder(BorderFactory.createTitledBorder("Personendaten"));

		jpForm.add(fieldset(new JLabel("Name: "), jtfName = new JTextField(30)));

		jpForm.add(fieldset(new JLabel("Login: "), jtfLogin = new JTextField(30)));

		this.add(jpForm, BorderLayout.CENTER);
	}

	private JPanel fieldset(JComponent... components) {
		JPanel fieldset = new JPanel();
		for (JComponent component : components) {
			fieldset.add(component);
		}
		return fieldset;
	}

	private void createButtons() {
		JPanel jpButtons = new JPanel();

		jpButtons.add(jbSave = new JButton("Speichern"));
		jpButtons.add(jbCancel = new JButton("Abbrechen"));
		this.add(jpButtons, BorderLayout.SOUTH);
	}

	private void registerListeners() {
		jbSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdSave();
			}
		});
		jbCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdCancel();
			}
		});
	}

	private void cmdSave() {
		try {
			User user = new User(jtfName.getText(), jtfLogin.getText());
			if (id != null) {
				user.setId(id);
				UserController.getInstance().update(user);
			} else {
				UserController.getInstance().save(user);
			}
			JOptionPane.showMessageDialog(this, "Erfolgreich gespeichert", "", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			// e.printStackTrace();
		}
		dispose();
	}

	private void cmdCancel() {
		dispose();
	}

	private void clearForm(JTextComponent... jtcomponets) {
		for (JTextComponent component : jtcomponets) {
			component.setText("");
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		clearForm(jtfName, jtfLogin);
	}

	public static void toggle() {
		form.setVisible(!form.isVisible());
	}
}