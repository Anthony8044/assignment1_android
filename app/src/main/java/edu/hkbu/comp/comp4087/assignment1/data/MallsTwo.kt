package edu.hkbu.comp.comp4087.assignment1.data

data class MallsTwo (
    val id: Int,
    val title: String,
    val deptId: String,
    val mall: String,
    var bookmarked:Boolean = false //This value may change in run-time
) {}