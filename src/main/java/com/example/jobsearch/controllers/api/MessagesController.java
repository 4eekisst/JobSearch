//package com.example.jobsearch.controllers.api;
//
//import com.example.jobsearch.dto.MessageDto;
//import com.example.jobsearch.services.MessagesService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController("restMessages")
//@RequiredArgsConstructor
//@RequestMapping("api/messages")
//public class MessagesController {
//    private final MessagesService service;
//
//    @GetMapping("/getMessages/{id}")
//    public ResponseEntity<List<MessageDto>> messages(@PathVariable Long id) {
//        return ResponseEntity.ok(service.getMessages(id));
//    }
//
//    @GetMapping("/getNewMessage/{id}/{respId}")
//    public ResponseEntity<MessageDto> getNewMessage(@PathVariable Long id, @PathVariable Long respId) {
//        return ResponseEntity.ok((MessageDto) service.getNewMessagesBylastMessage(id, respId));
//    }
//
//
//
//    @PostMapping("/sendMessage/{recipientEmail}")
//    public ResponseEntity<Long> sendMessage(@RequestParam String message, @PathVariable String recipientEmail, Authentication authentication) {
//        MessageDto messageDto = MessageDto.builder()
//                .content(message)
//                .senderEmail(authentication.getName())
//                .recipientEmail(recipientEmail)
//                .build();
//        service.addMessage(messageDto, authentication);
//        return ResponseEntity.ok(messageDto.getId());
//    }
//}
