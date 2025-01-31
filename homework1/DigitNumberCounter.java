class DigitNumberCounter {
  public static int countDigits(int number) {
    int answer = 0;
    if (number == 0) {
      return 1;
    }
    while (number != 0) { 
      ++answer;
      number /= 10;
    }
    
    return answer;
  }
}