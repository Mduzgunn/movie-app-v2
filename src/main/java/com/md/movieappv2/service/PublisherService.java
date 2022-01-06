package com.md.movieappv2.service;

import com.md.movieappv2.dto.PublisherDto;
import com.md.movieappv2.dto.converter.PublisherDtoConverter;
import com.md.movieappv2.dto.request.CreatePublisherRequest;
import com.md.movieappv2.exception.PublisherNotFoundException;
import com.md.movieappv2.model.Publisher;
import com.md.movieappv2.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherDtoConverter publisherDtoConverter;

    public PublisherService(PublisherRepository publisherRepository, PublisherDtoConverter publisherDtoConverter) {
        this.publisherRepository = publisherRepository;
        this.publisherDtoConverter = publisherDtoConverter;
    }

    protected Publisher findPublisherById(String id) {
        return publisherRepository
                .findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("publisher not found " + id));
    }

    public PublisherDto getPublisherById(String id) {
        return publisherDtoConverter.convert(findPublisherById(id));
    }

    protected List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public List<PublisherDto> getAllPublisherDtoList() {
        return publisherDtoConverter.convertToPublisherDtoList(getAllPublishers());
    }

    public PublisherDto createPublisher(CreatePublisherRequest createPublisherRequest) {
        Publisher publisher = new Publisher(
                createPublisherRequest.getName()
        );
        return publisherDtoConverter.convert(publisherRepository.save(publisher));
    }

    public String deletePublisherById(String id) {
        getPublisherById(id);
        publisherRepository.deleteById(id);
        return "publisher deleted successfully "+id;
    }

}
