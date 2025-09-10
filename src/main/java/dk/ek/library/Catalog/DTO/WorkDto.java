package dk.ek.library.Catalog.DTO;

import java.util.Set;

public record WorkDto(
        Long id,
        String title,
        String workType,
        String details,
        Set<Long> authorIds,
        Set<Long> subjectIds
) {}
