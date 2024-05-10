package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.models.ChatMessage;
import com.example.jobsearch.services.MessagesService;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final UserService service;
    private final MessagesService messageService;

    @GetMapping()
    public String chatPage(
            @RequestParam String email,
            Authentication authentication,
            Model model) {
        UserDto sender = service.getUserByEmail(authentication.getName());
        UserDto recipient = service.getUserByEmail(email);

        model.addAttribute("sender", sender);
        model.addAttribute("recipient", recipient);

        return "chat";
    }
}