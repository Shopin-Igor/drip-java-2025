public class AtbashCipher {
    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();
        for (char letter : input.toCharArray()) {
            if (Character.isLetter(letter)) {
                char size = Character.isLowerCase(letter) ? 'a' : 'A';
                char mirrored = (char) (size + ('z' - Character.toLowerCase(letter)));
                result.append(Character.isLowerCase(letter) ? mirrored : Character.toUpperCase(mirrored));
            }
            else {
                result.append(letter);
            }
        }
        return result.toString();
    }
}
