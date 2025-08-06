package com.example.khatibalamytask.data.local

import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.CACHED_KEY
import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.NULL_STRING
import jakarta.inject.Inject

class LocalDataSourceImpl @Inject constructor(val preferencesManager: PreferencesManager) : LocalDataSource {

    override fun cacheSearch(query: String) {
        preferencesManager.saveString(CACHED_KEY, query)
    }

    override fun getCachedSearch(): String = preferencesManager.getString(CACHED_KEY) ?: NULL_STRING


    override fun clearCache() {
        preferencesManager.clearKey(CACHED_KEY)
    }
}