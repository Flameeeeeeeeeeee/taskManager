/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10462078;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class ST10462078 {

    Scanner input = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //registration
        System.out.println("Register");
        String message = Login.registerUser();
        System.out.println(message);

        System.out.println();
        //Username extraction
        if (message.equals("Registration Successful")) {
            //login
            System.out.println("Login");
            boolean isLoggedIn = Login.LoginDetails();
            System.out.println();

            //Check Status
            String loginStatus = Login.loginStatus(isLoggedIn);
            System.out.println(loginStatus);

            //task
            System.out.println("menuOption");
            Task.menuOptions(isLoggedIn);
            System.out.println();
             
            //task Name
            
            //total tasks accsess
            
            
            
            
        }

    }
}
