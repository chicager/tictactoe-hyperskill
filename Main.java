import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "         ";
        char[] moves = str.toCharArray();

        printField(moves);

        int[] coordinates = new int[2];
        boolean isFirstPlayer = true;

        while (!isGameFinished(moves)) {
            getCoordinates(coordinates);
            int position = matchCoordinates(coordinates);
            if (isOccupied(moves, position)) {
                continue;
            }
            putElement(moves, coordinates, position, isFirstPlayer);
            isFirstPlayer = !isFirstPlayer;
            printField(moves);
        }
    }

    //Print field
    static void printField(char[] arr) {
        System.out.println("---------");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("| %c", arr[i]);
            i++;
            System.out.printf(" %c", arr[i]);
            i++;
            System.out.printf(" %c |\n", arr[i]);
        }
        System.out.println("---------");
    }

    //Program State
    static boolean isGameFinished(char[] arr) {
        if (isImpossible(arr)) {
            System.out.println("Impossible");
            return true;
        } else if (isXWins(arr)) {
            System.out.println("X wins");
            return true;
        } else if (isOWins(arr)) {
            System.out.println("O wins");
            return true;
        } else if (isDraw(arr)) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    static boolean isXWins(char[] arr) {

        if ((arr[0] == 'X' && arr[1] == 'X' && arr[2] == 'X') || (arr[3] == 'X' && arr[4] == 'X' && arr[5] == 'X') ||
                (arr[6] == 'X' && arr[7] == 'X' && arr[8] == 'X') || (arr[0] == 'X' && arr[4] == 'X' && arr[8] == 'X') ||
                (arr[2] == 'X' && arr[4] == 'X' && arr[6] == 'X') || (arr[0] == 'X' && arr[3] == 'X' && arr[6] == 'X') ||
                (arr[1] == 'X' && arr[4] == 'X' && arr[7] == 'X') || (arr[2] == 'X' && arr[5] == 'X' && arr[8] == 'X')) {
            return true;
        }
        return false;
    }

    static boolean isOWins(char[] arr) {

        if ((arr[0] == 'O' && arr[1] == 'O' && arr[2] == 'O') || (arr[3] == 'O' && arr[4] == 'O' && arr[5] == 'O') ||
                (arr[6] == 'O' && arr[7] == 'O' && arr[8] == 'O') || (arr[0] == 'O' && arr[4] == 'O' && arr[8] == 'O') ||
                (arr[2] == 'O' && arr[4] == 'O' && arr[6] == 'O') || (arr[0] == 'O' && arr[3] == 'O' && arr[6] == 'O') ||
                (arr[1] == 'O' && arr[4] == 'O' && arr[7] == 'O') || (arr[2] == 'O' && arr[5] == 'O' && arr[8] == 'O')) {
            return true;
        }
        return false;
    }

    static boolean isImpossible(char[] arr) {
        if (isXWins(arr) && isOWins(arr)) {
            return true;
        }

        if (checkEquality(arr)) {
            return true;
        }
        return false;
    }

    static boolean isDraw(char[] arr) {
        if (!isXWins(arr) && !isOWins(arr)) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '_' || arr[i] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean checkEquality(char[] arr) {
        int x = 0;
        int o = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'X') {
                x++;
            } else if (arr[i] == 'O') {
                o++;
            }
        }

        if (x - o >= 2 || x - o <= -2) {
            return true;
        }

        return false;
    }

    //Check is occupied
    static boolean isOccupied(char[] arr, int position) {
        if (arr[position] == 'X' || arr[position] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
    }

    //Enter coordinates
    static void getCoordinates(int[] coord) {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the coordinates: ");
                coord[0] = scan.nextInt();
                coord[1] = scan.nextInt();
                if ((coord[0] < 1 || coord[0] > 3) || (coord[1] < 1 || coord[1] > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    //Match coordinates
    static int matchCoordinates(int[] coord) {
        int position = 0;

        switch (coord[0]) {
            case 1:
                switch (coord[1]) {
                    case 1:
                        position = 6;
                        break;
                    case 2:
                        position = 3;
                        break;
                    case 3:
                        position = 0;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (coord[1]) {
                    case 1:
                        position = 7;
                        break;
                    case 2:
                        position = 4;
                        break;
                    case 3:
                        position = 1;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (coord[1]) {
                    case 1:
                        position = 8;
                        break;
                    case 2:
                        position = 5;
                        break;
                    case 3:
                        position = 2;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return position;
    }

    //Insert element
    static void putElement(char[] arr, int[] coord, int position, boolean isFirstPlayer) {
        if (arr[position] == ' ' || arr[position] == '_') {
            if (isFirstPlayer) {
                arr[position] = 'X';
            } else {
                arr[position] = 'O';
            }
        }
    }
}
