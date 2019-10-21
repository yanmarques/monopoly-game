package com.prj.commom.factory;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.prj.commom.BoardNode;
import com.prj.commom.Dice;
import com.prj.entity.PathBoard;
import com.prj.entity.building.Ground;
import com.prj.entity.Player;

public class BoardSeeder {
    public static int SIZE = 38;
    public static int TOTAL_PROPORTION = 10;
    public static int GROUND_PROPORTION = 7;
    public static int GROUND_AMOUNT_PERCENT = 2;
    public static long GROUND_PRICE_MULTIPLIER = 100;
    public static int GROUND_PRICE_RANGE = 90;

	public PathBoard seedBoard() {

		PathBoard board = new PathBoard();
		int i, j;
		i = 0;

		while (i < SIZE) {
			j = Dice.nextInt(TOTAL_PROPORTION);

			if (j < GROUND_PROPORTION) {
				BoardNode groundNode = this.generateGround(board.getBanker());
				board.append(groundNode);
			} else {
				BoardNode luckyCardNode = new BoardNode(false);
				board.append(luckyCardNode);
			}
			i++;
		}
		return board;
	}

    private BoardNode generateGround(Player banker) {
        DoubleChainedList<Integer> prices = new DoubleChainedList<>();
        for (int i = 10; i <= GROUND_PRICE_RANGE; i += 10) {
            prices.insertLast(new Node<>(i));
        }

        int randBoundNumber = Dice.nextInt(prices.getSize());
        int randPrice = prices.get(randBoundNumber).getValue();
        long price = Dice.nextPositiveInt(randPrice) * GROUND_PRICE_MULTIPLIER;
        int rentAmount = (GROUND_AMOUNT_PERCENT * randPrice) / 10;

        Ground ground = new Ground(price, rentAmount, banker);
        return new BoardNode(ground);
    }
}
