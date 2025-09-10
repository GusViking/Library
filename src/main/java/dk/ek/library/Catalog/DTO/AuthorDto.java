package dk.ek.library.Catalog.DTO;

import java.util.Set;

public record AuthorDto(
        Long id,
        String name,
        Set<Long> workIds
) {}
