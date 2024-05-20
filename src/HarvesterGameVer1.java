import java.util.Scanner;

public class HarvesterGameVer1 {

  public static void main(String[] args) {

    char[][] level = getLevel();

    int robotPosCol = 34, robotPosRow = 3;

    Scanner scanner = new Scanner(System.in);

    while (true) {

      showLevel(level, robotPosCol, robotPosRow);

      System.out.println("Move your robot (move forward (w), back (s), left (a), right (d)): ");

      String yourMove = scanner.nextLine();

      if (isCanMakeThisMove(robotPosCol, robotPosRow, yourMove, level)) {

        robotPosCol = calculateNewPosCol(robotPosCol, yourMove);
        robotPosRow = calculateNewPosRow(robotPosRow, yourMove);

        if (isBox(robotPosCol, robotPosRow, level) || isBoxOnExit(robotPosCol, robotPosRow,
            level)) {
          moveBox(robotPosCol, robotPosRow, yourMove, level);
        }

      } else {
        System.out.println("This move is not possible! Your move was: " + yourMove);
      }

      if (isWon(level)) {
        System.out.println("Congradulations!");
      }

    }


  }

  private static boolean isWon(char[][] level) {

    for (int i = 0; i < level.length; i++) {
      for (int j = 0; j < level[0].length; j++) {
        if (level[i][j] == 'n') { //if even one lower case 'n' is found than game is not over or not won
          return false;
        }
      }
    }

    return true;
  }

  private static char[][] getLevel() {
    return new char[][]

        {{'+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+',
            '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+',
            '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+'},

            {'+', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', 'x', 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+'},

            {'+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', '0', '0', '0', '0', '0', '0', '0', '0', '+'},

            {'+', ' ', ' ', ' ', ' ', ' ', 'n', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', '0', '0', '0', '0', '0', '0', '0', '0', '+'},

            {'+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', '0', '0', '0', '0', '0', '0', '0', '0', '+'},

            {'+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ', '0', '0', '0', '0', '0', '0', '0', '0', '+'},

            {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+',
                '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+',
                '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
  }

  private static void showLevel(char[][] level, int robotPosCol, int robotPosRow) {
    int r = 0, c = 0;
    while (r < level.length) {
      while (c < level[0].length) {

        if (robotPosCol == c && robotPosRow == r) {
          System.out.print("M");
        } else {
          System.out.print(level[r][c]);
        }

        c++;
      }
      System.out.println();
      c = 0;
      r++;
    }
  }

  private static int calculateNewPosRow(int robotPosRow, String yourMove) {

    switch (yourMove) {
      case "w":
        return robotPosRow - 1;
      case "s":
        return robotPosRow + 1;
      default:
        return robotPosRow;
    }
  }

  private static int calculateNewPosCol(int robotPosCol, String yourMove) {

    switch (yourMove) {
      case "a":
        return robotPosCol - 1;
      case "d":
        return robotPosCol + 1;
      default:
        return robotPosCol;
    }
  }

  private static boolean isCanMakeThisMove(int robotPosCol, int robotPosRow, String yourMove,
      char[][] level) {

    final int newPosCol = calculateNewPosCol(robotPosCol, yourMove);
    final int newPosRow = calculateNewPosRow(robotPosRow, yourMove);

    if (isBox(newPosCol, newPosRow, level) || isBoxOnExit(newPosCol, newPosRow, level)) {
      return isBoxMovable(newPosCol, newPosRow, yourMove, level);
    } else if (!isWall(newPosCol, newPosRow, level) && !isBorder(newPosCol, newPosRow, level)) {
      return true;
    }

    return false;
  }

  private static boolean isWall(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == 'x';
  }

  private static boolean isBorder(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == '+';
  }

  private static boolean isBox(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == 'n';
  }

  private static boolean isFreeExit(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == '0';
  }

  private static boolean isBoxOnExit(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == 'N';
  }

  private static boolean isEmpty(int posCol, int posRow, char[][] level) {
    return level[posRow][posCol] == ' ';
  }

  private static void moveBox(int boxCol, int boxRow, String yourMove, char[][] level) {

    int newBoxPosRow = calculateNewPosRow(boxRow, yourMove);
    int newBoxPosCol = calculateNewPosCol(boxCol, yourMove);

    if (isBox(newBoxPosCol, newBoxPosRow, level) || isBoxOnExit(newBoxPosCol, newBoxPosRow,
        level)) {
      moveBox(newBoxPosCol, newBoxPosRow, yourMove, level);
    }

    if (isFreeExit(newBoxPosCol, newBoxPosRow, level)) {
      level[newBoxPosRow][newBoxPosCol] = 'N';
    } else {
      level[newBoxPosRow][newBoxPosCol] = 'n';
    }

    if (isBox(boxCol, boxRow, level)) {
      level[boxRow][boxCol] = ' ';
    } else if (isBoxOnExit(boxCol, boxRow, level)) {
      level[boxRow][boxCol] = '0';
    }

  }

  private static boolean isBoxMovable(int boxPosCol, int boxPosRow, String yourMove,
      char[][] level) {

    int newBoxPosRow = calculateNewPosRow(boxPosRow, yourMove);
    int newBoxPosCol = calculateNewPosCol(boxPosCol, yourMove);

    if (isEmpty(newBoxPosCol, newBoxPosRow, level) || isFreeExit(newBoxPosCol, newBoxPosRow,
        level)) {
      return true;
    } else if (isBox(newBoxPosCol, newBoxPosRow, level) || isBoxOnExit(newBoxPosCol, newBoxPosRow,
        level)) {
      return isBoxMovable(newBoxPosCol, newBoxPosRow, yourMove, level);
    } else {
      return false;
    }

  }

}


/*

+++++++++++++++++++++++++++++++++++++++++++++++
+   x                    x                    +
+   x                    xxxxx                +
+   xxxxxxxxx       xxxxxx         E          +
+   x       x       x                         +
+   x       x       x       nnn               +
+   x       x       x       nnn               +
+           x       x                 00000000+
+    nn                               00000000+
+    nn     n            nn           00000000+
+                                     00000000+
+++++++++++++++++++++++++++++++++++++++++++++++


*/
