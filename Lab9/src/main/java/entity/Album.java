package entity;

import javax.persistence.*;

@Entity
@Table(name = "albums")
//We declare a namedQuery for our EntityManager
@NamedQueries({
        @NamedQuery(name = "Album.findByName",
                query = "SELECT a FROM Album a WHERE a.name = :name"),
        @NamedQuery(name = "Album.findByArtist",
                query = "SELECT a FROM Album a WHERE a.artist = :artist")

})
public class Album {
    //@Id linkes the id atribute as primary key for this table
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    //The Album class holds a reference to its single artist
    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @Column(name = "release_year")
    private Integer releaseYear;


}
