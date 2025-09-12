package dk.ek.library.Catalog.Controller;

import dk.ek.library.Catalog.Service.SubjectService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

}
