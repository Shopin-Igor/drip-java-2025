class CycleBitRotator {
    public static int rotateRight(int number, int shift) {
        String input_to_string = Integer.toString(number);
        shift = shift % input_to_string.length();
        input_to_string = input_to_string.substring(input_to_string.length() - shift) + input_to_string.substring(0, input_to_string.length() - shift);
        return Integer.parseInt(input_to_string);
    }

    public static int rotateLeft(int number, int shift) {
        String input_to_string = Integer.toString(number);
        shift = shift % input_to_string.length();
        input_to_string = input_to_string.substring(shift) + input_to_string.substring(0, shift);
        return Integer.parseInt(input_to_string);
    }
}