package Game;

import java.util.ArrayList;
import java.util.HashMap;

public class TennisMatch {

	
	Player player1;
	Player player2;
	
	GameType gameType;
	
	boolean tieBreak;
	
	
	ArrayList<GameSet> sets;
	int currentSetIndex;

	public TennisMatch(Player player1, Player player2, GameType gameType, boolean tieBreak) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.gameType = gameType;
		this.tieBreak = tieBreak;
		
		sets = new ArrayList<>();
		GameSet set = new GameSet(player1,player2);
		sets.add(set);
		currentSetIndex=0;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}




	public void updateWithPointWonBy(Player player) {
		// TODO Auto-generated method stub
		HashMap<Player, Integer> score = getCurrentSet().getPointsForPlayerInCurrentGame();
		int currentPoints = score.get(player);
		Integer otherPlayerPoints = score.get(getOtherPlayer(player));
		
		if(currentPoints <30)
			currentPoints+=15;
		else if(currentPoints==30)
			currentPoints+=10;
		else if(currentPoints==40)
		{
			
			if(otherPlayerPoints==40)
			{
				currentPoints+=10;
			}
			else if(otherPlayerPoints==50)
			{
				score.put(getOtherPlayer(player), 40);
				getCurrentSet().setPointsForPlayerInCurrentGame(score);
			}
			else
			{
				//PLAYER WON A GAME
				currentPoints = playerWinsGame(player, score);
			}
		}
		else if(currentPoints==50)
		{
			currentPoints = playerWinsGame(player, score);
		}
		
		score.put(player, currentPoints);
	}




	private int playerWinsGame(Player player, HashMap<Player, Integer> score) {
		int currentPoints;
		getCurrentSet().resetGame();
		currentPoints = score.get(player);
		HashMap<Player,Integer> gameNumberWonByPlayer = getCurrentSet().getGameNumberWonByPlayer();
		gameNumberWonByPlayer.put(player, gameNumberWonByPlayer.get(player)+1);
		getCurrentSet().setGameNumberWonByPlayer(gameNumberWonByPlayer);
		return currentPoints;
	}




	private GameSet getCurrentSet() {
		return sets.get(currentSetIndex);
	}




	public String pointsForPlayer(Player player) {
		// TODO Auto-generated method stub
		
		if(getCurrentSet().getPointsForPlayerInCurrentGame().get(player) == 50)
		{
			return "A";
		}
		
		return   String.valueOf(getCurrentSet().getPointsForPlayerInCurrentGame().get(player));
	}

	
	public Player getOtherPlayer(Player player)
	{
		if (player1.equals(player))
			return player2;
		else
			return player1;
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




	public GameType getGameType() {
		return gameType;
	}




	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}




	public boolean isTieBreak() {
		return tieBreak;
	}




	public void setTieBreak(boolean tieBreak) {
		this.tieBreak = tieBreak;
	}




	public ArrayList<GameSet> getSets() {
		return sets;
	}




	public void setSets(ArrayList<GameSet> sets) {
		this.sets = sets;
	}




	public int currentSetNumer() {
		return currentSetIndex;
	}




	public void setCurrentSetIndex(int currentSetIndex) {
		this.currentSetIndex = currentSetIndex;
	}
	
	
	
	
	
	
}
