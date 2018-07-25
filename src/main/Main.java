package main;

import java.sql.SQLException;

import model.User;
import view.layout.MainJFrame;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		//textMode();
		MainJFrame.createAndShowGUI();
	}		

	@SuppressWarnings("unused")
	private static void textMode() throws SQLException{
		System.out.println("Datenbank");
		System.out.println(User.deleteAll() + " Benutzer geloescht");
		
		System.out.println("Generiere Benutzer");
		User user = new User("Hansjoerg", "Hofer");
		user.save();
		
		User user_a = new User("Hansjoerg", "Hofer_a");
		user_a.save();
		
		User user_b = new User("Hansjoerg", "Hofer_b");
		user_b.save();
		
		System.out.println("====================================");
		System.out.println("Liste der erstellten Benutzer");
		
		for (User u : User.all()) {
			System.out.println(u);
		}
	}
}
