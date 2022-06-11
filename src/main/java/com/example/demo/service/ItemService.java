package com.example.demo.service;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.model.item;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface ItemService {

    public void createItem(item Item) throws ItemCollectionException, ConstraintViolationException;
    public List<item> getAllitems();
    public item getSingleItem(String id) throws ItemCollectionException;
    public void updateById(String id, item Item) throws ItemCollectionException;
    public void deleteById(String id) throws ItemCollectionException;
    public void updateBiddingList(String id, item Item, Integer price) throws ItemCollectionException;
}
