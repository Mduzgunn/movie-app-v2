package com.md.movieappv2.controller;

import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.request.CreateActorRequest;
import com.md.movieappv2.dto.request.UpdateActorRequest;
import com.md.movieappv2.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v2/actor")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ActorDto> createActor(@Valid @RequestBody CreateActorRequest createActorRequest) {
        return ResponseEntity.ok(actorService.createActor(createActorRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActorDto>> getActors() {
        List<ActorDto> actorDtoList = actorService.getAllActorDtoList();
        return ResponseEntity.ok(actorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable String id) {
        ActorDto actorDto = actorService.getActorById(id);
        return ResponseEntity.ok(actorDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable String id, @Valid @RequestBody UpdateActorRequest updateActorRequest) {
        return ResponseEntity.ok(actorService.updateActor(id, updateActorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActorById(@PathVariable String id) {
        return ResponseEntity.ok(actorService.deleteActorById(id));
    }

}
