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

//    @Test
//    public void movesPlayerOnePositionBackward() {
//        BoardNode node = new BoardNode(false);
//        Player player = this.dummyPlayer();
//
//        this.board.append(node);
//        this.board.addPlayer(player);
//
//        BoardNode movedNode = this.board.movePlayer(player, -1);
//
//        assertEquals(node, movedNode);
//    }
}
