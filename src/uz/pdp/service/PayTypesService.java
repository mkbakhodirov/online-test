package uz.pdp.service;

import uz.pdp.model.Account;
import uz.pdp.model.PayType;
import uz.pdp.model.Subject;
import uz.pdp.model.Test;
import uz.pdp.service.base.BaseService;

import java.util.LinkedList;
import java.util.List;

public class PayTypesService implements BaseService<PayType, Account> {
    private List<PayType> payTypes;

    public PayTypesService() {
        payTypes = new LinkedList<>();
    }

    @Override
    public void add(PayType payType) {
        payTypes.add(payType);
    }

    @Override
    public PayType get(String str1, String str2) {
        return null;
    }

    @Override
    public PayType getByIndex(int index) {
        return payTypes.get(index);
    }

    @Override
    public PayType get(Account account) {
        return null;
    }

    @Override
    public boolean check(String name) {
        for (PayType payType : payTypes) {
            if (payType.getName().equals(name))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder str = new StringBuilder();
        for (PayType payType : payTypes) {
            str.append(++index);
            str.append('.');
            str.append(payType.getName());
            str.append("\t ");
        }
        return str.toString();
    }

    @Override
    public boolean isEmpty() {
        return payTypes.isEmpty();
    }
}
