package com.md.movieappv2.controller;

import com.md.movieappv2.dto.PublisherDto;
import com.md.movieappv2.dto.request.CreatePublisherRequest;
import com.md.movieappv2.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<PublisherDto> createPublisher(@Valid @RequestBody CreatePublisherRequest createPublisherRequest) {
        return ResponseEntity.ok(publisherService.createPublisher(createPublisherRequest));
    }

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getPublishers() {
        List<PublisherDto> publisherDtoList = publisherService.getAllPublisherDtoList();
        return ResponseEntity.ok(publisherDtoList);
    }

    @GetMapping("/(id)")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable String id) {
        PublisherDto publisherDto = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisherDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisherById(@PathVariable String id) {
        return ResponseEntity.ok(publisherService.deletePublisherById(id));
    }
}
