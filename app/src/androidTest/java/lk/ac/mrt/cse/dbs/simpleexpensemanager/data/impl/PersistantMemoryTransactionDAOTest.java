package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import static org.junit.Assert.*;
import androidx.test.core.app.ApplicationProvider;

//import org.testng.annotations.Test;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistantMemoryTransactionDAOTest {

    @Test
    public void logTransaction() {
        Date date=new java.util.Date();
        String accountNo="23499Q";
        ExpenseType expenseType=ExpenseType.INCOME;
        Double amount=12000.0;
        Transaction transaction1=new Transaction(date,accountNo,expenseType,amount);
        PersistantMemoryTransactionDAO test3=new PersistantMemoryTransactionDAO(ApplicationProvider.getApplicationContext());
        test3.logTransaction(date,accountNo,expenseType,amount);
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList=test3.getAllTransactionLogs();
        try {
            assertTrue(transactionList.contains(transaction1));
        }
        catch (AssertionError e) {

        }

    }

}