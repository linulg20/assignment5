package org.example.Assignment;
import java.util.Objects;


public class Invoice {
    private final String customer;  //immutable value
    private final int value;  //immutable value

    //initializing the customer and value
    public Invoice(String customer, int value) {
        this.customer = customer;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  //condition to ensure of two instances are the same
        if (o == null || getClass() != o.getClass()) return false;  // checks if its null or a different class
        Invoice invoice = (Invoice) o;  //converting the object to an invoice
        return value == invoice.value &&  //checking the value if its the same
                customer.equals(invoice.customer);  // and the same thing for the customer
    }


    @Override
    public int hashCode() {
        return Objects.hash(customer, value);
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "customer='" + customer + '\'' +  //adding the customer name
                ", value=" + value +  // adding the value
                '}';
    }

    // Getter method for the customer and value
    public String getCustomer() {
        return customer;
    }


    public int getValue() {
        return value;
    }
}
