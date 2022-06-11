package com.example.demo.service;


import com.example.demo.exception.auctionException;
import com.example.demo.model.item;

import java.util.List;

public interface auctionService {

    public void StartAuctoin();
    public List<item> StopAuction(String id) throws auctionException;
    public List<> AucionHistory(String id)throws auctionException;
}
