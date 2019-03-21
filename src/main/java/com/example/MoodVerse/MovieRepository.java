package com.example.MoodVerse;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

    @Service
    public class MovieRepository {

        private List<Movie> movies;

        public MovieRepository() {
            // this function need to get data from DB and verify.
            // we will use JDBC to send a query to the user table
            // and check if that user exits in the DB and if the infomration
            // match then we set that verification is passed. SO return
            // the current user as a logged in one. IF we don't find
            // a user in the database, then we return NULL:
        }


    }//last brace


/**
 *  movies = new ArrayList<>();
 *
 *             movies.add(new Movie("Life Is Beautiful", 1997, Color.yellow, "tt0118799"));
 *             movies.add(new Movie("The Intouchables", 2011, Color.yellow, "tt1675434"));
 *             movies.add(new Movie("Snatch", 2000, Color.yellow, "tt0208092"));
 *             movies.add(new Movie("Lock, Stock and Two Smoking Barrels", 1998, Color.yellow, "tt0120735"));
 *             movies.add(new Movie("The Grand Budapest Hotel", 2014, Color.yellow, "tt2278388"));
 *
 *             movies.add(new Movie("Psycho", 1960, Color.dark_green, "tt0054215"));
 *             movies.add(new Movie("The Shining", 1980, Color.dark_green, "tt0081505"));
 *             movies.add(new Movie("The Exorcist", 1973, Color.dark_green, "tt0070047"));
 *             movies.add(new Movie("Get Out", 2017, Color.dark_green, "tt5052448"));
 *             movies.add(new Movie("A Quiet Place", 2018, Color.dark_green, "tt6644200"));
 *
 *             movies.add(new Movie("Eternal Sunshine of the Spotless Mind", 2004, Color.blue, "tt0338013"));
 *             movies.add(new Movie("Before Sunset", 2004, Color.blue, "tt0381681"));
 *             movies.add(new Movie("before Sunrise", 1995, Color.blue, "tt0112471"));
 *             movies.add(new Movie("La La Land", 2016, Color.blue, "tt3783958"));
 *             movies.add(new Movie("Her", 2013, Color.blue, "tt1798709"));
 *
 *             movies.add(new Movie("New York Doll", 2005, Color.orange, "tt0436629" ));
 *             movies.add(new Movie("The Shawshank Redemption", 1994, Color.orange, "tt0111161"));
 *             movies.add(new Movie("The Pursuit of Happyness", 2006, Color.orange, "tt0454921"));
 *             movies.add(new Movie("Up", 2009, Color.orange, "tt1049413"));
 *             movies.add(new Movie("Cinderella Man", 2005, Color.orange, "tt0352248"));
 *
 *             movies.add(new Movie("Pulp Fiction", 1994, Color.red, "tt0110912"));
 *             movies.add(new Movie("Kill Bill: Vol.1", 2003, Color.red, "tt0266697"));
 *             movies.add(new Movie("Kill Bill: Vol. 2", 2004, Color.red, "tt0378194"));
 *             movies.add(new Movie("Inglourious Basterds", 2009, Color.red, "tt0361748"));
 *             movies.add(new Movie("Django Unchained", 2012, Color.red, "tt1853728"));
 *
 *             movies.add(new Movie("The Karate Kid", 1984, Color.bright_green, "tt0087538"));
 *             movies.add(new Movie("Marley & Me", 2008, Color.bright_green, "tt0822832" ));
 *             movies.add(new Movie("How to Train Your Dragon", 2010, Color.bright_green, "tt0892769"));
 *             movies.add(new Movie("Brave", 2012, Color.bright_green, "tt1217209"));
 *             movies.add(new Movie("Frozen", 2013, Color.bright_green, "tt2294629"));
 *
 *             movies.add(new Movie("Se7en", 1995, Color.sky_blue, "tt0114369"));
 *             movies.add(new Movie("The Prestige", 2006, Color.sky_blue, "tt0482571"));
 *             movies.add(new Movie("The Departed", 2006, Color.sky_blue, "tt0407887"));
 *             movies.add(new Movie("The Lives of Others", 2006, Color.sky_blue, "tt0405094"));
 *             movies.add(new Movie("The Silence of the Lambs", 1991, Color.sky_blue, "tt0102926"));
 *
 *             movies.add(new Movie("Wrong Turn", 2003, Color.purple, "tt0295700"));
 *             movies.add(new Movie("Saw", 2004, Color.purple, "tt03875"));
 *             movies.add(new Movie("Hostel", 2005, Color.purple, "tt04502"));
 *             movies.add(new Movie("Saw 2", 2005, Color.purple, "tt04323"));
 *             movies.add(new Movie("Wrong Turn 2: Dead End", 2007, Color.purple, "tt08045"));
 *
 *
 *               public Movie getMovie(int index){
 *             Movie movie = null;
 *             if(index >= 0 && index < movies.size()){
 *                 movie = movies.get(index);
 *             }
 *             else{
 *                 movie = null;
 *             }
 *             return movie;
 *         }
 *
 *         public int size(){
 *             return movies.size();
 *         }
 */
