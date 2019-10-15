package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract List<T> getAll() throws SQLException;
    public abstract T getById(int id);
    public abstract T update(T elem);
    public abstract void delete(int id);
    public abstract void delete(T elem);
    public abstract T create(T elem);
}
