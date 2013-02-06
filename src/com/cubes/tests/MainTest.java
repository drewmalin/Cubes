package com.cubes.tests;

import junit.framework.*;

/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/3/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainTest extends TestCase {

    public MainTest() {
        super();
    }

    // --- Any method which begins test* will be run ---//

    public void test1() {
        assertTrue(true);
    }

    public void test2() {
        assertFalse(false);
    }

    public void test3() {
        assertTrue(true);
    }
}
