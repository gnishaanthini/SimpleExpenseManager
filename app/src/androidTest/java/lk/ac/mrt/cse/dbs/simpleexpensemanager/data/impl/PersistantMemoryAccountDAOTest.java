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
        Account account1= new Account(accountNo,bankName,accountHolderName,balance);
        PersistantMemoryAccountDAO test1= new PersistantMemoryAccountDAO(ApplicationProvider.getApplicationContext());
        test1.addAccount(account1);
        Account output= test1.getAccount(accountNo);
        try {
            assertEquals(account1,output);
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
        Account account2= new Account(accountNo,bankName,accountHolderName,balance);
        PersistantMemoryAccountDAO test2= new PersistantMemoryAccountDAO(ApplicationProvider.getApplicationContext());
        test2.addAccount(account2);
        Account output= test2.getAccount(accountNo);
        try {
            assertEquals(account2,output);
        }
        catch (AssertionError e) {

        }
    }
}