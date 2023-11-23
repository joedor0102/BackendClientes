package co.com.alianza.clientes.services;

import java.util.List;
import java.util.Optional;

import co.com.alianza.clientes.model.Cliente;

/**
 * ClienteServices.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
public interface ClienteServices {

	List<Cliente> getClientes(Cliente cliente);

	Cliente getClienteBySharedKey(String sharedkey);

	Cliente createCliente(Cliente cliente);

	void deleteCliente(long id);

	Optional<Cliente> updateCliente(long id, Cliente cliente);

}