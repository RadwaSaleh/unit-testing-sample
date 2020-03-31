package bankService;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomerTest {

    /**
     * The system should be able to store information on a new customer using valid data.
     * Scenario:
     * 1. Given I create a new customer named Radwa Saleh
     * 2. When I initialize the customer object with valid details
     * 3. Then Radwa's information should be stored in the system
     */
    @Test
    public void createCustomerWithValidData_StoreSpecifiedData(){
        String customerName = "Radwa Saleh";
        String customerAddress = "Cairo";
        String customerEmail = "hello@Disneyland.com";

        //Given and When
        Customer customer = new Customer(customerName, customerAddress, customerEmail);

        //Then
        assertNotNull(customer);
        assertEquals(customer.getName(), customerName);
        assertEquals(customer.getAddress(), customerAddress);
        assertEquals(customer.getEmail(), customerEmail);

    }

    /**
     * The system should be able to update customer information using valid data
     * Scenario:
     * 1. Given I create a new customer named Radwa Saleh.
     * 2. When I update Radwa's customer information with Sohir's information.
     * 3. Then Sohir's information should be stored in the system
     */
    @Test
    public void updateCustomerWithValidData_StoreNewData(){
        String customerName = "Radwa Saleh";
        String customerAddress = "Cairo";
        String customerEmail = "hello@Disneyland.com";

        //Given
        Customer customer = new Customer(customerName, customerAddress, customerEmail);

        //When
        customer.updateName("Sohir");
        customer.updateAddress("Giza");
        customer.updateEmail("Sohir@Disneyland.com");

        //Then
        assertEquals(customer.getName(), "Sohir");
        assertEquals(customer.getAddress(), "Giza");
        assertEquals(customer.getEmail(), "Sohir@Disneyland.com");
    }
}
