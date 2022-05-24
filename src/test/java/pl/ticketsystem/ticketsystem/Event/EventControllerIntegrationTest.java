package pl.ticketsystem.ticketsystem.Event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithMockUser(username="moderator",roles={"MODERATOR"})
class EventControllerIntegrationTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/api/event");
    }

    @Test
    @WithAnonymousUser
    void shouldReturnEvents() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress())
                .build();
        ResponseEntity<List<Event>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Event>>(){});
        // then:
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithAnonymousUser
    void shouldReturnClientOrders() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress() + "/client/orders/1")
                .build();
        ResponseEntity<List<Object[]>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Object[]>>(){});
        // then:
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithAnonymousUser
    void shouldNotReturnEvent() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress() + "/1")
                .build();
        ResponseEntity<Event> response = restTemplate.exchange(request, new ParameterizedTypeReference<Event>(){});
        // then:
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNull(response.getBody());
    }

    @Test
    @WithAnonymousUser
    void shouldReturnCategoryEvent() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress() + "/category/1")
                .build();
        ResponseEntity<List<Event>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Event>>(){});
        // then:
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}