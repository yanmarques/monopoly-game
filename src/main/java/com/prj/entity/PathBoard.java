package com.prj.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.org.Node;
import com.org.circle.PositionedCircularList;
import com.prj.commom.BoardNode;
import com.prj.entity.bank.Banker;

public class PathBoard {
    private PositionedCircularList<BoardNode> board;
    private HashMap<Player, Integer> players;
    private Banker banker;

    public PathBoard() {
        this.board = new PositionedCircularList<>();
        this.banker = new Banker();
        this.players = new HashMap<>();

        BoardNode startNode = new BoardNode(true);
        this.append(startNode);
    }

    public Banker getBanker() {
        return banker;
    }

    public BoardNode movePlayer(Player player, int positions) throws IllegalArgumentException {
        int srcPosition = this.findPosition(player);

        this.board.forwardTo(srcPosition);
        this.board.forwardTo(positions);

        this.players.put(player, this.board.getSize());

        return this.board.getCurrentNode().getValue();
    }

    public void addPlayer(Player player) {
        this.banker.createAccount(player);
        this.players.put(player, 0);
    }

    public void append(BoardNode node) {
        this.board.insertLast(new Node<>(node));
    }
    
    public List<Player> getPlayers(){
    	List<Player> lista = new ArrayList<Player>();
    	for (Player player : this.players.keySet()) {
			lista.add(player);
		}
    	return lista;
    }

    private int findPosition(Player player) {
        Integer position = this.players.get(player);
        if (position == null)
            throw new IllegalArgumentException("Player ["+ player.getName() +"] is not in the board.");
        return position;
    }
}