package tasks.hackerrank;

public class SuperReducedString {
    static String superReducedString(String s) {
        if (s.length() == 0) {
            return "Empty String";
        }
        char prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == prev) {
                StringBuilder reduced = new StringBuilder();
                if (i > 1) {
                    reduced.append(s.substring(0, i - 1));
                }
                if (i < s.length() - 1) {
                    reduced.append(s.substring(i + 1));
                }
                return superReducedString(reduced.toString());
            } else {
                prev = c;
            }
        }
        return s;
    }
}
