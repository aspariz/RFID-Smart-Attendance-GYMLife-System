/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICEE;

import object.User;
import DAOO.GenericDAO;
import GUII.admin;
import GUII.LoginPage;
import UTILY.SecurityUtils;
import com.mongodb.client.model.Filters;
import java.awt.Frame;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
/**
 *
 * @author bsame
 */
public class AuthService {
    private final GenericDAO<User> UserDAO = new GenericDAO<>("users", User.class);
    
    public void login(String username, String plainPassword, LoginPage loginPage) {
        String hashedInput = SecurityUtils.getHash(plainPassword, SecurityUtils.SHA_256);
        
        User user = UserDAO.findOne(Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", hashedInput)
        ));
        
        if (user != null) {
            user.setLastLogin(LocalDateTime.now());
            UserDAO.update(Filters.eq("username", username), user);
            
            JOptionPane.showMessageDialog(null, "Selamat Datang, " + user.getFullname());
            admin admPage = new admin();
            admPage.setLocationRelativeTo(null);
            admPage.setVisible(true);
            admPage.setExtendedState(Frame.MAXIMIZED_BOTH);
            loginPage.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "username atau password salah", 
                    "Login gagal!", JOptionPane.ERROR_MESSAGE);
        }
    }
       public void registerUser(String fullname, String username, String plainPassword) {
            String hashedPassword = SecurityUtils.getHash(plainPassword, SecurityUtils.SHA_256);
            
            User newUser = new User(fullname, username, hashedPassword, null);
            try {
               UserDAO.save(newUser);
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "gagal mendaftarkan user:" + e.getMessage());
           }
        }    
}
