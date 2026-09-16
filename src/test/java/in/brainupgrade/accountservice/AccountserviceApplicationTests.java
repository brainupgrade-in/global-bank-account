package in.brainupgrade.accountservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.config.import=optional:configserver:", "spring.cloud.config.enabled=false" })
class AccountserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
