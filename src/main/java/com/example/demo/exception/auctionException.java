package com.example.demo.exception;

public class auctionException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public auctionException(String message){
        super(message);
    }
    public static String auctionNorFoundException(String id){
        return "auction with "+id+" not found!";
    }

}
