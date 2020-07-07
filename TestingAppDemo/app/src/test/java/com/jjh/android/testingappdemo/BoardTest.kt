package com.jjh.android.testingappdemo

import org.junit.*

class BoardTest {

    companion object {
        @BeforeClass
        @JvmStatic
        internal fun beforeAll() {
            println("BeforeAll called")
        }
        @AfterClass
        @JvmStatic
        internal fun afterAll() {
            println("AfterAll called")
        }
    }

    private var board: Board? = null

    private val X = Counter(Counter.X)
    private val Y = Counter(Counter.Y)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        println("Before called (setup)")
        board = Board()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        println("After called (tearDown")
        board = null
    }

    @Test
    fun addMoveAMoveToTheBoardAt00() {
        val move = Move(0, 0, X)
        board!!.addMove(move)
        Assert.assertFalse("cell should contain a counter", board!!.isCellEmpty(0, 0))
        Assert.assertTrue("cell should contain counter", board!!.cellContains(X, 0, 0))
    }

    @Test
    fun whenCreatedCellsAreAllEmpty() {
        Assert.assertTrue("cell should be empty at start", board!!.isCellEmpty(0, 0))
    }

    @Test
    fun whenCreatedBoardIsNotFull() {
        Assert.assertFalse("board should not be full at start", board!!.isFull)
    }
}