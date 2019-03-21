package com.example.MoodVerse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoodVerseApplicationTests {

	@Autowired
	private DBRepository repository;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testAddUser(){
	boolean check = repository.addUser(new User("alex", "mittal", "alex@ab.com", "123456"));
		//Assert.assertEquals("User Exist Boolean Value", "shubham@gmail.com");
		Assert.assertTrue(check); //Passes if User is added
		//Assert.assertFalse(check); // Passes if addition fails
	}

	@Test
	public void testEmail(){
		boolean test = repository.checkEmail("alex@ab.com");
		Assert.assertTrue(test);

	}

	@Test
	public void testUserLogin(){
		User inUser = new User("alex@ab.com","123456");
		User TestUser = repository.userLoginVerify(inUser);

		//Assert.assertEquals("if null",null,TestUser);
		Assert.assertEquals("checking if the user is in the DB", "alex@ab.com", TestUser.getEmail());
	}

	@Test
	public void testgetMovieByColor(){
		ArrayList<Movie> movies = new ArrayList<>();
		movies = repository.getMovieByColor("red");
		Assert.assertEquals("check for the size of movies arraylist", 5, movies.size());
		//Assert.assertEquals("first movie should be pulp fiction", "Pulp Fiction", movies.get(0).getTitle());

	}

	@Test
	public void testDeleteUser(){
		repository.deleteUser("alex@ab.com");
		boolean test = repository.checkEmail("alex@ab.com");
		Assert.assertFalse(test);
	}

    @Test
    public void testUpdateUserEmail(){

        repository.updateUserEmail("alex@gmail.com", "alex@ab.com");

        boolean test = repository.checkEmail("alex@gmail.com");
       // Assert.assertFalse(test); //passes if email does not exist in databse
        Assert.assertTrue(test); //passes if email exist in databse
    }

    @Test
    public void testUpdateUserPassword(){

        repository.updateUserPassword("112233", "alex@gmail.com");

        User inUser = new User("alex@gmail.com","112233");
        User TestUser = repository.userLoginVerify(inUser);
        Assert.assertEquals("checking if the user is in the DB", "alex@gmail.com", TestUser.getEmail());
    }


	@Test
	public void testaddColorToMoodHistory(){
		//int userid = repository.getUserID("chandler@friends.com");
		repository.addColorToMoodHistory("sky_blue", "chandler@friends.com");
		//User TestUser = repository.addColorToMoodHistory();
		//Assert.assertEquals("checking if the user added colour to mood history", "chandler@friends.com", TestUser.getEmail());
		//Assert.assertEquals("check user id",42,userid);
	}

	@Test
	public void testfrequencyOfUserColorHistory(){
	HashMap<String, Integer> moodHistoryTest = repository.frequencyOfUserColorHistory("chandler@friends.com");
	Assert.assertEquals("checking frequency", 3L, (moodHistoryTest.get("red")).longValue());

	}

}