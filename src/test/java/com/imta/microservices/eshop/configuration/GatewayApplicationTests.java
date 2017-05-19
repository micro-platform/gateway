package com.imta.microservices.eshop.configuration;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayApplicationTests {
	@LocalServerPort
	private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

	@Before
	public void setup() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/test/");
	}

	@Test
	public void testBaseRoute() {
        ResponseEntity<String> response = template.withBasicAuth("admin1", "admin1").getForEntity(base.toString(), String.class);

        assertEquals(response.getBody(), "Test qui marche");
	}

}
