package com.example.demo.service;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.exception.userCollectionException;
import com.example.demo.model.item;
import com.example.demo.model.user;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface userService {

    public void createUser(user User) throws userCollectionException, ConstraintViolationException;
    public List<user> getAllUsers();
    public user getUserById(String id) throws userCollectionException;
    public user getUserByEmail(String email) throws userCollectionException;
    public void updateByEmail(String email, user User) throws userCollectionException;
    public void deleteById(String id) throws userCollectionException;
    public void deleteByEmail(String email) throws userCollectionException;

    public void addItem(item Item, user User) throws ItemCollectionException, ConstraintViolationException, userCollectionException;
}
