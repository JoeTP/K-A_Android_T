package com.example.khatibalamytask.core.utils.functions


fun dateAndTimeFormatter(date: String): Pair<String, String>? {
    //"2025-08-07T05:01:39Z"
    val date = date.substringBefore("T").replace("-", "/")
    val time = date.substringAfter("T").substringBefore("Z")
    return Pair(date, time)
}