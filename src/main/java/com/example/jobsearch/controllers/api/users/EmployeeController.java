//package com.example.jobsearch.controllers.api.users;
//
//import com.example.jobsearch.services.UserService;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
//import com.example.jobsearch.dto.users.UserDto;
//import com.example.jobsearch.enums.AccountTypes;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController("restEmployee")
//@RequestMapping("api/employee")
//@RequiredArgsConstructor
//public class EmployeeController {
//    private final UserService service;
//
//    @GetMapping()
//    public ResponseEntity<List<UserDto>> getEmployees(Authentication auto) {
//        return ResponseEntity.ok(service.getUserByType(AccountTypes.APPLICANT));
//    }
//
//    @GetMapping("/{name}")
//    public ResponseEntity<List<UserDto>> getEmployeesByName(@Valid @PathVariable(name = "name") String name) {
//        return ResponseEntity.ok(service.getUserByName(name.strip()));
//    }
//
//    @GetMapping( "/phone/{phone}")
//    public ResponseEntity<UserDto> getEmployeeByPhoneNum(@Valid @PathVariable(name = "phone") String phone) {
//        UserDto user = service.getUserByPhone(phone.strip());
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/email")
//    public ResponseEntity<UserDto> getEmployeeByEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
//        UserDto user = service.getUserByEmail(email);
//        return ResponseEntity.ok(user);
//    }
//}
