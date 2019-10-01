package com.org.circle;

import com.org.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoubleCircleListTest {
    private PositionedCircularList<Integer> list;

    @BeforeEach
    public void setUp() {
        this.list = new PositionedCircularList<>();
    }

    @AfterEach
    public void teardown() {
        this.list.clear();
        this.list = null;
    }
}
