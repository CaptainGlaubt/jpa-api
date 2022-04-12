package se.kimler.demo.domain.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import se.kimler.demo.domain.InvoiceDocument;

@Projection(name = "projIdFilename", types = InvoiceDocument.class)
public interface InvoiceDocumentProjection {

    @Value("#{target.id}")
    Integer getId();
    
    String getFileName();
}
