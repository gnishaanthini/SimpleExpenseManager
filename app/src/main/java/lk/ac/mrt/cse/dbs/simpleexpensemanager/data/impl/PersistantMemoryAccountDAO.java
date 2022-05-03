package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.DatabaseHelper;

public class PersistantMemoryAccountDAO implements AccountDAO {
    DatabaseHelper myDb;
    public PersistantMemoryAccountDAO(Context context) {
       myDb=new DatabaseHelper(context);
    }
    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumberList = new ArrayList<String>();
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select "+DatabaseHelper.COL_1+" from "+DatabaseHelper.TABLE_NAME_1,null);

        if (res.moveToFirst()) {
            do {
                String accountNo=res.getString(0);
                accountNumberList.add(accountNo);
            } while (res.moveToNext());
        }

        // return account list
        return accountNumberList;

    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME_1;

        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account=new Account();
                account.setAccountNo(cursor.getString(0));
                account.setBankName(cursor.getString(1));
                account.setAccountHolderName(cursor.getString(2));
                account.setBalance(cursor.getDouble(3));
                // Adding account to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }

        // return account list
        return accountList;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = myDb.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_1, new String[] { DatabaseHelper.COL_1,
                        DatabaseHelper.COL_2,  DatabaseHelper.COL_3, DatabaseHelper.COL_4 }, DatabaseHelper.COL_1 + "=?",
                new String[] { accountNo }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Account account=new Account(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3));

        return account;
    }


    @Override
    public void addAccount(Account account) {
        SQLiteDatabase db= myDb.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,account.getAccountNo());
        contentValues.put(DatabaseHelper.COL_2,account.getBankName());
        contentValues.put(DatabaseHelper.COL_3,account.getAccountHolderName());
        contentValues.put(DatabaseHelper.COL_4,account.getBalance());

        long result=db.insert(DatabaseHelper.TABLE_NAME_1,null,contentValues);

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = myDb.getWritableDatabase();
        int delete= db.delete(DatabaseHelper.TABLE_NAME_1, "accountNo = ?",new String[] {accountNo});
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,accountNo);
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_1, new String[] {  DatabaseHelper.COL_4 }, DatabaseHelper.COL_1 + "=?",
                new String[] { accountNo }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        double balance=0;
        balance = cursor.getDouble(0);
        switch (expenseType) {
            case EXPENSE:
                balance=balance-amount;
                contentValues.put(DatabaseHelper.COL_4,balance);
                break;
            case INCOME:
                balance=balance+amount;
                contentValues.put(DatabaseHelper.COL_4,balance);
                break;
        };
        db.update(DatabaseHelper.TABLE_NAME_1, contentValues, "accountNo = ?", new String[]{accountNo});
    }
}
