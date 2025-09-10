package dk.ek.library.Catalog.Service;

import dk.ek.library.Catalog.Model.Subject;
import dk.ek.library.Catalog.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        return subjectRepository.findById(id)
                .map(subject -> {
                    subject.setName(updatedSubject.getName());
                    return subjectRepository.save(subject);
                })
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }
}
