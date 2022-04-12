package se.kimler.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import se.kimler.demo.domain.InvoiceDocument;
import se.kimler.demo.domain.projections.InvoiceDocumentProjection;

@Repository
@RepositoryRestResource(path = "documents", collectionResourceRel = "documents", itemResourceRel = "document", excerptProjection = InvoiceDocumentProjection.class)
public interface InvoiceDocumentRepository extends PagingAndSortingRepository<InvoiceDocument, Integer> {

}
