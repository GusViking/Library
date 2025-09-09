package dk.ek.library.Catalog.Model;


import jakarta.persistence.*;

@Entity
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String editNumber;
    private int year;
    private String format;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;



    public Publisher getPublisher() {
        return publisher;
    }

    public Work getWork() {
        return work;
    }

    public Long getId() {
        return id;
    }

    public String getEditNumber() {
        return editNumber;
    }

    public Integer getYear() {
        return year;
    }

    public String getFormat() {
        return format;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEditNumber(String editNumber) {
        this.editNumber = editNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public void setFormat(String format) {
        this.format = format;

    }
}
