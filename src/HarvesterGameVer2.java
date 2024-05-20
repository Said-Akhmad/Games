import java.util.Scanner;

public class HarvesterGameVer2 {

  public static void main(String[] args) {

    int fieldCols = 50;
    int fieldRows = 20;

    int[][] boxes = getBoxes();
    int[][] walls = getWalls();
    int[][] exits = getExits();

    int robotPosCol = 34, robotPosRow = 3;

    Scanner scanner = new Scanner(System.in);

    while (true) {

      showLevel(fieldRows, fieldCols, robotPosRow, robotPosCol, boxes, walls, exits);

      System.out.println("Move your robot (move forward (w), back (s), left (a), right (d)): ");

      String yourMove = scanner.nextLine();

      if (isCanMakeThisMove(fieldRows, fieldCols, robotPosRow, robotPosCol, boxes, walls, exits, yourMove)) {

        robotPosCol = calculateNewPosCol(robotPosCol, yourMove);
        robotPosRow = calculateNewPosRow(robotPosRow, yourMove);

        if (isCoordinateInsideAnyObject(robotPosRow, robotPosCol, boxes)) {
          moveBox(robotPosRow, robotPosCol, boxes, yourMove);
        }

      } else {
        System.out.println("This move is not possible! Your move was: " + yourMove);
      }

      if (isWon(boxes, exits)) {
        System.out.println("Congradulations!");
      }

    }


  }

  private static boolean isWon(int[][] boxes, int[][] exits) {

    for (int i=0; i<boxes.length; i++) {
      if (!isCoordinateInsideAnyObject(boxes[i][0], boxes[i][1], exits)) {
        return false;
      }
    }

    return true;
  }

  private static void moveBox(int boxPosRow, int boxPosCol, int[][] boxes, String yourMove) {

    for (int i=0; i<boxes.length; i++) {

      if (isCoordinateInsideObject(boxPosRow, boxPosCol, boxes[i][0], boxes[i][1], boxes[i][2], boxes[i][3])) {

        int newBoxPosRow = calculateNewPosRow(boxes[i][0], yourMove);
        int newBoxPosCol = calculateNewPosCol(boxes[i][1], yourMove);

        if (isCoordinateInsideAnyObject(newBoxPosRow, newBoxPosCol, boxes)) {
          moveBox(newBoxPosRow, newBoxPosCol, boxes, yourMove);
        }

        boxes[i][0] = newBoxPosRow;
        boxes[i][1] = newBoxPosCol;

      }
    }

  }

  private static boolean isCanMakeThisMove(int fieldRows, int fieldCols, int robotPosRow,
      int robotPosCol, int[][] boxes, int[][] walls, int[][] exits, String yourMove) {

    final int newRobotPosRow = calculateNewPosRow(robotPosRow, yourMove);
    final int newRobotPosCol = calculateNewPosCol(robotPosCol, yourMove);

    if (isCoordinateInsideAnyObject(newRobotPosRow, newRobotPosCol, walls) ||
        isBorder(newRobotPosRow, newRobotPosCol, fieldRows, fieldCols)) {
      return false;
    } else if (isCoordinateInsideAnyObject(newRobotPosRow, newRobotPosCol, boxes)) {
      return isCanMakeThisMove(fieldRows, fieldCols, newRobotPosRow, newRobotPosCol, boxes, walls, exits, yourMove);
    } else {
      return true;
    }

  }

  private static boolean isBorder(int r, int c, int fieldRows, int fieldCols) {
    return r == fieldRows - 1 || c == fieldCols - 1 || r == 0 || c == 0;
  }

  private static void showLevel(int fieldRows, int fieldCols, int robotPosRow, int robotPosCol, int[][] boxes, int[][] walls, int[][] exits) {

    for (int r=0; r<fieldRows; r++) {
      for (int c=0; c<fieldCols; c++) {
        if (isCoordinateInsideAnyObject(r,c,exits) && isCoordinateInsideAnyObject(r,c,boxes)) {
          System.out.print('N');
        } else if (isCoordinateInsideAnyObject(r,c,boxes)) {
          System.out.print('n');
        } else if (r == robotPosRow && c == robotPosCol) {
          System.out.print('M');
        } else if (isCoordinateInsideAnyObject(r,c,walls)) {
          System.out.print('x');
        } else if (isBorder(r,c,fieldRows,fieldCols)) {
          System.out.print('+');
        } else if (isCoordinateInsideAnyObject(r,c,exits)) {
          System.out.print('0');
        } else {
          System.out.print(' ');
        }
      }
      System.out.println();
    }

  }

  private static boolean isCoordinateInsideAnyObject(int r, int c, int[][] boxes) {

    for (int i=0; i<boxes.length; i++) {
      if (isCoordinateInsideObject(r,c,boxes[i][0],boxes[i][1],boxes[i][2],boxes[i][3])) {
        return true;
      }
    }

    return false;
  }

  private static boolean isCoordinateInsideObject(int r, int c, int r1, int c1, int height, int width) {

    return r >= r1 && r <= (r1+height-1) && c >= c1 && c <= (c1+width-1);
  }


  private static int[][] getExits() {
    return new int[][]{{3,3,3,3}};
  }

  private static int[][] getWalls() {
    return new int[][]{{10,10,2,2}, {14,14,5,1}};
  }

  private static int[][] getBoxes() {
    return new int[][]{{6,6,1,1}, {6,8,1,1}};
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

}
