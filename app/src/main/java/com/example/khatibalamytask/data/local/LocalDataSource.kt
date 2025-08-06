package com.example.khatibalamytask.data.local

interface LocalDataSource {

    fun cacheSearch(query : String)

    fun getCachedSearch() : String

    fun clearCache()
}