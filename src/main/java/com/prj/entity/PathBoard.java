package com.prj.entity;

import java.util.HashMap;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;
import com.org.circle.DoubleCircledList;
import com.prj.commom.BoardNode;
import com.prj.commom.Logger;
import com.prj.entity.bank.Banker;
import com.prj.entity.prison.Jail;

public class PathBoard {
    private DoubleCircledList<BoardNode> board;
    private HashMap<Player, Integer> players, prisioners;
    private Banker banker;
    private Jail jail;

    public PathBoard() {
        this.board = new DoubleCircledList<>();
        this.banker = new Banker();
        this.players = new HashMap<>();
        this.prisioners = new HashMap<>();
        this.jail = new Jail(getBanker());

        BoardNode startNode = new BoardNode(true);
        this.append(startNode);
    }

    public Banker getBanker() {
        return banker;
    }

    public void putInJail(Player player) {
        int position = this.findPosition(player);
        this.removePlayer(player);
        this.jail.arrest(player);
        this.prisioners.put(player, position);
    }

    public int getPosition(Player player) {
        return this.players.get(player);
    }

    public DoubleCircledList<BoardNode> getBoard() {
        return board;
    }

    public BoardNode movePlayer(Player player, int positions) throws IllegalArgumentException {
        Logger.shPlayer(player, "moving "+ positions +" positions.");
        int srcPosition = this.findPosition(player);
        int targetPosition = srcPosition + positions;

        this.board.moveTo(srcPosition);

        if (positions > 0) {
            if (targetPosition >= this.board.getSize()) {
                Logger.shPlayer(player, "completed a round.");
                this.banker.give(player, 200);
                this.banker.getRegistry().chargeTaxes(player);
                targetPosition -= this.board.getSize();
            }
        }

        this.players.put(player, targetPosition);
        return this.board.moveTo(targetPosition).getValue();
    }

    public void applyRoutines() {
        int oldPosition;
        for (Player player : this.jail.liberate()) {
            oldPosition = this.findPrisionerPosition(player);
            this.players.put(player, oldPosition);
            this.prisioners.remove(player);
        }

        for (Player player : getBanker().getDefaultings()) {
            this.removePlayer(player);
        }
    }

    public void addPlayer(Player player) {
        this.banker.createAccount(player);
        this.players.put(player, 0);
    }

    public void append(BoardNode node) {
        this.board.insertLast(new Node<>(node));
    }
    
    public DoubleChainedList<Player> getPlayers() {
        DoubleChainedList<Player> players = new DoubleChainedList<>();

        for (Player player : this.players.keySet()) {
            players.insertLast(new Node<>(player));
        }

    	return players;
    }

    public boolean hasPlayers() {
        return ! getPlayers().isEmpty() || ! prisioners.isEmpty();
    }

    private int findPosition(Player player) {
        Integer position = this.players.get(player);
        if (position == null)
            throw new IllegalArgumentException("Player ["+ player.getName() +"] is not in the board.");
        return position;
    }

    private int findPrisionerPosition(Player player) {
        Integer position = this.prisioners.get(player);
        if (position == null)
            throw new IllegalArgumentException("Prisioner ["+ player.getName() +"] is not in the board.");
        return position;
    }

    private void removePlayer(Player player) {
        this.players.remove(player);
    }
}