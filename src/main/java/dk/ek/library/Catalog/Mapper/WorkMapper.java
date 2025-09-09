package dk.ek.library.Catalog.Mapper;

import dk.ek.library.Catalog.Model.*;
import dk.ek.library.Catalog.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkMapper {

    public WorkDto toWorkDto(Work work) {
        return new WorkDto(
                work.getId(),
                work.getTitle(),
                work.getWorkType() != null ? work.getWorkType().name() : null,
                work.getDetails(),
                work.getAuthors(),
                work.getEditions() != null
                        ? work.getEditions().stream().map(WorkMapper::toEditionDto).collect(Collectors.toList())
                        : List.of(),
                work.getSubjects()
        );
    }

    public Work toWorkEntity(WorkDto dto) {
        Work work = new Work();
        work.setId(dto.id());
        work.setTitle(dto.title());
        work.setWorkType(dto.workType() != null ? WorkType.valueOf(dto.workType()) : null);
        work.setDetails(dto.details());
        work.setAuthors(dto.author());
        work.setSubjects(dto.subjects());
        // editions handled separately to avoid circular mapping
        return work;
    }

    public static EditionDto toEditionDto(Edition edition) {
        return new EditionDto(
                edition.getId(),
                edition.getEditNumber(),
                edition.getYear(),
                edition.getFormat(),
                edition.getPublisher() != null ? toPublisherDto(edition.getPublisher()) : null
        );
    }

    public static Edition toEditionEntity(EditionDto dto) {
        Edition edition = new Edition();
        edition.setId(dto.id());
        edition.setEditNumber(dto.editionNumber());
        edition.setYear(dto.year());
        edition.setFormat(dto.format());
        if (dto.publisher() != null) {
            edition.setPublisher(toPublisherEntity(dto.publisher()));
        }
        return edition;
    }

    public static PublisherDto toPublisherDto(Publisher publisher) {
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getContactInfo()
        );
    }

    public static Publisher toPublisherEntity(PublisherDto dto) {
        Publisher publisher = new Publisher();
        publisher.setId(dto.id());
        publisher.setName(dto.name());
        publisher.setAddress(dto.address());
        publisher.setContactInfo(dto.contactInfo());
        return publisher;
    }
    public WorkDto toDto(Work work) {
        return toWorkDto(work);
    }

    public Work toEntity(WorkDto dto) {
        return toWorkEntity(dto);
    }
}