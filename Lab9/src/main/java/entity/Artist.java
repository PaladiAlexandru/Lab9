package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
//We declare a namedQuery for our EntityManager
@NamedQueries({
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT a FROM Artist a WHERE a.name = :name")
})
public class Artist implements Serializable {
    //@Id linkes the id atribute as primary key for this table
    //@GeneratedValue tells JPA that the primary key should be generated when entity is persisted to the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    //The Artist class maintains a list of all the albums and Artist creates
    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<Album>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
