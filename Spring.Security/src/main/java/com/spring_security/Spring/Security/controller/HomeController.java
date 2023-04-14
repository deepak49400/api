package com.spring_security.Spring.Security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/normal")
    @PreAuthorize("hasRole('NORMAL')")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("yes, I am normal User");
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminUser(){
        return ResponseEntity.ok("yes, I am admin User");
    }@GetMapping("/public")
    public ResponseEntity<String> publicUser(){
        return ResponseEntity.ok("yes, I am public User");
    }

}
