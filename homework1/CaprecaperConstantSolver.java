class CaprecaperConstantSolver {
    public static int max_number_from_this_numbers(int number) {
        String input_to_str = String.valueOf(number);
        int maximum = 0;
        int index_of_max_element = -1;
        String output = "";
        for (int i = 0; i < input_to_str.length(); i++) {
            for (int j = 0; j < input_to_str.length(); ++j) {
                if (input_to_str.charAt(j) - '0' > maximum) {
                    maximum = Math.max(maximum, input_to_str.charAt(j) - '0');
                    index_of_max_element = j;
                }
            }
            output += input_to_str.charAt(index_of_max_element);
            input_to_str = input_to_str.substring(0, index_of_max_element) + '-' + input_to_str.substring(index_of_max_element) ;
            maximum = -1;
        }
        return Integer.parseInt(output);
    }
    public static int min_number_from_this_numbers(int number) {
        String input_to_str = String.valueOf(number);
        int minimum = 10;
        int index_of_min_element = -1;
        String output = "";
        for (int i = 0; i < input_to_str.length(); ++i) {
            for (int j = 0; j < input_to_str.length(); ++j) {
                if (input_to_str.charAt(j) - '0' < minimum) {
                    minimum = Math.max(minimum, input_to_str.charAt(j) - '0');
                    index_of_min_element = j;
                }
            }
            output += input_to_str.charAt(index_of_min_element);
            input_to_str = input_to_str.substring(0, index_of_min_element) + '=' + input_to_str.substring(index_of_min_element) ;
            minimum = 10;
        }
        return Integer.parseInt(output);
    }
    public static int countK(int number) {
        if (number == 6174) {
            return 0;
        } else {
            return 1 + countK(max_number_from_this_numbers(number) - min_number_from_this_numbers(number));
        }
    }

}