package UTILY;

import com.mongodb.client.MongoDatabase; // Interface untuk merepresentasikan database MongoDB
import org.bson.Document;                // Class untuk membuat dokumen BSON (format data MongoDB)

public class TesKoneksi {

    public static void main(String[] args) {

        try {
            // Memberitahu user bahwa proses koneksi sedang dimulai
            System.out.println("Sedang mencoba menghubungkan ke database...");

            // Memanggil MongoManager untuk mendapatkan instance database "gymlife_db"
            // MongoManager akan membuat koneksi baru jika belum ada (Singleton Pattern)
            MongoDatabase database = MongoManager.getDatabase();

            // Membuat dokumen perintah "ping" dengan nilai 1
            // "ping" adalah perintah standar MongoDB untuk mengecek apakah server aktif/merespons
            Document ping = new Document("ping", 1);

            // Mengirim perintah ping ke server MongoDB
            // Jika server tidak aktif atau koneksi gagal, baris ini akan melempar Exception
            database.runCommand(ping);

            // Jika sampai sini tanpa error, berarti koneksi berhasil
            System.out.println("=========================================");
            System.out.println("STATUS: KONEKSI BERHASIL!");

            // Menampilkan nama database yang berhasil terhubung
            System.out.println("Terhubung ke Database: " + database.getName());
            System.out.println("=========================================");

            // Menampilkan semua nama koleksi (setara "tabel") yang ada di database
            System.out.println("Daftar Koleksi di " + database.getName() + ":");

            // Iterasi satu per satu nama koleksi dan tampilkan ke konsol
            for (String name : database.listCollectionNames()) {
                System.out.println("- " + name);
            }

        } catch (Exception e) {

            // Jika terjadi error (MongoDB mati, salah URL, dll), masuk ke blok ini
            System.err.println("=========================================");
            System.err.println("STATUS: KONEKSI GAGAL!");

            // Menampilkan pesan error spesifik agar mudah didiagnosis
            System.err.println("Pesan Error: " + e.getMessage());
            System.err.println("=========================================");
        }
    }
}