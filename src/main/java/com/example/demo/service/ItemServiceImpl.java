package com.example.demo.service;

import com.example.demo.exception.ItemCollectionException;
import com.example.demo.model.Pair;
import com.example.demo.model.item;
import com.example.demo.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private itemRepository itemRepository;

    @Override
    public void createItem(item Item) throws ItemCollectionException, ConstraintViolationException {
       Optional<item> itemOptional = itemRepository.findByName(Item.getName());
       if(itemOptional.isPresent()){
           throw new ItemCollectionException(ItemCollectionException.ItemAlreadyExists());
       }else {
           Item.setCreated(LocalDateTime.now());
           itemRepository.save(Item);

       }
    }

    @Override
    public List<item> getAllitems() {
        List<item>  itemList = itemRepository.findAll();
        if(itemList.size()>0){
            return itemList;
        }else{
            return new ArrayList<item>();
        }
    }

    @Override
    public item getSingleItem(String id) throws ItemCollectionException {

       Optional<item> itemOptional = itemRepository.findById(id);

       if(!itemOptional.isPresent()){
           throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
       }else {
           return itemOptional.get();
       }
    }

    @Override
    public void updateById(String id, item Item) throws ItemCollectionException {
        Optional<item> itemOptional = itemRepository.findById(id);
        Optional<item> itemWithSameOwner = itemRepository.findByOwnerId(itemOptional.get().getOwnerId());
        if(itemOptional.isPresent()){

            if(itemWithSameOwner.isPresent() && itemWithSameOwner.get().getOwnerId().equals(Item.getOwnerId())){
                throw new ItemCollectionException(ItemCollectionException.OwnerExists(id));
            }

            item itemToUpdate = itemOptional.get();

            itemToUpdate.setName(Item.getName());
            itemToUpdate.setDescription(Item.getDescription());
            itemToUpdate.setOwnerId(Item.getOwnerId());
            itemToUpdate.setCreated(LocalDateTime.now());
            itemRepository.save(itemToUpdate);
        }else{
            throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteById(String id) throws ItemCollectionException {
        Optional<item> itemOptional = itemRepository.findById(id);

        if(!itemOptional.isPresent()){
            throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
        }else {
            itemRepository.deleteById(id);
        }
    }

    @Override
    public void updateBiddingList(String id, item Item, Integer price) throws ItemCollectionException {
        Optional<item> itemOptional = itemRepository.findById(id);

        if(itemOptional.isPresent()){


            item itemToUpdate = itemOptional.get();

            itemToUpdate.getBiddingList().add(new Pair<>(price, id));

            itemRepository.save(itemToUpdate);
        }else{
            throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
        }
    }


}
