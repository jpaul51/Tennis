package Game;

import java.util.HashMap;

public class GameSet {



	Player player1;
	Player player2;

	HashMap<Player,Integer> gameNumberWonByPlayer;


	HashMap<Player,Integer> pointsForPlayerInCurrentGame;



	public GameSet(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		gameNumberWonByPlayer = new HashMap<>();
		gameNumberWonByPlayer.put(player1, 0);
		gameNumberWonByPlayer.put(player2, 0);
		resetGame();


	}



	public void resetGame()
	{
		pointsForPlayerInCurrentGame = new HashMap<>();
		pointsForPlayerInCurrentGame.put(player1, 0);
		pointsForPlayerInCurrentGame.put(player2, 0);

	}

	public boolean isSetFinished()
	{
		int player1GamesWon = gameNumberWonByPlayer.get(player1);
		int player2GamesWon = gameNumberWonByPlayer.get(player2);
		
		if(player1GamesWon > player2GamesWon && player1GamesWon - player2GamesWon >= 2)
			return true;
		else if(player2GamesWon > player1GamesWon && player2GamesWon - player1GamesWon >= 2)
		{
			return true;
		}
		else 
			return false;
	}

	public Player getPlayerWhoWonSet()
	{
		if(gameNumberWonByPlayer.get(player1) - gameNumberWonByPlayer.get(player2) >= 2 && gameNumberWonByPlayer.get(player1) >= 6)
			return player1;
		else if( gameNumberWonByPlayer.get(player2) - gameNumberWonByPlayer.get(player1) >= 2 && gameNumberWonByPlayer.get(player2)>=6)
			return player2;
		else
			return null;
		
	}


	public Player getPlayer1() {
		return player1;
	}



	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}



	public Player getPlayer2() {
		return player2;
	}



	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}



	public HashMap<Player, Integer> getGameNumberWonByPlayer() {
		return gameNumberWonByPlayer;
	}



	public void setGameNumberWonByPlayer(HashMap<Player, Integer> gameNumberWonByPlayer) {
		this.gameNumberWonByPlayer = gameNumberWonByPlayer;
	}



	public HashMap<Player, Integer> getPointsForPlayerInCurrentGame() {
		return pointsForPlayerInCurrentGame;
	}



	public void setPointsForPlayerInCurrentGame(HashMap<Player, Integer> pointsForPlayerInCurrentGame) {
		this.pointsForPlayerInCurrentGame = pointsForPlayerInCurrentGame;
	}





}
