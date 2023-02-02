package it.lucapuzzoni.crud.code;

/**
 * @author Luca Puzzoni
 * Default return codes
 */
public interface GlobalCodes {
    public static final String CRUD_CREATE_OK = "CRUD-001";
    public static final String CRUD_CREATE_VALUE_EXCEPTION = "CRUD-002";
    public static final String CRUD_CREATE_DATA_TRUNCATION = "CRUD-003";
    public static final String CRUD_CREATE_INTEGRITY = "CRUD-004";
    public static final String CRUD_CREATE_EXCEPTION = "CRUD-005";
    public static final String CRUD_FINDONE_OK = "CRUD-006";
    public static final String CRUD_FINDONE_NOT_FOUND = "CRUD-007";
    public static final String CRUD_FINDONE_EXCEPTION = "CRUD-008";
    public static final String CRUD_FIND_OK = "CRUD-009";
    public static final String CRUD_UPDATE_OK = "CRUD-010";
    public static final String CRUD_UPDATE_NOT_FOUND = "CRUD-011";
    public static final String CRUD_UPDATE_VALUE_EXCEPTION = "CRUD-012";
    public static final String CRUD_UPDATE_DATA_TRUNCATION = "CRUD-013";
    public static final String CRUD_UPDATE_INTEGRITY = "CRUD-014";
    public static final String CRUD_DELETE_OK = "CRUD-015";
    public static final String CRUD_DELETE_NOT_FOUND = "CRUD-016";
    public static final String CRUD_DELETE_EXCEPTION = "CRUD-017";
}