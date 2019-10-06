package com.prj;

import com.prj.commom.factory.BoardSeeder;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;
import com.prj.commom.Dice;

public class Main {
	
	public static void main(String[] args) {
		Player player1 = new Player("Joao");
		Player player2 = new Player("Pedro");
		Player player3 = new Player("Henrique");

		BoardSeeder boardSeeder = new BoardSeeder();
		PathBoard board = boardSeeder.seedBoard();

		board.addPlayer(player1);
		board.addPlayer(player2);
		board.addPlayer(player3);
		
		while(!board.getPlayers().isEmpty()) {
			for (int i = 0; i < board.getPlayers().size(); i++) {
				board.movePlayer(board.getPlayers().get(i), Dice.play());	
			}
		}
	}
}
