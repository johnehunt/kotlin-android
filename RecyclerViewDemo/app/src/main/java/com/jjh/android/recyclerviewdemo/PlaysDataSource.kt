package com.jjh.android.recyclerviewdemo

data class Play(val title: String, val year: Int)

class PlaysDataSource {

    private val data: List<Play> = listOf(
        Play("The Tempest", 1610),
        Play("The Comedy of Errors", 1598),
        Play("Much Ado About Nothing", 1599),
        Play("Hamlet", 1600),
        Play("Henry IV Part 1", 1597),
        Play("Henry IV Part 2", 1598),
        Play("Richard III", 1595),
        Play("King Lear", 1606),
        Play("Twelfth Night", 1601),
        Play("A midsummer Night's Dream", 15966),
        Play("Romeo and Juliet", 1594)
    )

    val size
       get() = data.size

    fun get(index: Int) = data[index]

}