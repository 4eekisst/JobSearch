package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.MessageDto;
import com.example.jobsearch.services.AuthAdapter;
import com.example.jobsearch.services.MessagesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restMessages")
@RequiredArgsConstructor
@RequestMapping("api/message")
public class ChatMessageController {
    private final MessagesService messagesService;
    private final AuthAdapter adapter;


    @GetMapping("/{respondedId}")
    public ResponseEntity<List<MessageDto>> getNewMessage(@RequestParam Long lastMessageId, @PathVariable Long respondedId) {
        return ResponseEntity.ok(messagesService.getNewMessagesBylastMessage(lastMessageId, respondedId));
    }

    @PostMapping
    public HttpStatus sendMessage(@Valid @RequestBody MessageDto messageDto, Authentication authentication) {
        messagesService.addMessage(messageDto, authentication);
        return HttpStatus.OK;
    }
}
