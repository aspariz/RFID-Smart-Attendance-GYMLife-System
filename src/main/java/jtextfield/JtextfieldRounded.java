package jtextfield; // Pastikan nama package ini sesuai dengan folder di project kamu

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalTextFieldUI;

public class JtextfieldRounded extends JTextField {

    private final RenderingHints hints;
    private Color focusColor = Color.BLUE; // Warna saat dipilih
    private Color borderColor = Color.GRAY; // Warna border normal
    private int round = 15; // Tingkat kelengkungan

    public JtextfieldRounded() {
        // Mengatur agar background transparan supaya lengkungan terlihat
        setOpaque(false);
        // Memberi jarak teks agar tidak menempel ke pinggir border
        setBorder(new EmptyBorder(5, 10, 5, 10));
        
        // Pengaturan agar gambar border halus (Anti-aliasing)
        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Listener untuk mengubah warna border saat fokus
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                borderColor = focusColor;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                borderColor = Color.GRAY;
                repaint();
            }
        });

        // Mengatur UI ke Metal agar custom painting berjalan lancar
        setUI(new MetalTextFieldUI());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHints(hints);
        
        // Menggambar background rounded
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
        
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHints(hints);
        
        // Menggambar garis tepi (border) rounded
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, round, round);
        
        g2.dispose();
    }

    // Getter dan Setter jika ingin mengubah warna secara dinamis
    public void setFocusColor(Color focusColor) {
        this.focusColor = focusColor;
    }

    public void setRound(int round) {
        this.round = round;
        repaint();
    }
}