package dk.ek.library.Catalog.Controller;

import dk.ek.library.Catalog.Service.AuthorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

}
