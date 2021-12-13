package assignments.no12dataabstraction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class DataAbstractTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testDepositToString () {
        Date now = new Date();
        Deposit d = new Deposit(200, now, Customer.SAVING);
        assertEquals(String.format("Deposit of: $200.0 Date: %s into account: Saving", now), d.toString());
    }

    @Test
    public void testWithdrawToString () {
        Date now = new Date();
        Withdraw w = new Withdraw(200, now, Customer.CHECKING);
        assertEquals(String.format("Withdraw of: $200.0 Date: %s into account: Checking", now), w.toString());
    }

    @Test
    public void testDeposit () throws InterruptedException {
        System.out.println("Simulating...");

        Date[] dd = new Date[6];
        for (int i = 0; i < 6; i++) {
            dd[i] = new Date();
            Thread.sleep(1000);
        }

        Customer c = new Customer();
        double bc = 0, bs = 0;

        for (int i = 0; i < 3; i++)
            bc = c.deposit(200, dd[i], Customer.CHECKING);
        for (int i = 3; i < 6; i++)
            bs = c.deposit(100, dd[i], Customer.SAVING);

        c.displayDeposits();

        assertEquals(600, bc, 0);
        assertEquals(300, bs, 0);
    }

    @Test
    public void testWithdraw () throws InterruptedException {
        System.out.println("Simulating...");

        Date[] dd = new Date[6];
        for (int i = 0; i < 6; i++) {
            dd[i] = new Date();
            Thread.sleep(1000);
        }

        Customer c = new Customer("My account", 999, 0, 0);
        double bc = 0, bs = 0;

        for (int i = 0; i < 3; i++)
            c.deposit(200, dd[i], Customer.CHECKING);
        for (int i = 3; i < 6; i++)
            c.deposit(100, dd[i], Customer.SAVING);

        for (int i = 0; i < 3; i++)
            bc = c.withdraw(201, dd[i], Customer.CHECKING);
        for (int i = 3; i < 6; i++)
            bs = c.withdraw(200, dd[i], Customer.SAVING);

        c.displayWithdraws();

        assertEquals(-3, bc, 0);
        assertEquals(-101, bs, 0);
    }
}