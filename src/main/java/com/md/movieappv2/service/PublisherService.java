package com.md.movieappv2.service;

import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.dto.PublisherDto;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.exception.PublisherNotFoundException;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.model.Publisher;
import com.md.movieappv2.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    protected Publisher findPublisherById(String id) {
        return publisherRepository
                .findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("publisher not found " + id));
    }

    protected List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }


}
