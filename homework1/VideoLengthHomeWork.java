class VideoLengthHomeWork {
  public static int minutesToSeconds(String videoLenght) {
    String count_minutes = "";
    String count_seconds = "";
    int counter = 0;
    while (videoLenght.charAt(counter) != ':') {
      count_minutes += videoLenght.charAt(counter);
    }
    ++counter;
    while (counter != videoLenght.length()) {
      count_seconds += videoLenght.charAt(counter);
    }
    
    return (Integer.parseInt(count_minutes) * 60 + Integer.parseInt(count_seconds));
  }
}