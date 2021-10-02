package edu.hkbu.comp.comp4087.assignment1.data

object SampleData {
    val DEPT = listOf(
        Range("Computer Science", "COMP"),
        Range("Communication Studies", "COMS")
    )
    val EVENT = listOf(
        Inrange(1, "COMP Welcome speech", "COMP"),
        Inrange(2, "COMP Exit speech", "COMP"),
        Inrange(3, "TV Show", "COMS"),
        Inrange(4, "TED Talk", "COMS")
    )
}