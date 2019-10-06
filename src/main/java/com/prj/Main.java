package com.prj;

import com.prj.commom.BoardNode;
import com.prj.commom.Logger;
import com.prj.commom.card.CardStack;
import com.prj.commom.card.LuckyCard;
import com.prj.commom.card.un_luckies.GoToJail;
import com.prj.commom.card.un_luckies.PayMoney;
import com.prj.commom.factory.BoardSeeder;
import com.prj.entity.PathBoard;
import com.prj.entity.Player;
import com.prj.commom.Dice;
import com.prj.entity.building.Ground;
import com.prj.entity.building.Hotel;
import com.prj.entity.building.House;

import java.nio.file.Path;

public class Main {
	
	public static void main(String[] args) {
		PathBoard board = createBoard();
		CardStack cards = createCardStack(board);
		
		while(!board.getPlayers().isEmpty()) {
			for (Player player : board.getPlayers()) {
				BoardNode node = board.movePlayer(player, Dice.play());

				if (node.isStart()) {
					board.getBoard().forward();
					node = board.getBoard().getCurrentNode().getValue();
				}

				Ground ground = node.getGround();

				if (node.isLuckyCard()) {
					cards.take(player);
				} else {
					if (ground.getOwner() == player) {
						try {
							// tenta construir um hotel
							board.getBanker().getRegistry().build(ground, new Hotel());
						} catch (Exception exc1) {
							Logger.error(exc1.getMessage());
							try {
								// tenta construir uma casa
								board.getBanker().getRegistry().build(ground, new House());
							} catch (Exception exc2) {
								Logger.error(exc2.getMessage());
							}
						}
					} else if (ground.getOwner() == board.getBanker())  {
						try {
							// tenta comprar o terreno
							board.getBanker().getRegistry().sell(ground, player);
						} catch (Exception exc1) {
							Logger.error(exc1.getMessage());
						}
					} else {
						// paga o aluguel
						board.getBanker().getRegistry().chargeRent(player, ground);
					}
				}
			}

			board.applyRoutines();
		}
	}

	private static PathBoard createBoard() {
		Player player1 = new Player("Joao");
		Player player2 = new Player("Pedro");
		Player player3 = new Player("Henrique");

		BoardSeeder boardSeeder = new BoardSeeder();
		PathBoard board = boardSeeder.seedBoard();

		board.addPlayer(player1);
		board.addPlayer(player2);
		board.addPlayer(player3);

		return board;
	}

	private static CardStack createCardStack(PathBoard board) {
		LuckyCard[] availableCards = new LuckyCard[2];
		availableCards[0] = new PayMoney();
		availableCards[1] = new GoToJail();

		return new CardStack(board, availableCards);
	}
}
