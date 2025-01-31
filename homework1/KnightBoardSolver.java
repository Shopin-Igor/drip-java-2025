class KnightBoardSolver {

    public static boolean knightBoardCapture(int[][] board) {
        int[] steps = {1, -1, -2, 2};
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (board[x][y] == 1) {
                    for (int step_x = 0; step_x < steps.length; ++step_x) {
                        for (int step_y = 0; step_y < steps.length; ++step_y) {
                            if ( (Math.abs(steps[step_x]) != Math.abs(steps[step_y])) && (0 <= steps[step_x] + x && steps[step_x] + x <= 7) && (0 <= steps[step_y] + y && steps[step_y] + y <= 7)) {
                                if (board[x + steps[step_x]][y + steps[step_y]] == 1) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}