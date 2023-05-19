import java.util.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

class Guess {
    static final int EASY = 3;
    static final int INTERMEDIATE = 2;
    static final int HARD = 1;
    static Scanner in = new Scanner(System.in);
    static Random r = new Random();
    static Map<String, Integer> scores = new HashMap<String, Integer>();

    public static void printScores() {

        StringBuilder sb = new StringBuilder();
        scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> sb.append(entry).append(System.lineSeparator()));

        String options[] = { "Ok" };
        JOptionPane.showOptionDialog(null, "Player - Score\n" + sb, "Scores", 0, 1, null, options, null);
    }

    /* calculates scores based on the no.of attempts */
    public static void calculate_score(String name, int attempts) {

        if (!scores.containsKey(name) || scores.get(name) < (100 - attempts))
            scores.put(name, 100 - attempts);

    }

    /* takes number and validates and keeps count of no.of attempts */
    public static boolean round(String name) {
        int attempts = 0;
        int number = r.nextInt(100);
        String guess_num = "";
        int in_num = 0;

        do {
            guess_num = JOptionPane.showInputDialog("Enter your Guess (cancel to quit the round):");
            if (guess_num == null) {
                JOptionPane.showMessageDialog(null, "The number was " + number);

                return false;
            }
            try {
                in_num = Integer.parseInt(guess_num);
                if (in_num > number)
                    JOptionPane.showMessageDialog(null, "Lower!", "Remarks", JOptionPane.INFORMATION_MESSAGE);
                else if (in_num < number)
                    JOptionPane.showMessageDialog(null, "Higher!", "Remarks", JOptionPane.INFORMATION_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(null,
                            "Thats correct! You have guessed the number in " + (attempts + 1) + " attempt(s)",
                            "Remarks", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                attempts += 1;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Only integer input allowed!", "Remarks",
                        JOptionPane.WARNING_MESSAGE);

                System.out.println();
            }

        } while (true);

        Guess.calculate_score(name, attempts);
        return true;
    }

    public static void play() {

        int players, noOfPlayers, level;
        int round, noOfRounds;
        String name;

        System.out.print("Levels:\n1.EASY\n2.INTERMEDIATE\n3.HARD\nSelect your level:");
        while (!in.hasNextInt()) {
            System.out.println("Only integers allowed!");
            in.next();
        }
        level = in.nextInt();
        in.nextLine();
        if (level > 3)
            level = 3;
        switch (level) {
            case 1:
                noOfRounds = EASY;
                break;
            case 2:
                noOfRounds = INTERMEDIATE;
                break;
            case 3:
                noOfRounds = HARD;
                break;
            default:
                noOfRounds = EASY;
        }

        System.out.print("Enter no.of players:");
        while (!in.hasNextInt()) {
            System.out.println("Only integers allowed!");
            in.nextLine();
        }

        noOfPlayers = in.nextInt();
        in.nextLine();
        players = noOfPlayers;
        while (players > 0) {
            round = 0;
            name = JOptionPane.showInputDialog("Enter your name:");
            while (round < noOfRounds) {
                System.out.println("Round " + (round + 1) + " :");
                if (!Guess.round(name))
                    break;
                round++;
            }
            Guess.printScores();
            players--;
        }
    }

    public static void main(String[] args) {

        int choice;
        main: while (true) {
            System.out.println("Main Menu:\n1.Start Game\n2.ScoreBoard\n3.Exit");
            System.out.print("Enter your choice:");
            while (!in.hasNextInt()) {
                in.next();
                System.out.println("Only Integers allowed!");

            }
            choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    Guess.play();
                    break;
                case 2:
                    Guess.printScores();
                    break;
                case 3:
                    break main;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
