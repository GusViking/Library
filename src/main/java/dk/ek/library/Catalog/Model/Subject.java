package dk.ek.library.Catalog.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "subjects")
    private Set<Author> authors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
