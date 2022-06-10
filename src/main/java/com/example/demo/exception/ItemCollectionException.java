package com.example.demo.exception;

public class ItemCollectionException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ItemCollectionException(String message){
        super(message);
    }
    public static String NotFoundException(String id){
        return "Item with "+id+" not found!";
    }

    public static String ItemAlreadyExists(){
        return "Item with given name already exists.";
    }
    public static String OwnerExists(String id){
        return "Item with id"+id+" already has an owner";
    }
}
