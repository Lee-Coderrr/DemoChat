package com.example.demochat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demochat.dto.MessageDTO;
import com.example.demochat.dto.UserDTO;
import com.example.demochat.entity.Message;
import com.example.demochat.entity.User;
import com.example.demochat.repository.MessageRepository;
import com.example.demochat.repository.UserRepository;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    /* User */
    public UserDTO saveUser(UserDTO userDto) {
        User savedUser = userRepository.save(userDto.toEntity());
        return new UserDTO(savedUser.getId(), savedUser.getName());
    }

    /* Chat */
    public Iterable<MessageDTO> getAllMessages() {
        Iterable<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message message : messages) {
            messageDTOs.add(new MessageDTO(message.getId(), message.getText(), message.getUserId(),
                    message.getUsername(), message.getTimestamp()));
        }
        return messageDTOs;
    }

    public User findByUser(Long id) {

        return userRepository.findById(id).get();
    }

    public MessageDTO chatCreate(MessageDTO messageDTO) {
        Message message = messageDTO.toEntity();
        Message savedMessage = messageRepository.save(message);
        return new MessageDTO(savedMessage.getId(), savedMessage.getText(), savedMessage.getUserId(),
                savedMessage.getUsername(), savedMessage.getTimestamp());
    }

}
