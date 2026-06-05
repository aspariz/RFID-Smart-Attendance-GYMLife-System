package DAOO;

import org.bson.conversions.Bson; // Format filter/query untuk MongoDB
import java.util.List;
/**
 *
 * @author bsame
 * @param <T> tipe data entitas yang akan di kelola (member,admin)  implementasi
 */
public interface BaseDAO<T> {
    void save(T entity); //untuk menyimpan satu entitas baru di DB
    void update(Bson filter , T entity); //untuk memperbarui data entitas yang cocok dengan filter
    void delete(Bson filter ); //untuk menghapus entitas dari DB
    
    List<T> findAll();
    T findOne(Bson filter);
    List<T> findMany(Bson filter); //mengambil banyak entitas yang cocok dengn filter
}