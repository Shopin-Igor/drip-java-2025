class VideoLengthHomeWork {
  public static int minutesToSeconds(String videoLenght) {
    String[] seconds_and_minutes = videoLenght.split(":");
    int seconds = Integer.parseInt(seconds_and_minutes[1]);
    int minutes = Integer.parseInt(seconds_and_minutes[0]);
    if (seconds >= 60 ) {
      return -1;
    }
    return ((minutes * 60) + seconds);
  }
}