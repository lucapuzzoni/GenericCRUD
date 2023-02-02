package it.lucapuzzoni.crud.service.impl;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.lucapuzzoni.crud.entity.CrudModel;
import it.lucapuzzoni.crud.exception.NotFoundException;
import it.lucapuzzoni.crud.repository.CrudRepository;
import it.lucapuzzoni.crud.service.CrudService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;


/**
 * @author Luca Puzzoni
 * Standard Service Implementation for CRUD operations
 * @param <T> Real Entity
 */
public class CrudServiceImpl<T extends CrudModel<T>> implements CrudService<T> {
    @Autowired private CrudRepository<T> repository;

    /**
     * Creates a new entity
     * @param t Entity to create
     * @return returns the created entity
     * @throws PropertyValueException If a no null field is null
     * @throws MysqlDataTruncation If a field is too long
     * @throws SQLIntegrityConstraintViolationException If a unique field is duplicated
     */
    @Override
    public T create(T t) throws PropertyValueException, MysqlDataTruncation, SQLIntegrityConstraintViolationException {
        t.setId(UUID.randomUUID().toString());
        return this.repository.save(t);
    }

    /**
     * @return Returns the list of the entities
     */
    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }

    /**
     * @param id id to find
     * @return Returns an entity with [id]
     */
    @Override
    public T findById(String id) throws NotFoundException {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * @param id id to find
     * @param changes changes to apply to the entity founded by [id]
     * @return Returns the entity saved after the update
     * @throws PropertyValueException If a no null field is null
     * @throws MysqlDataTruncation If a field is too long
     * @throws SQLIntegrityConstraintViolationException If a unique field is duplicated
     */
    @Override
    public T update(String id, T changes) throws PropertyValueException, MysqlDataTruncation, SQLIntegrityConstraintViolationException, NotFoundException {
        T t = this.findById(id);
        t.absorbe(changes);
        return this.repository.save(t);
    }

    /**
     * Deletes an entity founded by [id]
     * @param id id to find
     */
    @Override
    public void delete(String id) throws NotFoundException {
        T t = this.findById(id);
        this.repository.delete(t);
    }
}
