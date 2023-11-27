package co.com.alianza.clientes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.com.alianza.clientes.model.Cliente;
import co.com.alianza.clientes.services.ClienteServices;
import co.com.alianza.clientes.services.impl.ClienteServicesImpl;

/**
 * TestClienteController.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestClienteController {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Mock
	ClienteServicesImpl clienteServicesImpl;

	@Mock
	private ClienteServices clienteServices;

	@InjectMocks
	private ClienteController clienteController;

	@Spy
	ObjectMapper mapper;

	private Cliente cliente;

	private List<Cliente> listCliente;

	private static final String PATH = "/api/clientes";

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
		this.cliente = new Cliente();
		this.cliente.setId(1L);
		this.cliente.setSharedKey("jgutierrez");
		this.cliente.setBussinessId("Juliana Gutierrez");
		this.cliente.setEmail("jgutierrez@gmail.com");
		this.cliente.setPhone(3219876543L);
		this.cliente.setStartDate(LocalDate.parse("2019-05-01"));
		this.cliente.setEndDate(LocalDate.parse("2019-06-30"));
		this.cliente.setCreationDate(LocalDate.parse("2019-05-20"));
		this.listCliente = new ArrayList<Cliente>();
		this.listCliente.add(this.cliente);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		Mockito.when(clienteServices.getClientes(this.cliente)).thenReturn(listCliente);
	}

	@Test
	@Order(1)
	void testGetAllCliente() throws Exception {
		final var mvcResult = this.mvc.perform(
				get(PATH).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		final var content = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(content.contains("jgutierrez"));
		Assert.assertTrue(content.contains("Juliana Gutierrez"));
		Assert.assertTrue(content.contains("jgutierrez@gmail.com"));
		Assert.assertTrue(content.contains("2019-05-01"));
	}

	@Test
	@Order(2)
	void testGetFilterCliente() throws Exception {
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		final var mvcResult = this.mvc
				.perform(post(PATH + "/advancesearch").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(this.mapper.writeValueAsString(this.cliente))
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		final var content = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(content.contains("jgutierrez"));
		Assert.assertTrue(content.contains("Juliana Gutierrez"));
		Assert.assertTrue(content.contains("jgutierrez@gmail.com"));
		Assert.assertTrue(content.contains("2019-05-01"));
	}

	@Test
	@Order(3)
	void testGetClienteBySharedKey() throws Exception {
		final var mvcResult = this.mvc.perform(get(PATH + "/sharedkey/jgutierrez")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	@Order(4)
	void testGetClienteBySharedKeyNotFound() throws Exception {
		final var mvcResult = this.mvc.perform(get(PATH + "/sharedkey/john")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
	}

	@Test
	@Order(5)
	void testGetClienteById() throws Exception {
		final var mvcResult = this.mvc.perform(get(PATH + "/id/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	@Order(6)
	void testGetClienteByIdNotFound() throws Exception {
		final var mvcResult = this.mvc.perform(get(PATH + "/id/0").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
	}

	@Test
	@Order(7)
	void testUpdateCliente() throws Exception {
		Cliente otroCliente = new Cliente("jortiz", "John Ortiz", "jortiz@gmail.com", 3219876543L,
				LocalDate.parse("2019-05-01"), LocalDate.parse("2019-06-30"), LocalDate.parse("2019-05-20"));
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		final var mvcResult = this.mvc
				.perform(put(PATH + "/7").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(this.mapper.writeValueAsString(otroCliente))
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		final var content = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(content.contains("jortiz"));
		Assert.assertTrue(content.contains("John Ortiz"));
		Assert.assertTrue(content.contains("jortiz@gmail.com"));
		Assert.assertTrue(content.contains("2019-05-01"));
		Assert.assertTrue(content.contains("2019-06-30"));
		Assert.assertTrue(content.contains("2019-05-20"));
	}

	@Test
	@Order(8)
	void testErrorUpdateCliente() throws Exception {
		Cliente otroCliente = new Cliente("jortiz", "John Ortiz", "jortiz@gmail.com", 3219876543L,
				LocalDate.parse("2019-05-01"), LocalDate.parse("2019-06-30"), LocalDate.parse("2019-05-20"));
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		final var mvcResult = this.mvc
				.perform(put(PATH + "/0").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(this.mapper.writeValueAsString(otroCliente))
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);

	}

	@Test
	@Order(9)
	void testDeleteCliente() throws Exception {
		final var mvcResult = this.mvc.perform(delete(PATH + "/9")).andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	@Order(10)
	void testGetClienteEmpty() throws Exception {
		this.mvc.perform(delete(PATH + "/9"));
		this.mvc.perform(delete(PATH + "/8"));
		this.mvc.perform(delete(PATH + "/7"));
		this.mvc.perform(delete(PATH + "/6"));
		this.mvc.perform(delete(PATH + "/5"));
		this.mvc.perform(delete(PATH + "/4"));
		this.mvc.perform(delete(PATH + "/3"));
		this.mvc.perform(delete(PATH + "/2"));
		this.mvc.perform(delete(PATH + "/1"));
		this.mapper.registerModule(new JavaTimeModule());
		final var mvcResult = this.mvc
				.perform(get(PATH).contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(this.mapper.writeValueAsString(this.cliente))
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(204, status);
	}

	@Test
	@Order(11)
	void testCreateCliente() throws Exception {
		Cliente otroCliente = new Cliente(1L, "jortiz", "John Ortiz", "jortiz@gmail.com", 3219876543L,
				LocalDate.parse("2019-05-01"), LocalDate.parse("2019-06-30"), LocalDate.parse("2019-05-20"));
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		final var mvcResult = this.mvc
				.perform(post(PATH).contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(this.mapper.writeValueAsString(otroCliente))
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		final var status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(201, status);
		final var content = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(content.contains("jortiz"));
		Assert.assertTrue(content.contains("John Ortiz"));
		Assert.assertTrue(content.contains("jortiz@gmail.com"));
		Assert.assertTrue(content.contains("2019-05-01"));
		Assert.assertTrue(content.contains("2019-06-30"));
	}

}
