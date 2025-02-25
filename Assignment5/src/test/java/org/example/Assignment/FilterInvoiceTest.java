package org.example.Assignment;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterInvoiceTest {

    @Test
    void filterInvoiceTest() {
        Database db = new Database();
        QueryInvoicesDAO dao = new QueryInvoicesDAO(db);
        FilterInvoice filterInvoice = new FilterInvoice(dao);

        List<Invoice> result = filterInvoice.lowValueInvoices();

        assertTrue(result.stream().allMatch(invoice -> invoice.getValue() < 100));
    }

    @Test
    void filterInvoiceStubbedTest() {
        QueryInvoicesDAO daoStub = mock(QueryInvoicesDAO.class);

        List<Invoice> fakeInvoices = Arrays.asList(
                new Invoice("Alice", 50),
                new Invoice("Bob", 200),
                new Invoice("Charlie", 75)
        );
        when(daoStub.all()).thenReturn(fakeInvoices);

        FilterInvoice filterInvoice = new FilterInvoice(daoStub);
        List<Invoice> result = filterInvoice.lowValueInvoices();

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(invoice -> invoice.getValue() < 100));
    }
}
