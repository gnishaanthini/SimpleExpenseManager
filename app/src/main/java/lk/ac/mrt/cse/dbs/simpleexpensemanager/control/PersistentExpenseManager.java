package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantMemoryAccountDAO;

public class PersistentExpenseManager extends ExpenseManager{

    public PersistentExpenseManager(Context context) throws ExpenseManagerException {
        super(context);
        setup(context);
    }

    @Override
    public void setup(Context context) throws ExpenseManagerException {
        /*** Begin generating dummy data for In-Memory implementation ***/

        TransactionDAO PersistantMemoryTransactionDAO = new PersistantMemoryTransactionDAO(context);
        setTransactionsDAO(PersistantMemoryTransactionDAO);

        AccountDAO PersistantMemoryAccountDAO = new PersistantMemoryAccountDAO(context);
        setAccountsDAO(PersistantMemoryAccountDAO);

        // dummy data
       Account dummyAcct1 = new Account("23168A", "Yoda Bank", "Anakin Skywalker", 10000.0);
       Account dummyAcct2 = new Account("90979Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
       getAccountsDAO().addAccount(dummyAcct1);
       getAccountsDAO().addAccount(dummyAcct2);

        /*** End ***/
    }
}
