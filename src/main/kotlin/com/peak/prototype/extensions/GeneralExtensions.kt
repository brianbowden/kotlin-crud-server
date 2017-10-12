package com.peak.prototype.extensions

import com.peak.prototype.JsonModel
import com.peak.prototype.moshi

// This extension applies to all classes inheriting from JsonModel
inline fun <reified T : JsonModel?> fromJson(json: String): T? {
    return try { moshi.adapter(T::class.java).fromJson(json) } catch (e: Exception) { null }
}