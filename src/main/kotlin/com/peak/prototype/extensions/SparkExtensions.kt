package com.peak.prototype.extensions

import com.peak.prototype.JsonModel
import com.peak.prototype.moshi
import spark.kotlin.RouteHandler

val RouteHandler.req
    get() = request

val RouteHandler.res
    get() = response

// Here's some f-ing Kotlin wizardry for you.
// This is a toJson(..) method added as a shortcut for
// creating a Moshi adapter for a certain object type
// as well as setting the response type to app/json.
// "inline" means that this code will be essentially
// "copied" inline at all locations where it is called
// throughout the code, which is a big efficiency boost
// that Kotlin introduces over Java compilation.
// The "reified T" essentially takes the generic type
// T and makes it reference-able within the function as
// an object. Otherwise, the compiler would yell at you
// because normally generic types are unable to be operated
// on at compile time. Oh and "T : JsonModel" means that
// T must be a subclass of JsonModel.
inline fun <reified T : JsonModel> RouteHandler.toJson(obj: T): String {
    response.type("application/json")
    return moshi.adapter(T::class.java).toJson(obj)
}