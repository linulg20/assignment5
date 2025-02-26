package org.example.Assignment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class SAP_BasedInvoiceSenderTest {

    @Test
    void testWhenLowInvoicesSent() {

        FilterInvoice mockFilter = mock(FilterInvoice.class); // mocking dependencies of filerinvoice and SAP
        SAP mockSAP = mock(SAP.class);

        List<Invoice> lowValuedInvoices = Arrays.asList( //making a new list to filter out the output
                new Invoice("Alice", 50),
                new Invoice("Bob", 75)
        );
        when(mockFilter.lowValueInvoices()).thenReturn(lowValuedInvoices); //stubbing the filter to return

        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSAP);
        sender.sendLowValuedInvoices(); //calling the method

        verify(mockSAP, times(lowValuedInvoices.size())).send(any(Invoice.class));
        // making sure that SAP'S method is called as many times as needed
    }

    @Test
    void testWhenNoInvoices() {
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSAP = mock(SAP.class);

        when(mockFilter.lowValueInvoices()).thenReturn(Collections.emptyList());

        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSAP);
        sender.sendLowValuedInvoices();

        verify(mockSAP, never()).send(any(Invoice.class));
    }
}