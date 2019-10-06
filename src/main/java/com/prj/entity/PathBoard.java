package com.prj.entity;

import com.org.Node;
import com.org.circle.DoubleCircledList;
import com.prj.commom.BoardNode;
import com.prj.entity.bank.Banker;

import java.util.HashMap;

public class PathBoard {
    private DoubleCircledList<BoardNode> board;
    private HashMap<Player, Integer> players;
    private Banker banker;

    public PathBoard() {
        this.board = new DoubleCircledList<>();
        this.banker = new Banker();
        this.players = new HashMap<>();

        BoardNode startNode = new BoardNode(true);
        this.append(startNode);
    }

    public Banker getBanker() {
        return banker;
    }

    public int getPosition(Player player) {
        return this.players.get(player);
    }

    public BoardNode movePlayer(Player player, int positions) throws IllegalArgumentException {
        int srcPosition = this.findPosition(player);
        int targetPosition = srcPosition + positions;

        this.board.moveTo(srcPosition);

        if (positions > 0) {
            if (targetPosition >= this.board.getSize()) {
                // volta completa
                targetPosition -= this.board.getSize();
            }
        }

        this.players.put(player, targetPosition);
        return this.board.moveTo(targetPosition).getValue();
    }

    public void addPlayer(Player player) {
        this.banker.createAccount(player);
        this.players.put(player, 0);
    }

    public void append(BoardNode node) {
        this.board.insertLast(new Node<>(node));
    }

    private int findPosition(Player player) {
        Integer position = this.players.get(player);
        if (position == null)
            throw new IllegalArgumentException("Player ["+ player.getName() +"] is not in the board.");
        return position;
    }
}
