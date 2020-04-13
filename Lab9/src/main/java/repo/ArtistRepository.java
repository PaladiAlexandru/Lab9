package repo;

import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

public class ArtistRepository {
    //We create the manager for our repository
    private EntityManager entityManager;

    public ArtistRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Artist> create(Artist artist) {
        try {
            //Opens a transaction to use in database interactions
            entityManager.getTransaction().begin();
            //We persist the artist object to the database
            entityManager.persist(artist);
            //comit calles the flush and synchronizes the state with the database
            entityManager.getTransaction().commit();
            return Optional.of(artist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // We use Optional class, in case that findByName method returns a null value;
    // So we inform the caller that the value might be null
    public Optional<Artist> findByName(String name) {

        //The entityManager uses a namedQuery "Artist.findByName", declared in entity class
        // We set the query parameter "name" to our name
        // And we call the getSingleResult method because we expect only one result
        Artist artist = null;
        try {
            artist = entityManager.createNamedQuery("Artist.findByName", Artist.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("The artist doesn't exist");
        }
        // We check if the artist was found, otherwise we return an Optional instance, wich contains nothing
        return artist != null ? Optional.of(artist) : Optional.empty();
    }

    //We call the find method of entityManager, who uses the primary key for finding an Artist
    public Optional<Artist> findById(Integer id) {
        Artist artist = entityManager.find(Artist.class, id);
        return artist != null ? Optional.of(artist) : Optional.empty();
    }
}
