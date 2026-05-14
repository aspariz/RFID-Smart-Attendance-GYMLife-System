package DAOO;

import org.bson.conversions.Bson;
import java.util.List;
/**
 *
 * @author bsame
 * @param <T>
 */
public interface BaseDAO<T> {
    void save(T entity);
    void update(Bson filter , T entity);
    void delete(Bson filter );
    
    List<T> findAll();
    T findOne(Bson filter);
    List<T> findMany(Bson filter);
}