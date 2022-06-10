package com.example.demo.service;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.exception.userCollectionException;
import com.example.demo.model.item;
import com.example.demo.model.user;
import com.example.demo.repository.itemRepository;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;
    @Autowired
    private itemRepository itemRepository;


    @Override
    public void createUser(user User) throws userCollectionException, ConstraintViolationException {
        Optional<user> userOptional = userRepository.findByEmail(User.getEmail());
        if(userOptional.isPresent()){
            throw new userCollectionException(userCollectionException.userAlreadyExistsException(User.getEmail()));
        }else {
            User.setCreated(LocalDateTime.now());
            userRepository.save(User);

        }
    }

    @Override
    public List<user> getAllUsers() {
        List<user>  userList = userRepository.findAll();
        if(userList.size()>0){
            return userList;
        }else{
            return new ArrayList<user>();
        }
    }

    @Override
    public user getUserById(String id) throws userCollectionException {
        Optional<user> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new userCollectionException(userCollectionException.userNotFoundException(id));
        }else {
            return userOptional.get();
        }
    }

    @Override
    public user getUserByEmail(String email) throws userCollectionException {
        Optional<user> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()){
            throw new userCollectionException(userCollectionException.userNotFoundException(email));
        }else {
            return userOptional.get();
        }
    }

    @Override
    public void updateByEmail(String email, user User) throws userCollectionException {
        Optional<user> userOptional = userRepository.findByEmail(email);
       // Optional<user> userWithSameOwner = itemRepository.findByOwnerId(itemOptional.get().getOwnerId());
        if(userOptional.isPresent()){
//
//            if(itemWithSameOwner.isPresent() && itemWithSameOwner.get().getOwnerId().equals(Item.getOwnerId())){
//                throw new ItemCollectionException(ItemCollectionException.OwnerExists(id));
//            }

            user userToUpdate = userOptional.get();

            userToUpdate.setFirstName(User.getFirstName());
            userToUpdate.setLastName(User.getLastName());
           // userToUpdate.setEmail(User.getEmail());
            userToUpdate.setUpdated(LocalDateTime.now());
            userRepository.save(userToUpdate);
        }else{
            throw new userCollectionException(userCollectionException.userNotFoundException(email));
        }
    }

    @Override
    public void deleteById(String id) throws userCollectionException {
        Optional<user> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new userCollectionException(userCollectionException.userNotFoundException(id));
        }else {
            userRepository.deleteById(id);
        }
    }
    public void deleteByEmail(String email) throws userCollectionException{
        Optional<user> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()){
            throw new userCollectionException(userCollectionException.userNotFoundException(email));
        }else {
            userRepository.deleteById(userOptional.get().getId());
        }
    }

    @Override
    public void addItem(item Item, user User) throws userCollectionException, ItemCollectionException, ConstraintViolationException {
      //  Optional<user> userOptional = userRepository.findByEmail(User.getEmail());
        Optional<item> itemOptional = itemRepository.findById(Item.getId());

        if(itemOptional.isPresent()){
            User.setItemList(new ArrayList<>());

            User.getItemList().add(Item);
            Item.setOwnerId(User.getId());
            userRepository.save(User);
            itemRepository.save(Item);
        }
        else{
            throw new ItemCollectionException(ItemCollectionException.NotFoundException(Item.getId()));
        }
    }
}
