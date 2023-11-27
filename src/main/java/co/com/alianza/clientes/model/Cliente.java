package co.com.alianza.clientes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Cliente.
 *
 * @author John Edilberto Ortiz Guarin.
 * @version 1.0.0, 20-11-2023
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cliente")
@SequenceGenerator(name="cliente_id_seq", initialValue = 10, allocationSize = 100)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
	private long id;

	@Column(name = "shared_key")
	private String sharedKey;

	@Column(name = "business_id")
	private String bussinessId;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private Long phone;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	public Cliente(String sharedKey, String bussinessId, String email, Long phone, LocalDate startDate, LocalDate endDate,
			LocalDate creationDate) {
		this.sharedKey = sharedKey;
		this.bussinessId = bussinessId;
		this.email = email;
		this.phone = phone;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
	}
}