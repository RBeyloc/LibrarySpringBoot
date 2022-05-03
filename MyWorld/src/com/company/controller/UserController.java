package com.company.controller;

import com.company.model.User;
import com.company.model.UserMap;
import com.company.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    //just an arraylist to store users
    static UserMap users = new UserMap();

    public static HashMap<String, String> createUser(HashMap<String, String> dataToCreateUser) {

        String name = dataToCreateUser.get("name");
        String surname = dataToCreateUser.get("surname");
        String birthdate = dataToCreateUser.get("birthdate");
        String address = dataToCreateUser.get("address");
        String email = dataToCreateUser.get("email");
        String phoneNumber = dataToCreateUser.get("phoneNumber");


        //Let s introduce data to create User
        User createdUser = new User(name, surname, birthdate, address, email, phoneNumber);

        //Let s add this new User object to the main (and just one) array
        boolean statusOperation = users.addUser(createdUser);

        HashMap<String, String> createUserResponse = new HashMap<>();
        createUserResponse.put("response", "createUserResponse");

        if (statusOperation) createUserResponse.put("status", "created");
        else createUserResponse.put("status", "not created");

        return createUserResponse;
    }

    public static void createFakeUsers() {
        //just to work with them, no having a void arraylist
        User newUser1 = new User("Alex", "Pixel", "1987-07-30", "Muntaner 555, 08032, Barcelona", "alex.pixel@gmail.com","619111435");
        User newUser2 = new User("Thomas", "Edison", "1982-07-30", "Carrer del Comte de Sert, 25, 08035, Barcelona", "Thomas@gmail.com", "653111345");
        User newUser3 = new User("Susan", "Lane", "1977-07-30", "Paseo de Gracia, 43, 08007 Barcelona", "lane@msn.com", "932 160 306");
        User newUser4 = new User("Marta", "Gross", "1980-07-30", "Paseo de Gracia, 92, 08008 Barcelona", "Martha.L@hotmail.com", "932 14 25 76");
        users.addUser(newUser1);
        users.addUser(newUser2);
        users.addUser(newUser3);
        users.addUser(newUser4);
    }


    public static UserMap getFakeUsers() {
        return users;
    }
}
