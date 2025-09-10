package dk.ek.library.Catalog.Service;

import dk.ek.library.Catalog.Model.Author;
import dk.ek.library.Catalog.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
        return (Author) authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) throws Throwable {
        return (Author) authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(updatedAuthor.getName());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
