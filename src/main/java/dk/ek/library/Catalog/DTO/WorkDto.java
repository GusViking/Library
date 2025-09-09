package dk.ek.library.Catalog.DTO;

import java.util.List;

public record WorkDto(
        Long id,
        String title,
        String workType,
        String details,
        String author,
        List<EditionDto> editions,
        String subjects
) {}
