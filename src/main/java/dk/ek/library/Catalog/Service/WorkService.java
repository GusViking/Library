package dk.ek.library.Catalog.Service;

import dk.ek.library.Catalog.DTO.WorkDto;
import dk.ek.library.Catalog.Mapper.WorkMapper;
import dk.ek.library.Catalog.Model.Work;
import dk.ek.library.Catalog.Repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    public WorkService(WorkRepository workRepository, WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
    }

    public WorkDto createWork(WorkDto workDto) {
        Work work = workMapper.toWorkEntity(workDto);
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
                            dk.ek.library.Catalog.Model.WorkType.class,
                            updatedWorkDto.workType()
                    ));
                    work.setDetails(updatedWorkDto.details());
                    work.setAuthors(updatedWorkDto.author());
                    work.setSubjects(updatedWorkDto.subjects());
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
