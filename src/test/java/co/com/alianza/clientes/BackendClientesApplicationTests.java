package co.com.alianza.clientes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BackendClientesApplicationTests.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class BackendClientesApplicationTests {

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testMain() {
		final var args = new String[] {};
		BackendClientesApplication.main(args);
	}

}
