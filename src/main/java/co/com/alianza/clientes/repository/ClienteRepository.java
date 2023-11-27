package co.com.alianza.clientes.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.alianza.clientes.model.Cliente;

/**
 * ClienteRepository.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findBySharedKey(String sharedKey);

	List<Cliente> findByBussinessIdContainingIgnoreCaseOrPhoneOrEmailContainingIgnoreCaseOrStartDateOrEndDate(String bussinessId, Long phone, String email,
			LocalDate startDate, LocalDate endDate);

}
