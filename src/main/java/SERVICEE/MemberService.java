/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICEE;

//import com.ituhn.pemkom2.gui.Adminae;
import GUII.admin;
import DAOO.GenericDAO;
//import com.ituhn.pemkom2.objects.Karyawan;
//import com.mycompany.gymlife1.object.Member;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import object.Member;
import org.bson.conversions.Bson;

/**
 *
 * @author mnish
 */
public class MemberService {

    // Inisialisasi GenericDAO khusus untuk entitas Karyawan
    // Menggunakan koleksi "karyawan" dan referensi Class Karyawan [3]
    private final GenericDAO<Member> DAO;
    private JLabel lblIDM;
    private JLabel lblPkt;

    public MemberService() {
        this.DAO = new GenericDAO<>("member", Member.class);
    }

    /**
     * 1.CREATE: Fungsi untuk menyimpan data karyawan baru ke MongoDB [2], [3]
     *
     * @param memberBaru
     */
    public void tambahMember(Member memberBaru) {
        DAO.save(memberBaru); // Memanggil insertOne melalui GenericDAO [3]
    }

    public void tambahMember(String uidRfid, String namamember, String idmember, String paket) {
        Member memberBaru = new Member(uidRfid, namamember, idmember, paket);
        DAO.save(memberBaru); // Memanggil insertOne melalui GenericDAO [3]
    }

    /**
     * 2. READ (All): Fungsi untuk mengambil semua data member [5], [6]
     */
    public void tampilkanDaftarMember() {
        List<Member> daftar = DAO.findAll();
        System.out.println("--- Daftar Member GYM ---");
        for (Member k : daftar) {
            System.out.println(k.toString()); // Menggunakan format toString di sumber [7]
        }
    }

    /**
     * 2.READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     *
     * @param panelTarget
     * @param key
     */
    public void tampilMember(JPanel panelTarget, String key) {
        //1. 
        // Menampilkan data berdasarkan request
        // key "null/kosong" = get all data
        // key "filled" = get specific data

        List<Member> daftarMember;
        if (key.isEmpty()) {
            //Mengambil data dari database menggunakan GenericDAO
            daftarMember = DAO.findAll();
        } else {
            //Mengambil data dari database menggunakan GenericDAO
            //berdasarkan kata kunci yang diketik
            daftarMember = cariMember(key);
        }
        // 2. Membersihkan panel target utama sebelum memuat data baru
        panelTarget.removeAll();

        // Mengubah layout panel target menjadi BorderLayout
        panelTarget.setLayout(new BorderLayout());
        // Mengatur warna background utama menjadi biru
        panelTarget.setBackground(new Color(68, 114, 196));

        // Membuat panel grid khusus untuk menampung kotak/card
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        gridPanel.setOpaque(false); // Transparan agar warna biru panelTarget terlihat
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Memberi jarak dari tepi layar

        // 3. Iterasi data dan menambahkannya ke panel grid
        try {
            for (Member k : daftarMember) {
                // Membuat panel 'Card' (box orange) untuk 1 karyawan
                // Layout 4 baris 1 kolom agar kolor berisi Nama,ID, Departemen, panel control 
                JPanel cardPanel = new JPanel(new GridLayout(4, 1, 0, 0));
                cardPanel.setBackground(new Color(237, 125, 49)); // Warna background orange

                // Memberikan garis tepi tipis membulat (rounded) dan padding/jarak ke dalam
                cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.MAGENTA, 1, true),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));

                // Membuat Label Nama & Set warna teks jadi Putih
                JLabel lblNama = new JLabel("Nama: " + k.getnamamember());
                lblNama.setForeground(Color.WHITE);

                // Membuat Label ID Karyawan & Set warna teks jadi Putih
                JLabel lblIDK = new JLabel("ID Karyawan: " + k.getidmember());
                lblIDK.setForeground(Color.WHITE);

                // Membuat Label Departemen & Set warna teks jadi Putih
                JLabel lblDept = new JLabel("Paket: " + k.getpaket());
                lblDept.setForeground(Color.WHITE);

                // Membuat panel kontrol 1 baris 2 kolom, berisi tombol edit dan hapus
                JPanel controlPanel = new JPanel(new GridLayout(1, 2, 20, 15));
                controlPanel.setBackground(new Color(237, 125, 49));

