package uz.pdp.service;

import uz.pdp.model.Account;
import uz.pdp.model.PayType;
import uz.pdp.model.base.BaseModel;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AccountsService implements BaseService<Account, PayType> {
    private List<Account> accounts;

    public AccountsService() {
        accounts = new LinkedList<>();
    }

    @Override
    public void add(Account account) {
        accounts.add(account);
    }

    @Override
    public Account get(PayType payType) {
        UUID payTypeId = payType.getId();
        for (Account account : accounts) {
            if (account.getPayTypeId().equals(payTypeId))
                return account;
        }
        return null;
    }

    @Override
    public Account get(String str1, String str2) {
        return null;
    }

    @Override
    public Account getByIndex(int index) {
        return null;
    }

    @Override
    public boolean check(String str1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return accounts.isEmpty();
    }
}
