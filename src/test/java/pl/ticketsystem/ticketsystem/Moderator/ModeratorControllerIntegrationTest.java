package pl.ticketsystem.ticketsystem.Moderator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ModeratorControllerIntegrationTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/api/moderator");
    }

    @Test
    @WithAnonymousUser
    void shouldNotReturnModerators() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress())
                .build();
        ResponseEntity<List<Moderator>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Moderator>>(){});
        // then:
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}