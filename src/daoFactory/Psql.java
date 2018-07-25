package daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.concrete.PsqlUserDao;
import dao.interfaces.UserDao;

public class Psql extends DaoFactory {
	
	private static String url = "jdbc:postgresql://127.0.0.1:5432/";
	private static String database = "mailsystem";
	private static String driver = "org.postgresql.Driver";
	private static String user = "postgres";
	private static String password = "pgroot";
	
	public Connection openConnection() {   
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + database, user, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println("Fehler keine Datenbankverbindung");
		}
		return null;
	}
	
	@Override
	public UserDao getUserDao() {
		return new PsqlUserDao();
	}
}
