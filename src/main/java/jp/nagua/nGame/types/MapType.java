package jp.nagua.nGame.types;

public class MapType {
    public static int NORMAL = 0;
    public static int SUMO = 1;
    public static int VANILLA = 2;
    public static int SPLEEF = 3;

    public static String getString(int num) {
        switch (num) {
            case 0:
                return "NORMAL";
            case 1:
                return "SUMO";
            case 2:
                return "VANILLA";
            case 3:
                return "SPLEEF";
            default:
                return "NOTFOUND";
        }
    }

    public static int getInteger(String type) {
        switch (type) {
            case "NORMAL":
                return 0;
            case "SUMO":
                return 1;
            case "VANILLA":
                return 2;
            case "SPLEEF":
                return 3;
            default:
                return -1;
        }
    }
}
