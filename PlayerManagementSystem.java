import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Player {
    private int jerseyNumber;
    private String playerName;
    private int runs;
    private int wickets;
    private int matchesPlayed;

    // Constructors
    public Player() {
        this.jerseyNumber = 0;
        this.playerName = "";
        this.runs = 0;
        this.wickets = 0;
        this.matchesPlayed = 0;
    }

    public Player(int jerseyNumber, String playerName, int runs, int wickets, int matchesPlayed) {
        this.jerseyNumber = jerseyNumber;
        this.playerName = playerName;
        this.runs = runs;
        this.wickets = wickets;
        this.matchesPlayed = matchesPlayed;
    }

    // Accessors
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    // Modifiers
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
}

class PlayerManager {
    private List<Player> players;

    public PlayerManager() {
        players = new ArrayList<>();
    }

    // Function to add a player
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Function to remove a player
    public void removePlayer(int jerseyNumber) {
        Player playerToRemove = players.stream()
                .filter(player -> player.getJerseyNumber() == jerseyNumber)
                .findFirst()
                .orElse(null);

        if (playerToRemove != null) {
            players.remove(playerToRemove);
            System.out.println("Player removed successfully!");
        } else {
            System.out.println("Player not found with Jersey Number " + jerseyNumber + ".");
        }
    }

    // Function to search for a player
    public void searchPlayer(int choice, String searchValue) {
        Player foundPlayer = null;
        switch (choice) {
            case 1:
                int jerseyNumber = Integer.parseInt(searchValue);
                foundPlayer = players.stream()
                        .filter(player -> player.getJerseyNumber() == jerseyNumber)
                        .findFirst()
                        .orElse(null);
                break;
            case 2:
                foundPlayer = players.stream()
                        .filter(player -> player.getPlayerName().equalsIgnoreCase(searchValue))
                        .findFirst()
                        .orElse(null);
                break;
        }

        if (foundPlayer != null) {
            System.out.println("Player found:");
            printPlayerDetails(foundPlayer);
        } else {
            System.out.println("Player not found.");
        }
    }

    // Function to update player details
    public void updatePlayer(int jerseyToUpdate, int runs, int wickets, int matches) {
        Player playerToUpdate = players.stream()
                .filter(player -> player.getJerseyNumber() == jerseyToUpdate)
                .findFirst()
                .orElse(null);

        if (playerToUpdate != null) {
            playerToUpdate.setRuns(runs);
            playerToUpdate.setWickets(wickets);
            playerToUpdate.setMatchesPlayed(matches);
            System.out.println("Player data updated successfully!");
        } else {
            System.out.println("Player not found with Jersey Number " + jerseyToUpdate + ".");
        }
    }

    // Function to sort players by runs
    public void sortByRuns() {
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparingInt(Player::getRuns).reversed())
                .collect(Collectors.toList());

        System.out.println("Top 3 Players by Runs:");
        for (int i = 0; i < Math.min(3, sortedPlayers.size()); i++) {
            System.out.println("Rank " + (i + 1) + ":");
            printPlayerDetails(sortedPlayers.get(i));
        }
    }

    // Function to sort players by wickets
    public void sortByWickets() {
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparingInt(Player::getWickets).reversed())
                .collect(Collectors.toList());

        System.out.println("Top 5 Players by Wickets:");
        for (int i = 0; i < Math.min(5, sortedPlayers.size()); i++) {
            System.out.println("Rank " + (i + 1) + ":");
            printPlayerDetails(sortedPlayers.get(i));
        }
    }

    // Function to print all players
    public void printAllPlayers() {
        if (players.isEmpty()) {
            System.out.println("No players in the system.");
            return;
        }
        System.out.println("Displaying All Players:");
        for (Player player : players) {
            printPlayerDetails(player);
        }
    }

    // Function to print player details
    private void printPlayerDetails(Player player) {
        System.out.println("Jersey Number: " + player.getJerseyNumber());
        System.out.println("Player Name: " + player.getPlayerName());
        System.out.println("Runs: " + player.getRuns());
        System.out.println("Wickets: " + player.getWickets());
        System.out.println("Matches Played: " + player.getMatchesPlayed());
        System.out.println("-----------------");
    }
}

public class PlayerManagementSystem {
    public static void main(String[] args) {
        PlayerManager manager = new PlayerManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPlayer Management System:");
            System.out.println("1. Add Player");
            System.out.println("2. Remove Player");
            System.out.println("3. Search Player");
            System.out.println("4. Update Player");
            System.out.println("5. Print Top 3 Players by Runs");
            System.out.println("6. Print Top 5 Players by Wickets");
            System.out.println("7. Display All Players");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter details for the new player:");
                    System.out.print("Jersey Number: ");
                    int jersey = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Player Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Runs: ");
                    int runs = scanner.nextInt();
                    System.out.print("Wickets: ");
                    int wickets = scanner.nextInt();
                    System.out.print("Matches Played: ");
                    int matches = scanner.nextInt();
                    manager.addPlayer(new Player(jersey, name, runs, wickets, matches));
                    break;
                case 2:
                    System.out.print("Enter the Jersey Number of the player to remove: ");
                    int jerseyToRemove = scanner.nextInt();
                    manager.removePlayer(jerseyToRemove);
                    break;
                case 3:
                    System.out.println("Search player by:");
                    System.out.println("1. Jersey Number");
                    System.out.println("2. Player Name");
                    System.out.print("Enter your choice: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter search value: ");
                    String searchValue = scanner.nextLine();
                    manager.searchPlayer(searchChoice, searchValue);
                    break;
                case 4:
                    System.out.print("Enter the Jersey Number of the player to update: ");
                    int jerseyToUpdate = scanner.nextInt();
                    System.out.print("Enter updated details for the player:\nRuns: ");
                    runs = scanner.nextInt();
                    System.out.print("Wickets: ");
                    wickets = scanner.nextInt();
                    System.out.print("Matches Played: ");
                    matches = scanner.nextInt();
                    manager.updatePlayer(jerseyToUpdate, runs, wickets, matches);
                    break;
                case 5:
                    manager.sortByRuns();
                    break;
                case 6:
                    manager.sortByWickets();
                    break;
                case 7:
                    manager.printAllPlayers();
                    break;
                case 8:
                    System.out.println("Exiting From Player Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter an option from the above options.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
