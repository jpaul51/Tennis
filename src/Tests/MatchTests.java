package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.GameType;
import Game.Player;
import Game.TennisMatch;

public class MatchTests {

	
	TennisMatch match1;
	Player player1;
	Player player2;
	
	@Before
	public void init()
	{
		player1 = new Player("Jean");
		player2 = new Player("Pierre");
		match1 = new TennisMatch(player1,player2,GameType.BEST_OF_THREE,false);
	}
	
	@Test
	public void assertAddFirstPointWorks() {
		match1.updateWithPointWonBy( player1);	
		assert(match1.pointsForPlayer(player1).equals("15"));
	}
	
	@Test
	public void assertAddSecondPointWorks() {
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		assert(match1.pointsForPlayer(player1).equals("30"));
	}
	
	@Test
	public void assertAddThirdPointWorks() {
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		assert(match1.pointsForPlayer(player1).equals("40"));
	}
	
	@Test
	public void assertAddLastPointWorks() {
		p1WonGame();
		assert(match1.pointsForPlayer(player1).equals("0"));
	}
	
	@Test
	public void assertPlayerWonGame() {
		p1WonGame();
		assert(match1.getSets().get(match1.currentSetNumer()).getGameNumberWonByPlayer().get(player1)==1);
	}

	@Test
	public void assertPlayerGetAdvantage() {
		bothPlayer40Points();
		match1.updateWithPointWonBy(player1);
		assert(match1.pointsForPlayer(player1).equals("A"));
		assert(match1.getSets().get(match1.currentSetNumer()).getGameNumberWonByPlayer().get(player1)==0);
	}
	
	@Test
	public void assertPlayerLoosesAdvantage() {
		bothPlayer40Points();
		match1.updateWithPointWonBy(player1);
		match1.updateWithPointWonBy(player2);
		assert(match1.pointsForPlayer(player1).equals("40"));
		assert(match1.pointsForPlayer(player2).equals("40"));
		
	}
	
	@Test
	public void assertPlayerWinsAfterAdvantage() {
		bothPlayer40Points();
		match1.updateWithPointWonBy(player1);
		match1.updateWithPointWonBy(player1);
		assert(match1.pointsForPlayer(player1).equals("0"));
		assert(match1.pointsForPlayer(player2).equals("0"));
		assert(match1.getSets().get(match1.currentSetNumer()).getGameNumberWonByPlayer().get(player1)==1);
		
	}
	
	
	
	private void bothPlayer40Points()
	{
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		
		match1.updateWithPointWonBy( player2);
		match1.updateWithPointWonBy( player2);
		match1.updateWithPointWonBy( player2);
	}
	
	private void p1WonGame()
	{
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
		match1.updateWithPointWonBy( player1);
	}
	
}
