/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILY;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author bsame
 */
public class SecurityUtils {
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";
    
    
    public static String getHash(String input, String algorithm) {
        try {
          MessageDigest md = MessageDigest.getInstance(algorithm);
          
          byte[] hashBytes = md.digest(input.getBytes());
          
          StringBuilder hexString = new StringBuilder();
          for (byte b: hashBytes) {
              String hex = Integer.toHexString(0xff & b);
              if (hex.length() == 1) {
                  hexString.append('0');
              }
              hexString.append(hex);
          }
          return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("kesalahan: algoritma" + algorithm + "tidak mendukung." + e.getMessage());
            return null;
        }
        }
}
        
