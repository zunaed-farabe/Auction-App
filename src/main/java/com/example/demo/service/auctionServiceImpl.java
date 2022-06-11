package com.example.demo.service;

import com.example.demo.exception.auctionException;
import com.example.demo.model.auction;
import com.example.demo.model.history;
import com.example.demo.model.item;
import com.example.demo.model.user;
import com.example.demo.repository.auctionRepository;
import com.example.demo.repository.itemRepository;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class auctionServiceImpl implements auctionService{

    private auctionRepository auctionRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private itemRepository itemRepository;
    @Autowired
    private userService userService;
    @Autowired
    private ItemService itemService;

    @Override
    public void StartAuctoin() {
        List<item> itemList = itemService.getAllitems();
        auction auction = new auction();
        for (item Item: itemList  ) {
            Item.setAuctionFlag(true);
            auction.getAuctionItemList().add( Item );
        }
        auctionRepository.save(auction);
    }

    @Override
    public List<item> StopAuction(String id) throws auctionException{
        Optional<auction> auctionOptional = auctionRepository.findById(id);
        if(auctionOptional.isPresent()){
            auction auctionToSave = auctionOptional.get();
            for (item Item:    auctionToSave.getAuctionItemList() ) {
                Item.setAuctionFlag(false);
                auctionToSave.getAuctionHistory().add(new history(Item));
            }
            auctionRepository.save(auctionToSave);
            return auctionToSave.getAuctionItemList();
        }else {
            throw new auctionException(auctionException.auctionNorFoundException(id));
        }

    }

    @Override
    public List<history> AucionHistory(String id) throws auctionException{
        Optional<auction> auctionOptional = auctionRepository.findById(id);
        if(auctionOptional.isPresent())
            return auctionOptional.get().getAuctionHistory();
        else
            throw new auctionException(auctionException.auctionNorFoundException(id));
    }
}
