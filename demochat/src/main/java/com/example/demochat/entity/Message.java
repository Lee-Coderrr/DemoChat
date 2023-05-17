package com.example.demochat.entity;

import com.example.demochat.dto.MessageDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long userId;
    private String username;
    private String timestamp;

    public static Message createMessage(MessageDTO msgDto) {
        return new Message(
                msgDto.getId(),
                msgDto.getText(),
                msgDto.getUserId(),
                msgDto.getUsername(),
                msgDto.getTimestamp());
    }
}
