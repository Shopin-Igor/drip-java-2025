class PalindromSolver {
    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; ++i) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i) ) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPalindromeDescendant(int number) {
        int surrent_number = number;
        if (isPalindrome(String.valueOf(number))) {
            return true;
        }
        while (surrent_number >= 100) {
            String surrent_string = "";
            String surrent_number_to_string = String.valueOf(surrent_number);
            for (int i = 0; i <= surrent_number_to_string.length() - 2; i += 2) {
                surrent_string += String.valueOf((surrent_number_to_string.charAt(i) - '0') + (surrent_number_to_string.charAt(i + 1) - '0'));
            }
            if (surrent_number_to_string.length() % 2 == 1) {
                surrent_string += surrent_number_to_string.charAt(surrent_number_to_string.length() - 1);
            }

            if (isPalindrome(surrent_string) && (!surrent_string.isEmpty())) {
                return true;
            }
            surrent_number = Integer.parseInt(surrent_string);
        }
        return false;
    }
}