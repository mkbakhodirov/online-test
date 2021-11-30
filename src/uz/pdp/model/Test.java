package uz.pdp.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Test extends Subject {
    private UUID subjectId;
    private UUID answerId;
    private int ball;
    private Map<Character, String> variants;

    public Test(String name, Map<Character, String> variants, UUID subjectId, UUID answerId, int ball) {
        super(name);
        this.subjectId = subjectId;
        this.answerId = answerId;
        this.ball = ball;
        this.variants = variants;
    }

    public Map<Character, String> getVariants() {
        return variants;
    }

    public void setVariants(Map<Character, String> variants) {
        this.variants = variants;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public void setAnswerId(UUID answerId) {
        this.answerId = answerId;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }
}
