package com.example.demo.exception;

public class userCollectionException extends Exception{


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public userCollectionException(String message){
        super(message);
    }
    public static String userNotFoundException(String id){
        return "User with "+id+" not found!";
    }

    public static String userAlreadyExistsException(String email){
        return "User with given email"+email+" already exists.";
    }
//    public static String userNotFoundException(String email){
//        return "User with given email"+email+" not found.";
//    }

}
