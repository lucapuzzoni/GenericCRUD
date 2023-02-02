package it.lucapuzzoni.crud.repository;

import it.lucapuzzoni.crud.entity.CrudModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luca Puzzoni
 * Standard Repository
 * @param <T> Real class
 */
public interface CrudRepository<T extends CrudModel<T>> extends JpaRepository<T, String> {}