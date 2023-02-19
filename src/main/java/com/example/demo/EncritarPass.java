/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Fanta
 */
public class EncritarPass {
 
    public static void main(String[] orgs){
        
        
        
        var password="1234";
        System.out.println(password);
        System.out.println(encritarPass(password));
        
        
    }
    public static String encritarPass(String pass){
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        
        return encoder.encode(pass);
    }
    
}
