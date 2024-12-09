package st10462078;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    static String storedUsername = "";
    static String storedPassword = "";
    static String storedFirstName = "";
    static String storedLastName = "";
   

    public static boolean checkUserName(String username) {
        String regex = "^(?=.*?_).{1,5}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean checkPasswordComplexity(String password) {
        String regExpn = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(regExpn);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
    
    //Registration of 
    public static String registerUser() {
        Scanner input = new Scanner(System.in);
        //First Name Input
        System.out.println("Please enter your first name");
        String firstName = input.nextLine();
        storedFirstName = firstName;
        System.out.println("Please enter your last name");
        String lastName = input.nextLine();
        storedLastName = lastName;
        System.out.println("My name is " + firstName + " " + lastName);

        //Username Input
        System.out.println("Please create your username");
        String inputUsername = input.nextLine();

        if (checkUserName(inputUsername)) {
            storedUsername = inputUsername;
            System.out.println("Username successfully captured");
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";

        }

        //Password Input
        System.out.println("Please create a password");
        String inputPassword = input.nextLine();

        if (checkPasswordComplexity(inputPassword)) {
            storedPassword = inputPassword;
            System.out.println("Password successfully captured");
            return "Registration Successful";
        } else {

            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

    }

    //loginuser
    public static boolean LoginDetails(){
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your username");
        String inputUsername = input.nextLine();
        System.out.println("Please enter your Password");
        String inputPassword = input.nextLine();
        
        return LoginUser(inputUsername ,inputPassword);
    }
    
    
    public static boolean LoginUser(String inputUsername,String inputPassword) {
        

        return (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword));
    }

    public static String loginStatus(boolean isLoggedIn) {

        if (!isLoggedIn) {
            return "login failed.";
        }

        {

            return ("Welcome " + storedFirstName + "," + storedLastName + " it is great to see you.");
            
        }

    }

}
