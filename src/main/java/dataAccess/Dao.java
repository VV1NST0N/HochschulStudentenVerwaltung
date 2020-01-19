package dataAccess;

public abstract class Dao<T> {

    public abstract T getEntryById(Integer id);
}
