package framework.dao;

import java.util.ArrayList;

public abstract class DataAccessObject <T> {
    
    public abstract void insert(T t) throws Exception;
    public abstract void update(T t) throws Exception;
    public abstract void delete(T t) throws Exception;
    
    public abstract T getUnique(Object... values) throws Exception;
    public abstract ArrayList<T> getAll() throws Exception;

}