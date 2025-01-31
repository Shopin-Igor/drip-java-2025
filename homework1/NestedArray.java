class NestedArray {
  public static boolean isNestable(int[] a1, int[] a2) {
    int min_a1 = 0;
    int max_a1 = 0;
    for (int i = 0; i < a1.length; ++i) {
      min_a1 = Math.min(min_a1, a1[i]);
      max_a1 = Math.max(max_a1, a1[i]);
    }
    int min_a2 = 0;
    int max_a2 = 0;
    for (int i = 0; i < a1.length; ++i) {
      min_a2 = Math.min(min_a2, a2[i]);
      max_a2 = Math.max(max_a2, a2[i]);
    }

    return (min_a1 > min_a2 && max_a1 < max_a2) ? (true) : (false);
  }
}