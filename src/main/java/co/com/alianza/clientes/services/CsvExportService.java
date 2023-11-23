package co.com.alianza.clientes.services;

import java.io.Writer;

public interface CsvExportService {

	void writeClientesToCsv(Writer writer);

}