package se.kimler.demo.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import se.kimler.demo.domain.Invoice;
import se.kimler.demo.domain.Invoice.Distribution;

@Repository
@RepositoryRestResource
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Integer> {

    Page<Invoice> findByCreatedAtBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Pageable pageable);
    
    Page<Invoice> findByDistribution(Distribution distribution, Pageable pageable);
    
    Page<Invoice> findByPersonalIdentifierAndDistribution(String pid, Distribution distribution, Pageable pageable);
}
