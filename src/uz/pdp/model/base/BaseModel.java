package uz.pdp.model.base;

import java.util.UUID;

public abstract class BaseModel {
    private final UUID id;
    private boolean isActive = true;

    {
        id = UUID.randomUUID();
    }

    public BaseModel() {}

    public UUID getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
