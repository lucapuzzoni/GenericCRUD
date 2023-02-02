package it.lucapuzzoni.crud.controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.lucapuzzoni.crud.entity.CrudModel;
import it.lucapuzzoni.crud.exception.NotFoundException;
import it.lucapuzzoni.crud.response.CrudResponse;
import it.lucapuzzoni.crud.service.CrudService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static it.lucapuzzoni.crud.code.GlobalCodes.*;

/**
 * @author Luca Puzzoni
 * Standard Crud Controller
 * @param <T> Real Entity
 */
public class CrudController<T extends CrudModel<T>> {
    @Autowired private CrudService<T> service;

    /**
     * Method to create a new Entity
     * @param t Real Entity
     * @return Returns a CrudResponse with the created entity or with an error
     */
    public CrudResponse<?> create(T t){
        CrudResponse<?> response;
        try{
            T result = this.service.create(t);
             response = CrudResponse.builder().status(HttpStatus.CREATED)
                    .code(CRUD_CREATE_OK).payload(result).build();
        } catch (PropertyValueException p){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_CREATE_VALUE_EXCEPTION).payload(p.getMessage()).build();
        } catch (MysqlDataTruncation msql){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_CREATE_DATA_TRUNCATION).payload(msql.getMessage()).build();
        } catch (SQLIntegrityConstraintViolationException s){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_CREATE_INTEGRITY).payload(s.getMessage()).build();
        } catch (Exception e){
            response = CrudResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .code(CRUD_CREATE_EXCEPTION).payload(e.getMessage()).build();
        }
        return response;
    }

    /**
     * Method to get an entity starting from its id
     * @param id ID to search
     * @return Returns the founded entity or an error
     */
    public CrudResponse<?> find(String id){
        CrudResponse<?> response;
        try{
            T result = this.service.findById(id);
            response = CrudResponse.builder().status(HttpStatus.OK)
                    .code(CRUD_FINDONE_OK).payload(result).build();
        } catch (NotFoundException nf){
            response = CrudResponse.builder().status(HttpStatus.NOT_FOUND)
                    .code(CRUD_FINDONE_NOT_FOUND).payload(nf).build();
        } catch (Exception e){
            response = CrudResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .code(CRUD_FINDONE_EXCEPTION).payload(e.getMessage()).build();
        }
        return response;
    }

    /**
     * Method to get the collection of entities
     * @return Returns the list of entities
     */
    public CrudResponse<?> find() {
        List<T> all = this.service.findAll();
        return CrudResponse.builder().status(HttpStatus.OK)
                .code(CRUD_FIND_OK).payload(all).build();
    }

    /**
     * Method to update a new Entity
     * @param id ID to find an entity
     * @param changes Changes to apply in the entity founded via id
     * @return Returns a CrudResponse with the updated entity or with an error
     */
    public CrudResponse<?> update(String id, T changes){
        CrudResponse<?> response;
        try{
            T result = this.service.update(id, changes);
            response = CrudResponse.builder().status(HttpStatus.OK)
                    .code(CRUD_UPDATE_OK).payload(result).build();
        } catch (NotFoundException nf){
            response = CrudResponse.builder().status(HttpStatus.NOT_FOUND)
                    .code(CRUD_UPDATE_NOT_FOUND).payload(nf).build();
        } catch (PropertyValueException p){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_UPDATE_VALUE_EXCEPTION).payload(p.getMessage()).build();
        } catch (MysqlDataTruncation msql){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_UPDATE_DATA_TRUNCATION).payload(msql.getMessage()).build();
        } catch (SQLIntegrityConstraintViolationException s){
            response = CrudResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .code(CRUD_UPDATE_INTEGRITY).payload(s.getMessage()).build();
        } catch (Exception e){
            response = CrudResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .code(CRUD_UPDATE_EXCEPTION).payload(e.getMessage()).build();
        }
        return response;
    }

    /**
     * Method to delete a new Entity
     * @param id ID to find an entity
     * @return Returns a CrudResponse
     */
    public CrudResponse<?> delete(String id){
        CrudResponse<?> response;
        try{
            this.service.delete(id);
            response = CrudResponse.builder().status(HttpStatus.OK)
                    .code(CRUD_DELETE_OK).payload("Deleted successfully").build();
        } catch (NotFoundException nf){
            response = CrudResponse.builder().status(HttpStatus.NOT_FOUND)
                    .code(CRUD_DELETE_NOT_FOUND).payload(nf).build();
        } catch (Exception e){
            response = CrudResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .code(CRUD_DELETE_EXCEPTION).payload(e.getMessage()).build();
        }
        return response;
    }
}
