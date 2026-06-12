/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICEE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JLabel;
/**
 *
 * @author bsame
 */
public class DigitalClockService {
    
    private final JLabel targetLabel;
    private final String pattern;
    

    public DigitalClockService(java.lang.String pattern, javax.swing.JLabel targetLabel) {
        this.pattern = pattern;
        this.targetLabel = targetLabel;
    }

    public DigitalClockService(JLabel lblJam, String pattern) {
    this.pattern = pattern;
    this.targetLabel = lblJam;
}
    
    
    public Thread getThread(){
        Runnable clockTask = () -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.of("id", "ID"));
            try {
                while (!Thread.currentThread().isInterrupted()){
                    LocalDateTime now = LocalDateTime.now();
                    String timeFormatted = now.format(formatter);
                    targetLabel.setText(timeFormatted);
                    
                    Thread.sleep(1000);
                }
            } catch (InterruptedException  e) {
                System.out.println(Thread.currentThread().getName() + " dihentikan. ");
            }
        };
        return new Thread(clockTask);
    }
    }