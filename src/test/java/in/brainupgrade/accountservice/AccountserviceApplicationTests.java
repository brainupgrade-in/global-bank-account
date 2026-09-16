package in.brainupgrade.accountservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountserviceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void exposesKubernetesHealthProbes() {
		assertThat(
				restTemplate.getForEntity("http://localhost:" + port + "/account/actuator/health/liveness", String.class)
						.getStatusCode())
				.isEqualTo(HttpStatus.OK);
		assertThat(
				restTemplate.getForEntity("http://localhost:" + port + "/account/actuator/health/readiness", String.class)
						.getStatusCode())
				.isEqualTo(HttpStatus.OK);
	}

	@Test
	void exposesOnlyExpectedActuatorEndpoints() {
		assertThat(restTemplate.getForEntity("http://localhost:" + port + "/account/actuator/info", String.class)
				.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(restTemplate.getForEntity("http://localhost:" + port + "/account/actuator/beans", String.class)
				.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
