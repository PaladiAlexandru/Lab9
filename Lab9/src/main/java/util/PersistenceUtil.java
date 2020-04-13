package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


//A singleton class
public class PersistenceUtil {

    //We create the single instance that will be used
    private static PersistenceUtil instance = null;

    //an EntityManagerFactory atribute to support instantiation of EntityManager instances.
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "MusicAlbumsPU");

    //We make the constructor private, so they can't create
    //other instances with the construcor
    private PersistenceUtil() {
    }

    public static PersistenceUtil getInstance() {

        //if instance is null or emf is closed we return a new PersistenceUtil instance
        //otherwise we return the same instance
        if (instance == null) {
            return new PersistenceUtil();
        } else if (!emf.isOpen()) {
            return new PersistenceUtil();
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
