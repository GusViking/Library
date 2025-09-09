package dk.ek.library.Catalog.DTO;

public record EditionDto(
        Long id,
        String editionNumber,
        int year, String format,
        PublisherDto publisher
) {}
