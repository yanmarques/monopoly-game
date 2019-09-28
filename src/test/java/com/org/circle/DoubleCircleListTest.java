package com.org.circle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DoubleCircleListTest {
    private DoubleCircledList list;

    @BeforeEach
    public void setUp() {
        this.list = new DoubleCircledList();
    }

    @AfterEach
    public void teardown() {
        this.list.clear();
        this.list = null;
    }
}
