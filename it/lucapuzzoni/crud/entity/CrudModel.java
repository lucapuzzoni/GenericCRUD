package it.lucapuzzoni.crud.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Luca Puzzoni
 * Standard model
 */
@MappedSuperclass @Data
public abstract class CrudModel<T> {
    @Id private String id;

    public abstract void absorbe(T changes);
}
