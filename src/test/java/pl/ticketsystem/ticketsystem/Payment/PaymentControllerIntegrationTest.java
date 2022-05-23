package pl.ticketsystem.ticketsystem.Payment;

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
class PaymentControllerIntegrationTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/api/payment");
    }

    @Test
    @WithAnonymousUser
    void shouldNotReturnPayment() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress())
                .build();
        ResponseEntity<List<Payment>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Payment>>(){});
        // then:
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}