package Game;

import java.util.ArrayList;
import java.util.HashMap;

public class TennisMatch {


	Player player1;
	Player player2;

	MatchType gameType;

	boolean tieBreak;

	boolean matchEnd;

	boolean tieBreakIsRunnig;



	ArrayList<GameSet> sets;
	int currentSetIndex;

	public TennisMatch(Player player1, Player player2, MatchType gameType, boolean tieBreak) {
		super();
		matchEnd=false;
		tieBreakIsRunnig=false;
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


	public int gamesInCurrentSetForPlayer(Player p)
	{
		return getCurrentSet().getGameNumberWonByPlayer().get(p);
	}

	public int gamesInSetForPlayer(int setIndex,Player player)
	{

		int wonGames=0;

		try
		{
			wonGames = sets.get(setIndex).getGameNumberWonByPlayer().get(player);
		}
		catch(Exception e)
		{
			System.out.println("This set doesn't exist yet");
		}

		return wonGames;
	}


	private int playerWinsGame(Player player, HashMap<Player, Integer> score) {
		int currentPoints;
		getCurrentSet().resetGame();
		currentPoints = score.get(player);
		
		if(getCurrentSet().getGameNumberWonByPlayer().get(player) == 6 && getCurrentSet().getGameNumberWonByPlayer().get(getOtherPlayer(player)) == 6)
		{
			//Manage tie break
			tieBreakIsRunnig = true;
		}
		
		
		addAWonGameToPlayer(player);


		if(getCurrentSet().getPlayerWhoWonSet() != null)
		{
			int setNumberWonByPlayerWhoJustWonSet = getSetNumberWonByPlayer(getCurrentSet().getPlayerWhoWonSet());
			if(setNumberWonByPlayerWhoJustWonSet >= gameType.numberOfSetsToWin() )
			{
				setMatchEnd(true);
			}
			else
			{
				currentSetIndex+=1;
				sets.add(new GameSet(player1, player2));
			}
		}





		return currentPoints;
	}




	private void addAWonGameToPlayer(Player player) {
		HashMap<Player,Integer> gameNumberWonByPlayer = getCurrentSet().getGameNumberWonByPlayer();
		gameNumberWonByPlayer.put(player, gameNumberWonByPlayer.get(player)+1);
		getCurrentSet().setGameNumberWonByPlayer(gameNumberWonByPlayer);
	}

	
	public Player whoWonMatch()
	{
		if(isMatchEnd())
		{
			if(getSetNumberWonByPlayer(player1) > getSetNumberWonByPlayer(player2))
			{
				return player1;
			}
			else return player2;
		}
		else return null;
	}

	private int getSetNumberWonByPlayer(Player player)
	{
		int number=0;
		for(GameSet set :sets)
		{
			if(set.getPlayerWhoWonSet() == player)
			{
				number++;
			}
		}
		return number;
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




	public MatchType getGameType() {
		return gameType;
	}




	public void setGameType(MatchType gameType) {
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



	public boolean isMatchEnd() {
		return matchEnd;
	}




	public void setMatchEnd(boolean gameEnd) {
		this.matchEnd = gameEnd;
	}



}
