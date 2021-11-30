package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

public class PayType extends BaseModel {
    private String name;

    public PayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
