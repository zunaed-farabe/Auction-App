package com.example.demo.controller;

import com.example.demo.repository.auctionRepository;
import com.example.demo.repository.itemRepository;
import com.example.demo.repository.userRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.auctionService;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class auctionController {
    @Autowired
    private com.example.demo.repository.itemRepository itemRepository;
    @Autowired
    private com.example.demo.repository.userRepository userRepository;
    @Autowired
    private com.example.demo.service.userService userService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private auctionRepository auctionRepository;
    @Autowired
    private auctionService auctionService;

}
