package jp.nagua.npractice.types;

public enum QueueType {

    UNRANKED("Unranked"),
    RANKED("Ranked");

    private final String type;
    private final String var_type;

    private QueueType(String type) {
        this.type = type;
        this.var_type = type + "-";
    }

    public String getType() {
        return type;
    }

    public String getVarType() {
        return var_type;
    }

    @Override
    public String toString() {
        return getVarType();
    }
}
