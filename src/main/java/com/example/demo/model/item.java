package com.example.demo.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "item")
public class item {
    @Id
    private String id;
    @NotNull(message = "name can not be null")
    private String name;
    @NotNull(message = "Description can not be null")
    private String description;
    private String ownerId;
    private List<Pair<Integer, String>> biddingList= new ArrayList<Pair<Integer, String>>();
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean AuctionFlag;

}
@Data
@AllArgsConstructor
public class Pair<F, S> {
    public Integer first;
    public String second;

    public Integer getValue() {
        return this.first;
    }
}
