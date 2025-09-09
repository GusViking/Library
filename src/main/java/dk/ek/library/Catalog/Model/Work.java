package dk.ek.library.Catalog.Model;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    private String details;
    private String subjects;

    // Many-to-Many with Author
    @ManyToMany
    @JoinTable(
            name = "work_author",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}