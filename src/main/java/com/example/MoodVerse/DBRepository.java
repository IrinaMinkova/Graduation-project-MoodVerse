package com.example.MoodVerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.HashMap;

@Repository
public class DBRepository {

    int userid = 0;

    @Autowired
    private DataSource dataSource;

    //our JDBC methods here

    //this functions gets a username and password from the users and check if it exists in the database. If the
    //user exists, the user is object is returned. If user is not found, null is returned.

    public User userLoginVerify(User inputUser){

        User approvedUser = new User();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE email = ? AND password = ?")){
            stmt.setString(1,inputUser.getEmail());
            stmt.setString(2,inputUser.getPassword());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                approvedUser = rsUser(rs);
                System.out.println("This is the user ");

            }
            else
            {
                approvedUser = null;
                System.out.println("NULL");
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return approvedUser;
    }


    // ************************* Registering Member and Adding it to database *************************

    public boolean addUser(User user) {
        boolean exist = false;
        if (!checkEmail(user.getEmail())) {

            try (Connection conn = dataSource.getConnection();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO MEMBER(FirstName, LastName, Email, Password) VALUES (?,?,?,?) ")) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.executeUpdate();
                exist = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            exist = false;
        }
        return exist;
    }


public boolean checkEmail(String email){
        boolean exist = false;
    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT TOP 1 Member.Email FROM Member WHERE Member.Email = ?")) {
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            exist=true; //This means email exists
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exist;
}


// ********************** Deleting User from Database ****************************

    public void deleteUser (String email){
        int memberId = getUserID(email);
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM MoodHistory WHERE MemberId = ?")) {
            ps.setInt(1, memberId);
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM MEMBER WHERE Email =?");
                ps2.setString(1, email);
                ps2.executeUpdate();

    }  catch (SQLException e) {
            e.printStackTrace();
    }

    } //end of deleteUser method


// *************** Update USer Password Method ******************************

    public void updateUserPassword(String PasswordNew, String email) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE MEMBER SET Password = ? WHERE Email =?")) {
            ps.setString(1, PasswordNew);
            ps.setString(2, email);
            ps.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }// End of updateUserPassword method


// ******************* Update User Email Method **************************

    public void updateUserEmail(String emailNew, String emailOld) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE MEMBER SET Email = ? WHERE Email =?")) {
            ps.setString(1, emailNew);
            ps.setString(2, emailOld);
            ps.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }// End of updateUserEmail method


// ********************* our JDBC methods here **********************

    public ArrayList<Movie> getMovieByColor(String color) {
        ArrayList<Movie> movies = new ArrayList<>();
        int index = 0, lastindex = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * " +
                     "FROM Genre " +
                     "Join Movie on Genre.GenreId = Movie.GenreId " +
                     "Where Genre.Color = ?")) {
            ps.setString(1, color);
            ResultSet rs = ps.executeQuery();

               while(rs.next() && index < 5) {
                   String newTitle = rs.getString("title");

                   if(index == 0){
                       movies.add(rsMovie(rs));
                       index++;
                       lastindex = index-1;
                      }
                   else if(movies.get(lastindex).getTitle().compareTo(newTitle) != 0) {
                       movies.add(rsMovie(rs));
                       index++;
                       lastindex = index-1;
                   }

                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // ****************** our helper methods here ********************

    private Movie rsMovie(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getString("title"),
                rs.getDate("year"),
                rs.getString("Omdb_Id"),
                rs.getString("Poster_path"),
                rs.getString("Description"),
                rs.getFloat("Popularity"),
                rs.getString("mood")
        );
    }

/*    private void rsUserid(ResultSet rs) throws SQLException{
        userid = rs.getInt("MemberId");
        //return userid;
    }*/


    private User rsUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("Email"),
                rs.getString("Password"));
    }

// ***************  Add user selection to Mood History Table *****************

    public int getUserID(String email) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT MemberId FROM Member Where email = ?")) {
            ps.setString(1, email);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()) {
                userid = rs.getInt("MemberId");
            }
            System.out.println("Got the userID "+userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userid;
    }


    public void addColorToMoodHistory(String color, String email) {
        int memberId = getUserID(email);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO MoodHistory (Color, MemberId) VALUES (?, ?)")) {
            ps.setString(1, color);
            ps.setInt(2, memberId);
            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }// End of addColorToMoodHistory method


// ****************** Retrieve User Color Selection ***********************

    public HashMap<String, Integer> frequencyOfUserColorHistory(String email) {
        HashMap<String, Integer> moodHistory = new HashMap<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("  SELECT COUNT(Color) As Frequency, Color, Email " +
                     "FROM MoodHistory " +
                     "JOIN Member on Member.MemberId = MoodHistory.MemberId " +
                     "WHERE Email = ? " +
                     "GROUP BY Color, Email " )) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getInt(1));
                moodHistory.put(rs.getString("Color"),rs.getInt("Frequency"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return moodHistory;
    }// End of addColorToMoodHistory method



}//class ends here