package com.example.demo.model;

import ch.qos.logback.core.rolling.helper.SizeAndTimeBasedArchiveRemover;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Document(collection = "users")
public class user {
    @Id
    private String id;
    @NotNull(message = "First Name can not be null")
    private String firstName;
    private String lastName;
    @NotNull(message = "Email can not be null")
    private String email;
    private List<item> itemList;

    private LocalDateTime created;
    private LocalDateTime updated;
}
