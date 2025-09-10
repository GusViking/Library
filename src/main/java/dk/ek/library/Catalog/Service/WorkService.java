package dk.ek.library.Catalog.Service;

import dk.ek.library.Catalog.DTO.WorkDto;
import dk.ek.library.Catalog.Mapper.WorkMapper;
import dk.ek.library.Catalog.Model.Author;
import dk.ek.library.Catalog.Model.Subject;
import dk.ek.library.Catalog.Model.Work;
import dk.ek.library.Catalog.Model.WorkType;
import dk.ek.library.Catalog.Repository.AuthorRepository;
import dk.ek.library.Catalog.Repository.SubjectRepository;
import dk.ek.library.Catalog.Repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;
    private final WorkMapper workMapper;

    public WorkService(
            WorkRepository workRepository,
            AuthorRepository authorRepository,
            SubjectRepository subjectRepository,
            WorkMapper workMapper
    ) {
        this.workRepository = workRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
        this.workMapper = workMapper;
    }

    public WorkDto createWork(WorkDto workDto) {
        Work work = workMapper.toWorkEntity(workDto);

        // Resolve authors and subjects from IDs in DTO
        Set<Author> authors = (Set<Author>) authorRepository.findAllById(workDto.authorIds())
                .stream().collect(Collectors.toSet());
        Set<Subject> subjects = (Set<Subject>) subjectRepository.findAllById(workDto.subjectIds())
                .stream().collect(Collectors.toSet());

        work.setAuthors(authors);
        work.setSubjects(subjects.toString());

        Work saved = workRepository.save(work);
        return workMapper.toWorkDto(saved);
    }

    public List<WorkDto> getAllWorks() {
        return workRepository.findAll()
                .stream()
                .map(workMapper::toWorkDto)
                .collect(Collectors.toList());
    }

    public WorkDto getWorkById(Long id) {
        return workRepository.findById(id)
                .map(workMapper::toWorkDto)
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + id));
    }

    public WorkDto updateWork(Long id, WorkDto updatedWorkDto) {
        return workRepository.findById(id)
                .map(work -> {
                    work.setTitle(updatedWorkDto.title());
                    work.setWorkType(Enum.valueOf(
                            WorkType.class,
                            updatedWorkDto.workType()
                    ));
                    work.setDetails(updatedWorkDto.details());

                    // Update relationships
                    Set<Author> authors = (Set<Author>) authorRepository.findAllById(updatedWorkDto.authorIds())
                            .stream().collect(Collectors.toSet());
                    Set<Subject> subjects = (Set<Subject>) subjectRepository.findAllById(updatedWorkDto.subjectIds())
                            .stream().collect(Collectors.toSet());

                    work.setAuthors(authors);
                    work.setSubjects(subjects.toString());

                    Work updated = workRepository.save(work);
                    return workMapper.toWorkDto(updated);
                })
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + id));
    }

    public void deleteWork(Long id) {
        if (!workRepository.existsById(id)) {
            throw new RuntimeException("Work not found with id: " + id);
        }
        workRepository.deleteById(id);
    }

    public List<WorkDto> searchWorks(String title) {
        return workRepository.findByTitleContaining(title)
                .stream()
                .map(workMapper::toWorkDto)
                .collect(Collectors.toList());
    }
}
