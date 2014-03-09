package commons.domain.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Павел on 09.03.14.
 */
public interface GenericDao<T, ID extends Serializable> {

    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
    void flush();
}

