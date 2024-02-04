package game;

import java.util.*;

public class OlympTour {
    public static void main(String[] args) {
        System.out.println("How many players?");
        int playersCount;
        Scanner sc = new Scanner(System.in);
        try {
            playersCount = sc.nextInt();
        } catch (Exception e) {
            System.out.println("you should give int number");
            return;
        }
        List<Player> players = new ArrayList<>(playersCount);
        List<Integer> scorePlayers = new ArrayList<>(playersCount);
        for (int i = 0; i < playersCount; ++i) {
            players.add(i, new HumanPlayer(i));
            scorePlayers.add(0);
        }
        int takesPart = playersCount == 0 ? 0 : 31 - Integer.numberOfLeadingZeros(playersCount);// min 2 pow
        takesPart = (playersCount - (1 << takesPart)) * 2;
        Board board;
        System.out.println("Round board or nmk board? Print r for round, anything else for mnk");
        String boardType = sc.next();
        int m = 0;
        int n = 0;
        int k;
        int r = 0;
        boolean roundBoard = false;
        if (boardType.equals("r")) {
            roundBoard = true;
            System.out.println("Radius and k:");
            try {
                r = sc.nextInt();
                k = sc.nextInt();
            } catch (Exception e) {
                System.out.println("2 integers expected");
                return;
            }
        } else {
            System.out.println("n, m and k:");
            try {
                n = sc.nextInt();
                m = sc.nextInt();
                k = sc.nextInt();
            } catch (Exception e) {
                System.out.println("3 integers expected");
                return;
            }
        }
        int roundsCount = 0;
        while (players.size() > 1) {
            roundsCount++;
            Collections.shuffle(players);
            List<Player> newRoundPlayers = new ArrayList<>();
            System.out.print("Circle ");
            System.out.println(roundsCount);
            for (int i = 0; i < takesPart; i += 2) {
                int rez = 0;
                while (rez == 0) {
                    if (roundBoard) {
                        board = new RoundBoard(r, k);
                    }
                    else{
                        board = new mnkBoard(m, n, k);
                    }
                    System.out.print("New game: ");
                    System.out.print(players.get(i).getPlayerNumber() + 1);
                    System.out.print(" plays for X, against ");
                    System.out.println(players.get(i + 1).getPlayerNumber() + 1);
                    Game game = new Game(true, players.get(i), players.get(i + 1));
                    rez = game.play(board);
                }
                if (rez == 1) {
                    newRoundPlayers.add(players.get(i));
                    //newPlayersNumbers.add(playersNumbers.get(i));
                    scorePlayers.set(players.get(i + 1).getPlayerNumber(), -roundsCount);
                } else {
                    newRoundPlayers.add(players.get(i + 1));
                    //newPlayersNumbers.add(playersNumbers.get(i + 1));
                    scorePlayers.set(players.get(i).getPlayerNumber(), -roundsCount);
                }
            }
            for (int i = takesPart; i<playersCount; ++i){
                newRoundPlayers.add(players.get(i));
                //newPlayersNumbers.add(playersNumbers.get(i));
            }
            players = newRoundPlayers;
            //playersNumbers = newPlayersNumbers;
            takesPart = players.size();
            playersCount = players.size();
        }
        scorePlayers.set(players.get(0).getPlayerNumber(), -roundsCount - 1);
        System.out.println("RESULTS!!!");
        Collections.sort(scorePlayers);
        System.out.println("Player/score");
        for (int i = 0; i < scorePlayers.size(); ++i) {
            System.out.print(i+1);
            System.out.print(" ");
            System.out.println(roundsCount + 2 + scorePlayers.get(i));

        }
    }
}
