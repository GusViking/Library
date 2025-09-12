package dk.ek.library.Catalog.DTO;

import dk.ek.library.Catalog.Model.Author;

import java.util.List;
import java.util.Set;

public record WorkDto(
        Long id,
        String title,
        String workType,
        String details,
        Set<Long> authorIds,
        Set<Long> subjectIds,
        List<EditionDto> editions
) {}
