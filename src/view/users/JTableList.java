package view.users;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.User;
import view.listeners.EventListerner;
import controllers.users.UserController;
import controllers.users.listeners.MailEvent;
import controllers.users.listeners.UserListener;

public class JTableList extends JTable implements UserListener, EventListerner {

	private static final long serialVersionUID = 1L;

	private TableModel model = new TableModel();

	public JTableList() {
		this.setModel(model);
		this.getTableHeader().setReorderingAllowed(false);
		UserController.getInstance().addUserListener(this);
		loadUsers();
	}

	public void loadUsers() {
		try {
			for (User user : UserController.getInstance().allUsers()) {
				model.insertRow(0, user.toArray());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Fehler", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private class TableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		public TableModel() {
			super(new Object[][] {}, new String[] { "id", "Name", "login" });
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	public void useradd(MailEvent<User> event) {
		if (event.getSource().getId().intValue() > 0 && this.getSelectedRow() > -1) {
			model.removeRow(this.getSelectedRow());
		}
		model.insertRow(0, event.getSource().toArray());

	}

	public void cmdEdit() {
		System.out.println(this.getSelectedRow());

		if (this.getSelectedRow() != -1) {
			int row = this.getSelectedRow();
			Long userId = Long.parseLong((String) this.getValueAt(row, 0));
			User user = null;
			try {
				user = User.findById(userId);
				Form.setId(user.getId());
				Form.setJtfName(user.getName());
				Form.setJtfLogin(user.getLogin());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Form.toggle();
		}
	}

	public void cmdRemove() {
		if (this.getSelectedRow() != -1) {
			int row = this.getSelectedRow();
			Long userId = Long.parseLong((String) this.getValueAt(row, 0));
			try {
				UserController.getInstance().remove(userId);
				model.removeRow(row);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void cmdDetails() {
		System.out.println(this.getSelectedRow());
	}

	public void cmdAdd() {
		Form.setId(null);
		Form.toggle();
	}

}
