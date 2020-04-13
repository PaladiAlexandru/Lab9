package app;

import entity.Artist;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;


public class AlbumManager {
    public static void main(String[] args) {
        int artistId = 31;
        String artistName = "MyName";

        //Creating the instance of persistenceUtil in order to get our EntityManagerFactory
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        //Getting EntityManagerFactory
        EntityManagerFactory emf = persistenceUtil.getEmf();
        //Creating an EntityManager
        EntityManager em = emf.createEntityManager();
        //Creating an ArtistRepository and passing the EntityManager
        ArtistRepository artistRepository = new ArtistRepository(em);

        Artist myArtist = new Artist();
        myArtist.setName(artistName);
        //We are saving myArtist to the database
        artistRepository.create(myArtist);

        //Creating and Optional collection of Artists just in case the query returns null
        Optional<Artist> artist = artistRepository.findById(artistId);
        //We print the name of the artist
        System.out.println(artist.get().getName());
        artist = artistRepository.findByName(artistName);
        //We print the name of the artist
        System.out.println(artist.get().getName());


    }
}
