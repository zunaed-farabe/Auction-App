package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auctions")
public class auction {
    @Id
    private String id;
    private List<item> auctionItemList;
    private List<history> auctionHistory;
}
@Data
public class history{
    private String sellerId;
    private String buyerId;
    private item Item;
    public history(item Item){
        this.buyerId = Item.getBiddingList().get(Item.getBiddingList().size()-1);
        this.sellerId = Item.getOwnerId();
        this.Item = Item;
    }
}