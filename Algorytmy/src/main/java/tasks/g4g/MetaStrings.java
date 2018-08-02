package tasks.g4g;

public class MetaStrings {
    public static boolean areMetaStrings(String str1, String str2) {
        int outOfPlace1 = -1;
        int outOfPlace2 = -1;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (outOfPlace1 < 0) {
                    outOfPlace1 = i;
                } else if (outOfPlace2 < 0) {
                    outOfPlace2 = i;
                } else {
                    return false;
                }
            }
        }
        return str1.charAt(outOfPlace1) == str2.charAt(outOfPlace2) &&
                str1.charAt(outOfPlace2) == str2.charAt(outOfPlace1);
    }

}
