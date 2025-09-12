package dk.ek.library.Catalog.Model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
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

    @ManyToMany
    @JoinTable(
            name = "work_author",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "work_subject",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Edition> editions = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public WorkType getWorkType() { return workType; }
    public void setWorkType(WorkType workType) { this.workType = workType; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Set<Author> getAuthors() { return authors; }
    public void setAuthors(Set<Author> authors) { this.authors = authors; }

    public Set<Subject> getSubjects() { return subjects; }
    public void setSubjects(Set<Subject> subjects) { this.subjects = subjects; }

    public Set<Edition> getEditions() { return editions; }
    public void setEditions(Set<Edition> editions) { this.editions = editions; }
}
