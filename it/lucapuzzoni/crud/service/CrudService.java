package it.lucapuzzoni.crud.service;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.lucapuzzoni.crud.entity.CrudModel;
import it.lucapuzzoni.crud.exception.NotFoundException;
import org.hibernate.PropertyValueException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author Luca Puzzoni
 * Standard Service Interface for CRUD operations
 * @param <T> Real Entity
 */
public interface CrudService <T extends CrudModel<T>> {
    T create(T t) throws PropertyValueException, MysqlDataTruncation, SQLIntegrityConstraintViolationException;
    List<T> findAll();
    T findById(String id) throws NotFoundException;
    T update (String id, T changes) throws PropertyValueException, MysqlDataTruncation, SQLIntegrityConstraintViolationException, NotFoundException;
    void delete (String id) throws NotFoundException;
}
