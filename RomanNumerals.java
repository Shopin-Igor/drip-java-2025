public class RomanNumerals {
    public static String convertToRoman(int arabic_number) {
        if (arabic_number < 1 || arabic_number > 3999) {
            return "Invalid Arabic Number";
        }
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arabicValues.length; i++) {
            while (arabic_number >= arabicValues[i]) {
                arabic_number -= arabicValues[i];
                output.append(romanSymbols[i]);
            }
        }


        return output.toString();
    }
}
