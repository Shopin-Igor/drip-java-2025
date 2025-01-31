class DigitNumberCounter {
  public static int countDigits(int number) {
    int answer = 0;
    while (number != 0) { 
      ++answer;
      number /= 10;
    }
    
    return answer;
  }
}