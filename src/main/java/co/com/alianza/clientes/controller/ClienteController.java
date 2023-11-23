package co.com.alianza.clientes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.alianza.clientes.model.Cliente;
import co.com.alianza.clientes.services.ClienteServices;
import lombok.extern.slf4j.Slf4j;

/**
 * ClienteController.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteServices clienteServices;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getClientes(@RequestBody(required = false) Cliente cliente) {
		Optional<List<Cliente>> clients = Optional.ofNullable(clienteServices.getClientes(cliente));
		if (!clients.isEmpty() && !clients.get().isEmpty()) {
			return new ResponseEntity<>(clients.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/clientes/{sharedkey}")
	public ResponseEntity<Cliente> getClienteBySharedKey(@PathVariable("sharedkey") String sharedkey) {
		Optional<Cliente> clientsData = Optional.ofNullable(clienteServices.getClienteBySharedKey(sharedkey));
		if (clientsData.isPresent()) {
			return new ResponseEntity<>(clientsData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		Optional<Cliente> resultCliente = Optional.ofNullable(clienteServices.createCliente(cliente));
		if (resultCliente.isPresent())
			return new ResponseEntity<>(resultCliente.get(), HttpStatus.CREATED);
		else {
			log.error("Exceptcion en Create Cliente No insertar: {}", cliente);
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {

		Optional<Cliente> clientData = clienteServices.updateCliente(id, cliente);
		if (clientData.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
		try {
			clienteServices.deleteCliente(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exceptcion en al borrar Cliente: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
