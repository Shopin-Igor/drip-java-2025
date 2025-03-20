package Task6;
public class BinaryStringValidator {
    public static boolean validateThirdSymbol(String input) {
        return input != null && input.matches("[01]{2}0.*");
    }

    public static boolean validateStartEnd(String input) {
        return input != null && input.matches("([01]).*\\1");
    }

    public static boolean validateLength(String input) {
        return input != null && input.matches("[01]{1,3}");
    }
}