                JButton tombolEdit = new JButton("Edit");
                tombolEdit.setBackground(Color.ORANGE);
                tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tombolEdit.addActionListener((ActionEvent e) -> {
                admin.txtUID.setText(k.getuidrfid());
                admin.txtKRID.setText(k.getidmember());
                admin.txtKRName.setText(k.getnamamember());
                admin.txtKRDept.setSelectedItem(k.getpaket());
                admin.btnUpdate.setEnabled(true);
                admin.btnSave.setEnabled(false);
                admin.showData("");
                });
                JButton tombolDelete = new JButton("Delete");
                tombolDelete.setBackground(Color.RED);
                tombolDelete.setForeground(Color.WHITE);
                tombolDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tombolDelete.addActionListener((ActionEvent e) -> {
                    Object[] options = {"Ya, Hapus", "Batal"};
                    int choice = JOptionPane.showOptionDialog(
                            null, // Parent component
                            "Apakah Anda ingin menyimpan data "+k.getnamamember()+"?", // Message
                            "Konfirmasi Pengelolaan", // Title
                            JOptionPane.YES_NO_OPTION, // Option type
                            JOptionPane.QUESTION_MESSAGE, // Message type
                            null, // Custom icon (null uses default)
                            options, // The array of custom button text
                            options[0] // Default button focused
                    );

                    switch (choice) {
                        case JOptionPane.YES_OPTION -> hapusMember(k.getidmember());
                        case JOptionPane.NO_OPTION -> System.out.println("User memilih: Batal");
                        default -> {
                        }
                    }
                });

                controlPanel.add(tombolEdit);
                controlPanel.add(tombolDelete);

                // Memasukkan label ke dalam cardPanel (box orange)
                lblIDM = new JLabel("ID Member: " + k.getidmember());
                lblIDM.setForeground(Color.WHITE);

                lblPkt = new JLabel("Paket: " + k.getpaket());
                lblPkt.setForeground(Color.WHITE);

                cardPanel.add(lblNama);
                cardPanel.add(lblIDM);
                cardPanel.add(lblPkt);
                cardPanel.add(controlPanel);

                // Memasukkan cardPanel utuh ke dalam gridPanel
                gridPanel.add(cardPanel);
            }

            // Memasukkan gridPanel ke bagian ATAS (NORTH) dari panel target.
            panelTarget.add(gridPanel, BorderLayout.NORTH);

            // 4. Me-refresh panel agar perubahan muncul di GUI
            panelTarget.revalidate();
            panelTarget.repaint();
        } catch (Exception e) {
        }
    }

    /**
     * 3.READ (One): Mencari satu karyawan spesifik berdasarkan UID RFID [5],
     * [6] Sangat krusial untuk alur Tap Kartu pada Pertemuan 14 [8].
     *
     * @param key
     * @return
     */
    public List<Member> cariMember(String key) {
        List<Bson> filters = new ArrayList<>();
        // Get all fields from the Karyawan class
        for (Field field : Member.class.getDeclaredFields()) {
            // Skip the uidRfid field and non-string fields if necessary
            if (field.getName().equals("uidRfid")) {
                continue;
            }
            filters.add(Filters.regex(field.getName(), key, "i"));
        }
        // Search and return Karyawan objects directly
        List<Member> results = DAO.findMany(Filters.or(filters));
        return results;
    }

    /**
     * 4.UPDATE: Memperbarui data karyawan menggunakan filter Bson [5], [6]
     *
     * @param newK
     */
    public void updateMember(Member newK) {
        Bson filter = Filters.eq("idmember", newK.getidmember());
        Member k = DAO.findOne(filter);
        if (k != null) {
            DAO.update(filter, newK);
            admin.showData("");
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
        }
    }

    /**
     * 5.DELETE: Menghapus data karyawan dari database [5], [6]
     *
     * @param idM
     */
    public void hapusMember(String idM) {
        Bson filter = Filters.eq("idmember", idM);
        DAO.delete(filter); // Menggunakan deleteOne [6]
        admin.showData("");
        JOptionPane.showMessageDialog(null, "Data karyawan berhasil dihapus.");
    }

}
