package com.md.movieappv2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.repository.ActorRepository;
import com.md.movieappv2.repository.DirectorRepository;
import com.md.movieappv2.repository.MovieRepository;
import com.md.movieappv2.repository.PublisherRepository;
import com.md.movieappv2.service.ActorService;
import com.md.movieappv2.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Context ayaga kaldirir
@TestPropertySource(locations = "classpath:application.properties") // Test context icin kullanilacak propertyleri ayarlar.
@DirtiesContext
@AutoConfigureMockMvc // Context icerisindeki servletleri ayaga kaldirir.
public class IntegrationTestSupport extends TestSupport{

    @Autowired
    public MovieService movieService;

    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    public ActorRepository actorRepository;

    @Autowired
    public ActorService actorService;

    @Autowired
    public PublisherRepository publisherRepository;

    @Autowired
    public DirectorRepository directorRepository;

    @Autowired
    public MovieDtoConverter movieDtoConverter;

    public final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }

}
