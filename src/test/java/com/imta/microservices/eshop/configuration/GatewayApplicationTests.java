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
@SpringBootTest
public class GatewayApplicationTests {
	@LocalServerPort
	private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

	@Before
	public void setup() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void testBaseRoute() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertEquals(response.getBody(), "Test qui marche");
	}

}
