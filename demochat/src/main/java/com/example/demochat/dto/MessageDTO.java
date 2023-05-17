package com.example.demochat.dto;

import com.example.demochat.entity.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private String text;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("username")
    private String username;
    private String timestamp;

    public static MessageDTO createDto(Message msg) {
        return new MessageDTO(
                msg.getId(),
                msg.getText(),
                msg.getUserId(),
                msg.getUsername(),
                msg.getTimestamp());
    }

    public Message toEntity() {
        return new Message(
                this.id,
                this.text,
                this.userId,
                this.username,
                this.timestamp);
    }
}
