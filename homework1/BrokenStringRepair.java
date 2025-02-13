class BrokenStringRepair {
    public static String fixString(String s) {
        String answer = "";
        for (int i = 0; i < s.length() / 2; ++i) {
            answer += s.charAt(2 * i + 1);
            answer += s.charAt(2 * i);
        }
        if (s.length() % 2 == 1) {
            answer += s.charAt(s.length() - 1);
        }
        return answer;
    }
}