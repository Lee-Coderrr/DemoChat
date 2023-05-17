package com.example.demochat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demochat.dto.MessageDTO;
import com.example.demochat.dto.UserDTO;
import com.example.demochat.service.ChatService;
import com.example.demochat.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChatService chatService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/connect")
    /* 접속 화면 */
    public String index() {
        return "chating/connect";
    }

    @PostMapping("/chat/login")
    public String login(UserDTO userDto, @RequestParam("name") String name, HttpSession session) {
        UserDTO savedUserDto = loginService.saveUserDto(userDto);
        session.setAttribute("name", name);
        session.setAttribute("id", savedUserDto.getId());
        log.info(name);
        log.info("saved: " + savedUserDto);
        return "redirect:/chating";
    }
    /* 채팅 화면 */

    @GetMapping("/chating")
    public String chat(Model model, HttpSession session) {

        if (session.getAttribute("name") == null) {
            return "redirect:/connect";
        }

        // 서비스로부터 데이터 불러오기
        Iterable<MessageDTO> msg = chatService.getAllMessages();

        String name = (String) session.getAttribute("name");
        Long id = (Long) session.getAttribute("id");

        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("msg", msg);

        log.info("이름: " + name + ", id: " + id);

        return "chating/chat";
    }

    @MessageMapping("/chat")
    public void chat(MessageDTO messageDTO) {

        log.info(messageDTO.toString());

        MessageDTO savedMessage = chatService.chatCreate(messageDTO); // 메시지 저장

        log.info(savedMessage.toString());

        // "/topic/messages" 주소를 구독하는 클라이언트에게 메시지를 전송합니다.
        template.convertAndSend("/topic/messages", savedMessage);
    }

}
