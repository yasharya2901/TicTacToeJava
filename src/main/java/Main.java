import controllers.GameController;
import models.*;
import exceptions.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Hello Player!");
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Enter the size of the board: ");
        int dimension = scanner.nextInt();
        List<Player> players = List.of(
                new Player("Yash", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game = gameController.startGame(dimension, players);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            System.out.println();

            gameController.makeMove(game);
        }

        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}
