import java.util.Scanner;

/*
+++++++++++++++++++++++++++++++++++++++++++++++
+   x                    x                    +
+   x                    xxxxx                +
+   xxxxxxxxx       xxxxxx         M          +
+   x       x       x                         +
+   x       x       x       n                 +
+   x       x       x                         +
+           x       x                 00000000+
+     n                               00000000+
+           n             n           00000000+
+                                     00000000+
+++++++++++++++++++++++++++++++++++++++++++++++
Gamer can make moves: a(left), d(right), w(up), s(down).
'n's are boxes not on exit
'N's are boxes placed on exist
'0's are exits
M is a Gamer
x - are walls
+ - are borders
Game is won when all 'n's are on '0's

i.e.:

+++++++++++++++++++++++++++++++++++++++++++++++
+   x                    x                    +
+   x                    xxxxx                +
+   xxxxxxxxx       xxxxxx         M          +
+   x       x       x                         +
+   x       x       x                         +
+   x       x       x                         +
+           x       x                 00000000+
+                                     0N000N00+
+                                     000N0000+
+                                     0N000000+
+++++++++++++++++++++++++++++++++++++++++++++++
*/

public class HarvesterGameVer1Skeleton {

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

        if (isBox(robotPosCol, robotPosRow, level) || isBoxOnExit(robotPosCol, robotPosRow, level)) {
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

    /*TODO*/
    return false;
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

    /*TODO*/

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

    /*TODO*/

  }

}
