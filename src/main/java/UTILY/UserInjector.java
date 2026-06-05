/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILY;

import SERVICEE.AuthService;
/**
 *
 * @author bsame
 */
public class UserInjector {
    public static void main(String[] args){
        AuthService userService = new AuthService();
        userService.registerUser("admin kicau", "kicau", "123");
    }
    
}
