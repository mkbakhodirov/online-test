package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class History extends BaseModel {
    private UUID userId;
    private String subjectName;
    private int ball;

    public History(UUID userId, String subjectName, int ball) {
        this.userId = userId;
        this.subjectName = subjectName;
        this.ball = ball;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
