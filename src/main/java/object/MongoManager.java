package com.mycompany.gymlife1.object;

// Import untuk konfigurasi koneksi MongoDB
import com.mongodb.MongoClientSettings;       // Berisi pengaturan default MongoDB client
import com.mongodb.client.MongoClient;         // Interface utama untuk koneksi ke MongoDB
import com.mongodb.client.MongoClients;        // Factory class untuk membuat instance MongoClient
import com.mongodb.client.MongoDatabase;       // Interface untuk merepresentasikan sebuah database MongoDB

// Import untuk konfigurasi codec (sistem serialisasi/deserialisasi data)
import org.bson.codecs.configuration.CodecRegistries;   // Utility untuk menggabungkan beberapa CodecRegistry
import org.bson.codecs.configuration.CodecRegistry;     // Interface yang menyimpan kumpulan codec
import org.bson.codecs.pojo.PojoCodecProvider;          // Provider codec khusus untuk mapping POJO ke/dari BSON

public class MongoManager {

    // Menyimpan satu instance MongoClient (Singleton Pattern - hanya dibuat sekali)
    private static MongoClient mongoClient;

    // Nama database yang akan digunakan di MongoDB
    private static final String DATABASE_NAME = "gymlife_db";

    public static MongoDatabase getDatabase() {

        // Cek apakah koneksi belum pernah dibuat (lazy initialization)
        if (mongoClient == null) {

            // Gabungkan codec default MongoDB dengan codec POJO otomatis
            // - getDefaultCodecRegistry() : mendukung tipe dasar (String, Integer, dll)
            // - PojoCodecProvider.automatic(true) : otomatis mapping class Java (POJO) ke dokumen BSON
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            // Buat koneksi baru ke MongoDB yang berjalan di localhost port 27017
            mongoClient = MongoClients.create("mongodb://localhost:27017");

            // Ambil database "gymlife_db" dan terapkan codec registry yang sudah dikonfigurasi
            // withCodecRegistry() memastikan POJO bisa otomatis dikonversi ke/dari dokumen MongoDB
            return mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
        }

        // Jika koneksi sudah ada, langsung kembalikan database tanpa membuat koneksi baru
        // (tanpa pojoCodecRegistry karena hanya dipakai saat pertama kali inisialisasi)
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}