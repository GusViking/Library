package dk.ek.library.Catalog.Controller;

import dk.ek.library.Catalog.DTO.WorkDto;
import dk.ek.library.Catalog.Model.Work;
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

    @GetMapping
    public ResponseEntity<List<WorkDto>> getAllWorks(){
        return ResponseEntity.ok(workService.getAllWorks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(workService.getWorkById(id));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<WorkDto> createWork(@RequestBody WorkDto workDto) {
        WorkDto created = workService.createWork(workDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<WorkDto> updateWork(@PathVariable Long id, @RequestBody WorkDto workDto) {
        try {
            WorkDto updated = workService.updateWork(id, workDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkDto>> searchWorks(@RequestParam String title) {
        return ResponseEntity.ok(workService.searchWorks(title));
    }
}
