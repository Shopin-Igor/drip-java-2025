package Task4;
public class PasswordValidator {
    public static boolean validatePassword(String password) {
        return password != null && password.matches(".*[~!@#$%^&*|].*");
    }
}