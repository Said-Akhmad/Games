
public class LabirintRightHandRuleSkeleton {

    public static void main(String[] args) {

        char[][] labirint = new char[][]{
                {'*', '*', '0', '*', '0', '*', '*', '*', '*', '*'},
                {'*', '*', '0', '*', '0', '0', '0', '0', '*', '*'},
                {'*', '*', '0', '*', '*', '*', '*', '0', '*', '*'},
                {'*', '0', '0', '0', '0', '0', '*', '0', '*', '*'},
                {'*', '*', '0', '*', '*', '0', '*', '0', '*', '*'},
                {'*', '*', '0', '*', '0', '0', '0', '0', '*', '*'},
                {'*', '0', '0', '*', '*', '*', '*', '*', '*', '*'},
                {'*', '*', '0', '*', '*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}
        };

        int robotR = 0, robotC = 2, stepsPassed=0;
        char oriented = 'S';

        while (true) {

            int rightR = 0, rightC = 0, frontR = 0, frontC = 0;

            switch(oriented) {
                case 'S':
                    rightR = robotR;
                    rightC = robotC - 1;
                    frontR = robotR + 1;
                    frontC = robotC;
                    break;
                case 'N':
                    rightR = robotR;
                    rightC = robotC + 1;
                    frontR = robotR - 1;
                    frontC = robotC;
                    break;
                case 'W':
                    rightR = robotR - 1;
                    rightC = robotC;
                    frontR = robotR;
                    frontC = robotC - 1;
                    break;
                case 'E':
                    rightR = robotR + 1;
                    rightC = robotC;
                    frontR = robotR;
                    frontC = robotC + 1;
            }

            switch(labirint[robotR][robotC]) {
                case '0': labirint[robotR][robotC]='1'; break;
                case '1': labirint[robotR][robotC]='2'; break;
            }

            if (stepsPassed>0 /*..TODO..*/) {
                System.out.println("Here is the way out!");
                break;
            }

            printLabirint(labirint, robotR, robotC, rightR, rightC, frontR, frontC);

            if (labirint[rightR][rightC]=='0' || labirint[rightR][rightC]=='1' || labirint[rightR][rightC]=='2') {

                switch(oriented) {
                    case 'S': robotC--; oriented='W'; break;
                    case 'E': robotR++; oriented='S'; break;
                    case 'N': robotC++; oriented='E'; break;
                    case 'W': robotR--; oriented='N';
                }

            } else if (labirint[frontR][frontC]=='0' || labirint[frontR][frontC]=='1' || labirint[frontR][frontC]=='2') {

                //...

            } else if (labirint[frontR][frontC]=='*') {

                //...

            }

            stepsPassed++;

        }

    }

    private static void printLabirint(char[][] labirint, int robotR, int robotC, int rightR, int rightC, int frontR, int frontC) {

        int i=0, j=0;

        while (i < labirint.length) {
            while (j < labirint[0].length) {
                if (i == robotR && j == robotC) {
                    System.out.print('A');
                } else if (i == rightR && j == rightC) {
                    System.out.print('R');
                } else if (i == frontR && j == frontC) {
                    System.out.print('F');
                } else {
                    System.out.print(labirint[i][j]);
                }
                j++;
            }
            j=0;
            i++;
            System.out.println();
        }

        System.out.println();
    }

}
