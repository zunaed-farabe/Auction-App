package com.example.demo.controller;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.exception.userCollectionException;
import com.example.demo.model.item;
import com.example.demo.model.user;
import com.example.demo.repository.itemRepository;
import com.example.demo.repository.userRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
public class userController {

    @Autowired
    private itemRepository itemRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private userService userService;
    @Autowired
    private ItemService itemService;


    @GetMapping("/users")
    public ResponseEntity<?> getAllusers(){
        List<user> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, userList.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createItem(@RequestBody user User){
        try{
//        Item.setCreated(LocalDateTime.now());
//        itemRepository.save(Item);
            userService.createUser(User);
            return new ResponseEntity<user>(User,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){

        try {
            return new ResponseEntity<>( userService.getUserById(id), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
//
//    @GetMapping("/users/{email}")
//    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
//
//        try {
//            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/users/{email}")
    public ResponseEntity<?> updateByEmail(@PathVariable("email") String email, @RequestBody user User){
        try {
            userService.updateByEmail(email, User);
            return new ResponseEntity<>("Update user with email "+email, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (userCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
            // itemRepository.deleteById(id);
            userService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted user with id "+id, HttpStatus.OK);

        }
        catch (userCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping("/users/{email}")
//    public ResponseEntity<?> deleteByEmail(@PathVariable("email") String email){
//        try {
//            // itemRepository.deleteById(id);
//            userService.deleteByEmail(email);
//            return new ResponseEntity<>("Successfully deleted user with email "+email, HttpStatus.OK);
//
//        }
//        catch (userCollectionException e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }


    @PutMapping("/users/additive/{id}")
    public ResponseEntity<?> addItem(@PathVariable("id") String id, @RequestBody item Item) {
        try {
            Optional<user> usertoadd = userRepository.findById(id);

            if(usertoadd.isPresent()) {
                System.out.println("=========================bfore=============");
                user User = usertoadd.get();
                System.out.println(User.getId());
                userService.addItem(Item, User);
                System.out.println("=========================after=============");
                return new ResponseEntity<>("Successfully added item to the user", HttpStatus.OK);
            }else{
               throw new userCollectionException(userCollectionException.userNotFoundException(id));
            }
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ItemCollectionException e) {
            throw new RuntimeException(e);
        } catch (userCollectionException e) {
            throw new RuntimeException(e);
        }
    }
}
