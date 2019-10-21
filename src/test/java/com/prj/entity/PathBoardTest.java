package com.prj.entity;

import com.prj.TestCase;
import com.prj.commom.BoardNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathBoardTest extends TestCase {
    private PathBoard board;

    @BeforeEach
    public void setUp() {
        this.board = new PathBoard();
    }

    @Test
    public void movesPlayerOnePositionForward() {
        BoardNode node = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(node);
        this.board.addPlayer(player);

        BoardNode movedNode = this.board.movePlayer(player, 1);

        assertEquals(node, movedNode);
    }

    @Test
    public void movesPlayerOnePositionBackward() {
        BoardNode node = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(node);
        this.board.addPlayer(player);

        BoardNode movedNode = this.board.movePlayer(player, -1);

        assertEquals(node, movedNode);
    }

    @Test
    public void movesPlayerOnePositionBackwardWithManyRows() {
        BoardNode expectedNode = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(expectedNode);
        this.board.append(new BoardNode(false));

        this.board.addPlayer(player);

        BoardNode movedNode = this.board.movePlayer(player, 3); // move to initial
        assertTrue(movedNode.isStart());

        BoardNode targetNode = this.board.movePlayer(player, -2);
        assertEquals(expectedNode, targetNode);
    }

    @Test
    public void movesPlayerOnePositionForwardWithManyRows() {
        BoardNode expectedNode = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(new BoardNode(false));
        this.board.append(expectedNode);

        this.board.addPlayer(player);

        BoardNode movedNode = this.board.movePlayer(player, -3); // move to initial
        assertTrue(movedNode.isStart());

        BoardNode targetNode = this.board.movePlayer(player, 2);
        assertEquals(expectedNode, targetNode);
    }

    @Test
    public void completeACircuitOnTheCircularList() {
        BoardNode dummyNode = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(dummyNode);
        this.board.append(dummyNode);

        this.board.addPlayer(player);

        this.board.movePlayer(player, 3); // move to initial
        assertEquals(0, this.board.getPosition(player));
    }

    @Test
    public void backwardNegativePositions() {
        BoardNode dummyNode = new BoardNode(false);
        Player player = this.dummyPlayer();

        this.board.append(dummyNode);
        this.board.append(dummyNode);

        this.board.addPlayer(player);

        this.board.movePlayer(player, -1);
        this.board.movePlayer(player, 1);
        assertEquals(0, this.board.getPosition(player));
    }
}
