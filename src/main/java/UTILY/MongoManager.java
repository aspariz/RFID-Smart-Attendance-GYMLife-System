package UTILY;

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
    CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );
    
    if (mongoClient == null) {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
    }
    
    return mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
}
}