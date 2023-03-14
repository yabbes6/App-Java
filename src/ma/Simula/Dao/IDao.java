package ma.Simula.Dao;

public interface IDao<T , ID> {

    T trouverParId(ID id);
}
