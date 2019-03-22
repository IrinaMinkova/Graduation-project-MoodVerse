package com.example.MoodVerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Controller

public class moodController {

    @Autowired
    private DBRepository repository;



// THIS IS FOR LOGIN PAGE WHERE USERS VERIFICATION IS TAKING PLACE

    @GetMapping("/")
    public String loginUser(HttpSession session){
       //initate a guest user.
        User intialUser = new User("guest", "guest", "guest@guest.com", "123456");
       session.setAttribute("user", intialUser);
        return "home";
    }

    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String verifyUser(HttpSession session, @RequestParam String email, @RequestParam String password){
        User inUser = new User(email,password);
        System.out.println("In post "+inUser.getEmail()+" "+inUser.getPassword());
        System.out.println(inUser.getPassword());

        User CurrentUser = repository.userLoginVerify(inUser); //verifying user

        if(CurrentUser == null){
            return "login"; // user not found, so display message "username or password incorrect"
        }

        // here we can put an else if condition if new guest HTMLs are there

       else {
            System.out.println(" member ! *****" + CurrentUser.getFirstName());
            session.setAttribute("user",CurrentUser); // add Guest user to the session by default
            return "home";
        }
    }


// THIS IS FOR HOME PAGE WHERE USER (GUEST OR LOGGED IN USER) CAN INPUT THEIR COLOR/MOOD INFO

    @GetMapping("/home")
    public String startSession(){

        return "home";
    }

    @GetMapping("/home1")
    public String duringSession(HttpSession session, @RequestParam Color color){
       System.out.println("yaha aya  +  " + color);
       User userData = (User)session.getAttribute("user");

       System.out.println(userData.getEmail());
       String colorStr = color.toString();

       if(userData == null){
           System.out.println(userData + "*************");
           return "login";
         }
        System.out.println(userData.getFirstName());

//Adding member color selection to databse

        if(!userData.getFirstName().equals("guest")){
            System.out.println("Ab mai database main history add karunga");
            System.out.println(colorStr + "   " + userData.getEmail());
        repository.addColorToMoodHistory(colorStr, userData.getEmail());
        }
        ArrayList<Movie> movies = repository.getMovieByColor(colorStr);

        System.out.println("user logged in : "+userData.getFirstName());

        for(int i=0; i<movies.size(); i++){
            System.out.println("found a match: "+movies.get(i).getTitle() + "  " + movies.get(i).getPoster_path() + "  " + movies.get(i).getMood());
        }
        System.out.println("no. of movies from DB: "+movies.size());
        session.setAttribute("user",userData);
        session.setAttribute("MovieArray",movies);
        return "results";
    }

//THIS IS FOR LOGGING OUT FROM THE WEBSITE AND GOTO LOGIN:

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");         //remove the user
        session.removeAttribute("MovieArray");   //remove the data
        session.invalidate();
        return "login";
    }

    //THIS IS FOR ACCESSING PROFILE PAGE:

    @GetMapping("/profile")
    public String gotoProfile(Model model){
HashMap<String, Integer> moodHistory = new HashMap<>();
model.addAttribute("moodHistory", moodHistory);
        return "profile";
    }

    @PostMapping("/profile")
    public String displayProfile(HttpSession session, Model model){
        User userData = (User)session.getAttribute("user");
        System.out.println(userData.getFirstName());
        session.setAttribute("user", userData);

          // ***************** Taking user color selection frequency *******************

        HashMap<String, Integer> moodHistory = repository.frequencyOfUserColorHistory(userData.getEmail());
        model.addAttribute("moodHistory", moodHistory);
        System.out.println("Trying to check frequency functionality");
        System.out.println("This is frequency of  red color chosen by " +userData.getFirstName() + " " + " Frequency : " + moodHistory.get("red"));
        return "profile";
    }

/*
    @GetMapping("/About")
    public String gotoPresentation(){
        return "Presentation";
    }
*/

// ***************    SignUp Page   ********************


    @GetMapping("/registration")
    public  String signUpMember (Model model, HttpSession session){
        model.addAttribute("user", new User());
        System.out.println("getmapping controller in registration");
        return "registration";
    }

    @PostMapping("/registration")
    public  String signUpMember (@Valid User user, BindingResult result, Model model, HttpSession session){
        MemberValidator memberValidator = new MemberValidator();
        if(memberValidator.supports(user.getClass())){
            memberValidator.validate(user, result);
        }
        if(result.hasErrors()){
            model.addAttribute("errorMsg", "Validation failed, please enter correct details");
            return "registration";
        }

        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        boolean userCheck = repository.addUser(user);
        if(userCheck) {
            session.setAttribute("user",user);
            return "home"; //page where member will go after registration
        }else{
            return "registration";
        }
    }




// ********************* All operations which can be done on Profile Page *******************

    /*
// Change Email

    @PostMapping("/ChangeEmail")
    public String ChangeUserEmail(HttpSession session, @RequestParam String emailOld, @RequestParam String emailNew ){
        User userData = (User)session.getAttribute("user");
        //String emailOld = userData.getEmail();
        System.out.println(userData.getEmail());
        repository.updateUserEmail(emailNew, emailOld);
        session.setAttribute("user", userData);
        System.out.println(userData.getEmail());
        return "redirect:/Profile";
    }
*/

// Change Password

    @GetMapping("/passwordReset")
    public String ChangeUserPassword(){
    return "passwordReset";
    }


    @PostMapping("/passwordReset")
    public String ChangeUserPassword(HttpSession session, @RequestParam String newPassword, @RequestParam String email ){
        repository.updateUserPassword(newPassword, email);
        return "redirect:/login";
    }

// Delete User

    @PostMapping("/deleteAccount")
    public String deleteUser(HttpSession session, @RequestParam String email){
        System.out.println("I came here to delete account");
        System.out.println(email);
        User userData = (User)session.getAttribute("user");
       email = userData.getEmail(); //if to use this method then button in html will not display user email
        repository.deleteUser(email);
        session.invalidate();
        return "login";
    }


}//class ends here
