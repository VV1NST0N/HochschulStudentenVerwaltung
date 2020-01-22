package dataAccess;

public interface DaoTemplate<T> {

    T getEntryById(Integer id);
}
