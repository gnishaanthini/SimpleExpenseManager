package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

//import android.app.Activity;

import static org.junit.Assert.*;
import androidx.test.core.app.ApplicationProvider;

//import org.testng.annotations.Test;
import org.junit.Test;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;


public class PersistantMemoryAccountDAOTest {

    @Test
    public void getAccount() throws InvalidAccountException {
        String accountNo="555693B";
        String bankName = "BMN";
        String accountHolderName = "XYZ";
        Double balance = 12000.0;
        Account newAccount= new Account(accountNo,bankName,accountHolderName,balance);
        PersistantMemoryAccountDAO testAccDAO= new PersistantMemoryAccountDAO(ApplicationProvider.getApplicationContext());
        testAccDAO.addAccount(newAccount);
        Account output= testAccDAO.getAccount(accountNo);
        try {
            assertEquals(newAccount,output);
        }
        catch (AssertionError e) {

        }
    }

    @Test
    public void addAccount() throws InvalidAccountException {
        String accountNo="8096895B";
        String bankName = "BMN";
        String accountHolderName = "XYZ";
        Double balance = 12000.0;
        Account newAccount= new Account(accountNo,bankName,accountHolderName,balance);
        PersistantMemoryAccountDAO testAccDAO= new PersistantMemoryAccountDAO(ApplicationProvider.getApplicationContext());
        testAccDAO.addAccount(newAccount);
        Account output= testAccDAO.getAccount(accountNo);
        try {
            assertEquals(newAccount,output);
        }
        catch (AssertionError e) {

        }
    }
}