package edu.hkbu.comp.comp4087.assignment1.data

data class CoinsTwo (
    val id: Int,
    val title: String,
    val deptId: String,
    var bookmarked:Boolean = false //This value may change in run-time
) {}