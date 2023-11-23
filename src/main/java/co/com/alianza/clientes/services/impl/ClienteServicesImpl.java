package co.com.alianza.clientes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.alianza.clientes.model.Cliente;
import co.com.alianza.clientes.repository.ClienteRepository;
import co.com.alianza.clientes.services.ClienteServices;
import lombok.extern.slf4j.Slf4j;

/**
 * ClienteServicesImpl.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@Slf4j
@Service
public class ClienteServicesImpl implements ClienteServices {

	@Autowired
	ClienteRepository clientRepository;

	@Override
	public List<Cliente> getClientes(Cliente cliente) {
		List<Cliente> clients = new ArrayList<>();
		if (cliente == null)
			clientRepository.findAll().forEach(clients::add);
		else
			clientRepository.findByBussinessIdOrPhoneOrEmailOrStartDateOrEndDate(cliente.getBussinessId(),
					cliente.getPhone(), cliente.getEmail(), cliente.getStartDate(), cliente.getEndDate())
					.forEach(clients::add);
		return clients;
	}

	@Override
	public Cliente getClienteBySharedKey(String sharedkey) {
		return clientRepository.findBySharedKey(sharedkey);
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		Cliente resultCliente = clientRepository
				.save(new Cliente(cliente.getSharedKey(), cliente.getBussinessId(), cliente.getEmail(),
						cliente.getPhone(), cliente.getStartDate(), cliente.getEndDate(), cliente.getCreationDate()));
		log.info("insert client : {}", resultCliente);
		return resultCliente;
	}

	@Override
	public Optional<Cliente> updateCliente(long id, Cliente cliente) {
		Optional<Cliente> clientData = clientRepository.findById(id);
		if (clientData.isEmpty()) {
			log.info("No se pudo modificar cliente no se encontró el registro : {}", id);
			return clientData;
		}
		Cliente modifCliente = Cliente.builder().id(clientData.get().getId()).sharedKey(cliente.getSharedKey())
				.bussinessId(cliente.getBussinessId()).email(cliente.getEmail()).phone(cliente.getPhone())
				.startDate(cliente.getStartDate()).endDate(cliente.getEndDate()).creationDate(cliente.getCreationDate())
				.build();
		log.info("Se va a modificar el registro : {}", clientData.get().toString());
		return Optional.of(clientRepository.save(modifCliente));
	}

	@Override
	public void deleteCliente(long id) {
		clientRepository.deleteById(id);
		log.info("Se eliminó el registro : {}", id);

	}

}
