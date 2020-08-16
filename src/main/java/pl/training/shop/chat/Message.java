package pl.training.shop.chat;

import lombok.Data;

import java.time.Instant;

@Data
public class Message {
 
    private String from;
    private String text;

}