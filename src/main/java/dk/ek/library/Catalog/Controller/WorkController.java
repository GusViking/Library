package dk.ek.library.Catalog.Controller;

import dk.ek.library.Catalog.DTO.WorkDto;
import dk.ek.library.Catalog.Service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    // 🔹 Get all works
    @GetMapping
    public ResponseEntity<List<WorkDto>> getAllWorks(){
        return ResponseEntity.ok(workService.getAllWorks());
    }

    // 🔹 Get work by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(workService.getWorkById(id));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🔹 Create work with authors + subjects
    @PostMapping
    public ResponseEntity<WorkDto> createWork(@RequestBody WorkDto workDto) {
        WorkDto created = workService.createWork(workDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 🔹 Update work (including relationships)
    @PutMapping("/{id}")
    public ResponseEntity<WorkDto> updateWork(@PathVariable Long id, @RequestBody WorkDto workDto) {
        try {
            WorkDto updated = workService.updateWork(id, workDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🔹 Delete work
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 🔹 Search works by title
    @GetMapping("/search")
    public ResponseEntity<List<WorkDto>> searchWorks(@RequestParam String title) {
        return ResponseEntity.ok(workService.searchWorks(title));
    }

    // 🔹 Add authors to a work
    @PostMapping("/{id}/authors")
    public ResponseEntity<WorkDto> addAuthorsToWork(@PathVariable Long id, @RequestBody List<Long> authorIds) {
        try {
            WorkDto updated = workService.addAuthorsToWork(id, authorIds);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🔹 Add subjects to a work
    @PostMapping("/{id}/subjects")
    public ResponseEntity<WorkDto> addSubjectsToWork(@PathVariable Long id, @RequestBody List<Long> subjectIds) {
        try {
            WorkDto updated = workService.addSubjectsToWork(id, subjectIds);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
