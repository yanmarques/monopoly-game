package com.prj.entity;

import com.org.Node;
import com.org.circle.DoubleCircledList;
import com.prj.commom.BoardNode;

public class PathBoard {
    private DoubleCircledList<BoardNode> board;
    private Player banker;

    public PathBoard() {
        this.board = new DoubleCircledList<>();
        this.banker = new Player("BANQUEIRO");

        BoardNode startNode = new BoardNode(true);
        this.append(startNode);
    }

    public Player getBanker() {
        return banker;
    }

    public void append(BoardNode node) {
        this.board.insertLast(new Node<>(node));
    }
}
