package com.example.demo.controller;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.model.item;
import com.example.demo.repository.itemRepository;
import com.example.demo.service.ItemService;
import jdk.jshell.spi.ExecutionControlProvider;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class itemController {

    @Autowired
    private itemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<?> getAllitems(){
        List<item> itemList = itemService.getAllitems();
       // System.out.println("====================="+itemList.size()+"================================");
//        if(itemList.size()>0) {
//            return new ResponseEntity<List<item>>(itemList, HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("No items available", HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(itemList, itemList.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@RequestBody item Item){
    try{
//        Item.setCreated(LocalDateTime.now());
//        itemRepository.save(Item);
        itemService.createItem(Item);
        return new ResponseEntity<item>(Item,HttpStatus.OK);
    } catch (Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getSingleItem(@PathVariable("id") String id){
//        Optional<item> itemOptional = itemRepository.findById(id);
//        if(itemOptional.isPresent()){
//            return new ResponseEntity<>(itemOptional.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("Item not found with id "+id, HttpStatus.NOT_FOUND);
//        }
        try {
           return new ResponseEntity<>(itemService.getSingleItem(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody item Item){
//        Optional<item> itemOptional = itemRepository.findById(id);
//        if(itemOptional.isPresent()){
//            item ItemToSave = itemOptional.get();
//            ItemToSave.setName(Item.getName() != null ? Item.getName() : ItemToSave.getName());
//            ItemToSave.setDescription(Item.getDescription() != null ? Item.getDescription() : ItemToSave.getDescription());
//            ItemToSave.setOwnerId(Item.getOwnerId() != null ? Item.getOwnerId() : ItemToSave.getOwnerId());
//            ItemToSave.setUpdated(LocalDateTime.now());
//            itemRepository.save(ItemToSave);
//            return new ResponseEntity<>(ItemToSave, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("Item not found with id "+id, HttpStatus.NOT_FOUND);
//        }


        try {
            itemService.updateById(id, Item);
            return new ResponseEntity<>("Update item with id "+id, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (ItemCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
           // itemRepository.deleteById(id);
            itemService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted with id "+id, HttpStatus.OK);

        }
        catch (ItemCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
