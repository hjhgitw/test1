package model;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void before_each_test() throws SQLException{
		User.deleteAll();
		user = new User("Hansjoerg", "Hofer");
	}

	@Test
	public void should_create_a_user() throws SQLException{
		user.save();
		assertEquals("Hansjoerg", user.getName());
		assertEquals("Hofer", user.getLogin());
		assertNotNull(user.getId());
	}
	
	public void testFindByname() throws SQLException {

		new User("Hansjoerg", "Hofer").save();
		new User("Hansjoerg1", "Hofer1").save();
		new User("Hansjoerg2", "Hofer2").save();
		new User("Hansjoerg3", "Hofer3").save();
		
		user = user.findByLogin("Hofer2");

		Assert.assertNotNull(user);
//		Assert.assertEquals(1, user.getId().intValue());
		Assert.assertEquals("Hansjoerg2", user.getName());
		Assert.assertEquals("Hofer2", user.getLogin());

	}
	
}
