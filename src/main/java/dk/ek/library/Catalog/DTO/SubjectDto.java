package dk.ek.library.Catalog.DTO;

import java.util.Set;

public record SubjectDto(
        Long id,
        String name,
        Set<Long> workIds
) {}
