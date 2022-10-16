package helper.enums;

public enum Level {
    EASY(.2f),
    MEDIUM(.5f),
    HARD(.75f);
    private final float value;
    Level(float val){this.value = val;}
    public float getValue(){return value;}
}

