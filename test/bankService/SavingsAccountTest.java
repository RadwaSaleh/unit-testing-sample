package bankService;

import bankService.exceptions.InsufficientFundsException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class SavingsAccountTest {

    private Customer customer;
    SavingsAccount savingsAccount;

    @BeforeClass
    public void oneTimeSetup(){
        customer = new Customer("Radwa Saleh", "Cairo", "hello@Disneyland.com");
    }

    @BeforeMethod
    public void eachTimeSetup(){
        savingsAccount = new SavingsAccount(customer, 100.00, 123456789);
    }

    /**
     * Customers should be able to withdraw from their savings account.
     * Scenario:
     * 1. Given a customer's savings account with an initial balance of $100.00
     * 2. When I withdraw $60.00 from the account
     * 3. Then the new account balance is $40.00
     */
    @Test(dataProvider = "ValidWithdrawDataProvider")
    public void withdrawValidAmountFromSavingsAccount_DecreasesBalanceByAmount(double amount, double expectedBalance) throws InsufficientFundsException {
        //When
        savingsAccount.withdraw(amount);

        //Then
        assertEquals(savingsAccount.getBalance(), expectedBalance);
    }

    @DataProvider(name= "ValidWithdrawDataProvider")
    private Object[][] createValidWithdrawData() {
        return new Object[][]{
                {60.0, 40.0},
                {100.00, 0.0},
                {0.37, 99.63}};
    }

    /**
     *Customers should not be able to withdraw more than their available savings balance
     * 1. Given a customer's savings account with an initial balance of $100.00
     * 2. When I withdraw $200.00 from the account
     * 3. Then an exception should occur indicating that there are insufficient funds in the account
     * 4. And the account balance should remain unchanged
     */
    @Test
    public void withdrawAmountGreaterThanBalanceFromSavingsAccount_Throws_InsufficientFundsException() throws InsufficientFundsException {
        //When
        try{
            savingsAccount.withdraw(200.00);
            fail("Expected Insufficient Funds Exception but none was thrown");
        } catch (InsufficientFundsException e){
            //Then
            assertEquals(savingsAccount.getBalance(), 100.00);
        }



    }


}