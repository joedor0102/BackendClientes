package co.com.alianza.clientes.model;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

/**
 * TestCliente.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@RunWith(SpringRunner.class)
class TestCliente {

	@Test
	void testCliente() {

		/* lombok allConstructor */
		final var clienteAllConstructor = new Cliente(1L, "jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com",
				3219876543L, LocalDate.parse("2019-05-01"), LocalDate.parse("2019-06-30"),
				LocalDate.parse("2019-05-20"));
		/* lombok build */
		final var clienteBuild = Cliente.builder().id(1L).sharedKey("jgutierrez").bussinessId("Juliana Gutierrez")
				.email("jgutierrez@gmail.com").phone(3219876543L).startDate(LocalDate.parse("2019-05-01"))
				.endDate(LocalDate.parse("2019-06-30")).creationDate(LocalDate.parse("2019-05-20")).build();
		/* lombok build toString */
		final var clienteBuildStr = Cliente.builder().id(1L).sharedKey("jgutierrez").bussinessId("Juliana Gutierrez")
				.email("jgutierrez@gmail.com").phone(3219876543L).startDate(LocalDate.parse("2019-05-01"))
				.endDate(LocalDate.parse("2019-06-30")).creationDate(LocalDate.parse("2019-05-20")).toString();
		/* defined constructor */
		final var cliente = new Cliente("jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com", 3219876543L,
				LocalDate.parse("2019-05-01"), LocalDate.parse("2019-06-30"), LocalDate.parse("2019-05-20"));
		cliente.setId(1L);
		/* toString */
		final var strCliente = "Cliente(id=1, sharedKey=jgutierrez, bussinessId=Juliana Gutierrez, email=jgutierrez@gmail.com, phone=3219876543, startDate=2019-05-01, endDate=2019-06-30, creationDate=2019-05-20)";

		Assert.assertEquals(clienteAllConstructor.toString(), strCliente);
		Assert.assertEquals(clienteBuild.toString(), strCliente);

		Assert.assertEquals(1L, cliente.getId());
		Assert.assertEquals("jgutierrez", cliente.getSharedKey());
		Assert.assertEquals("Juliana Gutierrez", cliente.getBussinessId());
		Assert.assertEquals("jgutierrez@gmail.com", cliente.getEmail());
		Assert.assertEquals(3219876543L, (long) cliente.getPhone());
		Assert.assertEquals(LocalDate.parse("2019-05-01"), cliente.getStartDate());
		Assert.assertEquals(LocalDate.parse("2019-06-30"), cliente.getEndDate());
		Assert.assertEquals(LocalDate.parse("2019-05-20"), cliente.getCreationDate());
	}

}
