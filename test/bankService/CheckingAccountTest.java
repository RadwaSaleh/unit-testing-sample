package bankService;

import notificationService.EmailService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
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

        //When
        checkingAccount.processCheck(mockCheck);

        //Then
        assertEquals(checkingAccount.getBalance(), 150);
    }

    @Spy
    EmailService spiedEmailService = new EmailService();

    /**
     * Customers should be able to receive notifications that a check has been processed
     * Scenario:
     * 1. Given I enable E-mail notifications on the checking account
     * 2. And I have a valid check #4321 for amount $100.00
     * 3. When I process check #4321
     * 4. Then the transaction notification should be sent ia e-mail
     * 5. And the account balance should be $150.00
     */
    @Test
    public void processingCheckWithNotificationsEnabled_DecreaseBalanceAndSendMessage(){
        //Given
        checkingAccount.enableNotifications(spiedEmailService);
        when(mockCheck.getAmount()).thenReturn(100.00);
        when(mockCheck.getCheckNumber()).thenReturn(4321);

        //When
        checkingAccount.processCheck(mockCheck);

        //Then
        verify(spiedEmailService).notify(contains("Check #4321"), eq(checkingAccount.getOwner().getEmail()));
        assertEquals(checkingAccount.getBalance(), 200);
    }
}