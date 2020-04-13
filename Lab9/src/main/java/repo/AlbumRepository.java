package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class AlbumRepository {
    //We create the manager for our repository
    private EntityManager entityManager;

    public AlbumRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Album> create(Album album) {
        try {
            //Opens a transaction to use in database interactions
            entityManager.getTransaction().begin();
            //We persist the album object to the database
            entityManager.persist(album);
            //comit calles the flush and synchronizes the state with the database
            entityManager.getTransaction().commit();
            return Optional.of(album);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // We use Optional class, in case that findByName method returns a null value;
    // So we inform the caller that the value might be null
    public Optional<Album> findByName(String name) {

        //The entityManager uses a namedQuery "Album.findByName", declared in entity class
        // We set the query parameter "name" to our name
        // And we call the getSingleResult method because we expect only one result
        Album album = null;
        try {
            album = entityManager.createNamedQuery("Album.findByName", Album.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("The album doesn't exist");
        }
        // We check if the album was found, otherwise we return an Optional instance, wich contains nothing
        return album != null ? Optional.of(album) : Optional.empty();
    }

    //We call the find method of entityManager, who uses the primary key for finding an Album
    public Optional<Album> findById(Integer id) {
        Album album = entityManager.find(Album.class, id);
        return album != null ? Optional.of(album) : Optional.empty();
    }

    public List<Album> findByArtist(Artist artist) {
        List<Album> album = null;
        try {
            //We add to the album list all the albums that our query finds by artistId
            album = entityManager.createNamedQuery("Album.findByArtist", Album.class)
                    .setParameter("artist", artist)
                    .getResultList();
        } catch (NoResultException e) {
            System.err.println("The album doesn't exist");
        }
        System.out.println(album);
        return album;
    }
}
