package bankService;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class CheckingAccountTest {
    Customer customer;
    CheckingAccount checkingAccount;

    @BeforeClass
    public void oneTimeSetup(){
        customer = new Customer("Radwa Saleh", "Cairo", "hello@Disneyland.com");
    }

    @BeforeMethod
    public void eachTimeSetup(){
        checkingAccount = new CheckingAccount(customer, 300.00, 123456789);
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Check mockCheck;

    /**
     * Customers should be able to process a valid check on a checking account with sufficient funds
     * Scenario:
     * 1. Given a customer's checking account with an initial balance of $300.00
     * 2. When I process a check for $150.00
     * 3. Then the new account balance should be $150.00
     */
    @Test
    public void processCheckWithSufficientFunds_DecreaseBalanceByAmount(){
        //Given
        when(mockCheck.getAmount()).thenReturn(150.00);
        when(mockCheck.getCheckNumber()).thenReturn(4321);



        ;lknb
        //When
        checkingAccount.processCheck(mockCheck);

        //Then
        assertEquals(checkingAccount.getBalance(), 150);
    }
}