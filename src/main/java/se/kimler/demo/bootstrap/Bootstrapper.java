package se.kimler.demo.bootstrap;

import static se.kimler.demo.bootstrap.util.random.RandomUtil.getRandomInteger;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.kimler.demo.bootstrap.util.ssn.SwedishSocialSecurityNumberGenerator;
import se.kimler.demo.domain.Invoice;
import se.kimler.demo.domain.Invoice.Distribution;
import se.kimler.demo.domain.InvoiceDocument;
import se.kimler.demo.repository.InvoiceDocumentRepository;
import se.kimler.demo.repository.InvoiceRepository;

@Component
@RequiredArgsConstructor
public class Bootstrapper {

    private final InvoiceRepository         invoiceRepository;
    private final InvoiceDocumentRepository invoiceDocumentRepository;

    @PostConstruct
    public void bootstrap() {
        generateInvoice(Distribution.EMAIL);
        generateInvoice(Distribution.MAIL);
        generateInvoice(Distribution.WORD_OF_MOUTH);
    }

    private void generateInvoice(Distribution distribution) {
        int amountOfInvoices = getRandomInteger(10, 20);
        for (int i = 0; i < amountOfInvoices; i++) {
            String costCenter = getValueWithUUID("cost_center_%s");
            Date createdAt = generateCreatedAt();
            String fileName = getValueWithUUID("invoice_%s");
            String mailBoxOperator = getValueWithUUID("mail_box_operator_%s");
            String pid = new SwedishSocialSecurityNumberGenerator().generate();
            Invoice invoice = Invoice.builder()
                    .costCenter(costCenter)
                    .createdAt(createdAt)
                    .distribution(distribution)
                    .fileName(fileName)
                    .mailBoxOperator(mailBoxOperator)
                    .personalIdentifier(pid)
                    .build();
            List<InvoiceDocument> invoiceDocuments = generateInvoiceDocuments(invoice, createdAt);
            invoiceRepository.save(invoice);
            invoiceDocumentRepository.saveAll(invoiceDocuments);
        }
    }

    private static List<InvoiceDocument> generateInvoiceDocuments(Invoice invoice, Date createdAt) {
        List<InvoiceDocument> invoiceDocuments = new ArrayList<>();
        int amountOfDocuments = getRandomInteger(1, 5);
        for (int i = 0; i < amountOfDocuments; i++) {
            String fileName = getValueWithUUID("invoice_document_%s");
            InvoiceDocument invoiceDocument = InvoiceDocument.builder()
                    .createdAt(createdAt)
                    .fileName(fileName)
                    .invoice(invoice)
                    .build();
            invoiceDocuments.add(invoiceDocument);
        }
        return invoiceDocuments;
    }

    private static Date generateCreatedAt() {
        int daysBack = getRandomInteger(1, 365);
        Instant instant = Instant.now().minus(Period.ofDays(daysBack));
        return Date.from(instant);
    }

    private static String getValueWithUUID(String format) {
        return String.format(format, UUID.randomUUID().toString());
    }
}
