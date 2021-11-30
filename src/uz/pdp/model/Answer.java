package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class Answer extends BaseModel {
    private String character;

    public Answer(String character) {
        this.character = character;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
