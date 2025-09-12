package dk.ek.library.Catalog.Service;

import dk.ek.library.Catalog.DTO.WorkDto;
import dk.ek.library.Catalog.Mapper.WorkMapper;
import dk.ek.library.Catalog.Model.Author;
import dk.ek.library.Catalog.Model.Subject;
import dk.ek.library.Catalog.Model.Work;
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

    public WorkService(WorkRepository workRepository,
                       AuthorRepository authorRepository,
                       SubjectRepository subjectRepository,
                       WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
        this.workMapper = workMapper;
    }

    // 🔹 Create Work
    public WorkDto createWork(WorkDto workDto) {
        Set<Author> authors = authorRepository.findAllById(workDto.authorIds()).stream().collect(Collectors.toSet());
        Set<Subject> subjects = subjectRepository.findAllById(workDto.subjectIds()).stream().collect(Collectors.toSet());

        Work work = workMapper.toWorkEntity(workDto, authors, subjects);
        Work saved = workRepository.save(work);
        return workMapper.toWorkDto(saved);
    }

    // 🔹 Get all
    public List<WorkDto> getAllWorks() {
        return workRepository.findAll()
                .stream()
                .map(workMapper::toWorkDto)
                .collect(Collectors.toList());
    }

    // 🔹 Get by ID
    public WorkDto getWorkById(Long id) {
        return workRepository.findById(id)
                .map(workMapper::toWorkDto)
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + id));
    }

    // 🔹 Update Work
    public WorkDto updateWork(Long id, WorkDto updatedWorkDto) {
        return workRepository.findById(id)
                .map(work -> {
                    work.setTitle(updatedWorkDto.title());
                    if (updatedWorkDto.workType() != null) {
                        work.setWorkType(Enum.valueOf(dk.ek.library.Catalog.Model.WorkType.class, updatedWorkDto.workType()));
                    }
                    work.setDetails(updatedWorkDto.details());

                    // 🔹 Resolve authors and subjects from IDs
                    Set<Author> authors = authorRepository.findAllById(updatedWorkDto.authorIds())
                            .stream().collect(Collectors.toSet());
                    Set<Subject> subjects = subjectRepository.findAllById(updatedWorkDto.subjectIds())
                            .stream().collect(Collectors.toSet());

                    work.setAuthors(authors);
                    work.setSubjects(subjects);

                    Work updated = workRepository.save(work);
                    return workMapper.toWorkDto(updated);
                })
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + id));
    }

    // 🔹 Delete
    public void deleteWork(Long id) {
        if (!workRepository.existsById(id)) {
            throw new RuntimeException("Work not found with id: " + id);
        }
        workRepository.deleteById(id);
    }

    // 🔹 Search
    public List<WorkDto> searchWorks(String title) {
        return workRepository.findByTitleContaining(title)
                .stream()
                .map(workMapper::toWorkDto)
                .collect(Collectors.toList());
    }

    // 🔹 Add Authors to Work
    public WorkDto addAuthorsToWork(Long workId, List<Long> authorIds) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + workId));

        Set<Author> authors = authorRepository.findAllById(authorIds).stream().collect(Collectors.toSet());
        work.getAuthors().addAll(authors);

        Work updated = workRepository.save(work);
        return workMapper.toWorkDto(updated);
    }

    // 🔹 Add Subjects to Work
    public WorkDto addSubjectsToWork(Long workId, List<Long> subjectIds) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new RuntimeException("Work not found with id: " + workId));

        Set<Subject> subjects = subjectRepository.findAllById(subjectIds).stream().collect(Collectors.toSet());
        work.getSubjects().addAll(subjects);

        Work updated = workRepository.save(work);
        return workMapper.toWorkDto(updated);
    }
}
