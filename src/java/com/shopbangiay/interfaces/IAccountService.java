package com.shopbangiay.interfaces;

import com.shopbangiay.models.Account;
import java.util.List;

public interface IAccountService {

    List<Account> findAllAccounts();

    Account findById(int id);

    void deleteById(int id);

    Account save(Account t);

    Account update(Account t);
}
