import java.util.Scanner;
import java.io.*;

public class RPSGame {
    static int highScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        int playerScore = 0;
        boolean playing = true;

        // Check if player data exists and load it if found
        File playerDataFile = new File("playerdata.txt");
        if (playerDataFile.exists()) {
            try {
                Scanner fileScanner = new Scanner(playerDataFile);
                String[] playerData = fileScanner.nextLine().split(",");
                String savedPlayerName = playerData[0];
                int savedHighScore = Integer.parseInt(playerData[1]);
                if (savedPlayerName.equals(playerName)) {
                    playerScore = Integer.parseInt(playerData[2]);
                    if (playerScore > savedHighScore) {
                        highScore = playerScore;
                    } else {
                        highScore = savedHighScore;
                    }
                } else {
                    highScore = savedHighScore;
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error loading player data: " + e.getMessage());
            }
        }

        while (playing) {
            System.out.println("Current high score: " + highScore);
            System.out.println("Player score: " + playerScore);
            System.out.println("Choose your move: (1) Rock, (2) Paper, (3) Scissors, (4) Quit");
            int playerMove = scanner.nextInt();
            if (playerMove == 4) {
                playing = false;
                break;
            }
            int computerMove = (int)(Math.random() * 3) + 1;
            String result = "";
            if (playerMove == computerMove) {
                result = "Draw";
            } else if ((playerMove == 1 && computerMove == 3) || 
                       (playerMove == 2 && computerMove == 1) ||
                       (playerMove == 3 && computerMove == 2)) {
                playerScore++;
                result = "You win!";
                if (playerScore > highScore) {
                    highScore = playerScore;
                }
            } else {
                playerScore--;
                result = "Computer wins!";
            }
            System.out.println("You chose " + moveToString(playerMove) + ".");
            System.out.println("Computer chose " + moveToString(computerMove) + ".");
            System.out.println(result);
        }

        // Save player data to file
        try {
            FileWriter writer = new FileWriter(playerDataFile);
            writer.write(playerName + "," + highScore + "," + playerScore);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }

    public static String moveToString(int move) {
        if (move == 1) {
            return "Rock";
        } else if (move == 2) {
            return "Paper";
        } else if (move == 3) {
            return "Scissors";
        } else {
            return "";
        }
    }
}