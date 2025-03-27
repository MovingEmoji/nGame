package jp.nagua.nGame.elements;

public class KitFlag {
    public static int NORMAL = 0;
    public static int FAST_HIT = 1;
    public static int SUMO = 2;
    public static int NO_DELAY = 3;
    public static int UHC = 4;
    public static int SUMO_DELAY = 5;
    public static int HALF_DELAY = 6;

    public static String getString(int num) {
        switch (num) {
            case 0:
                return "NORMAL";
            case 1:
                return "FASTHIT";
            case 2:
                return "SUMO";
            case 3:
                return "NODELAY";
            case 4:
                return "UHC";
            case 5:
                return "SUMODELAY";
            case 6:
                return "HALFDELAY";
            default:
                return "NOTFOUND";
        }
    }

}
