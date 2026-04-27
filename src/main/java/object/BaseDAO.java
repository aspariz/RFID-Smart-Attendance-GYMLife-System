/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package object;
import java.util.List;
/**
 *
 * @author bsame
 */
public interface BaseDAO<T> {
    void save(T entity);
    void update(int index, T entity);
    void delete(int index);
    List<T> findAll();
    T findByIndex(int index);    
}