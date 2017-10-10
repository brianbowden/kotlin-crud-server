package com.peak.prototype

// A sealed class is similar in concept to an enum; it also seals the
// inheriting class so that it can not inherit from another class.
// I am using the sealed class here to make sure that our models for
// json generation don't accidentally expose unintended elements,
// and the `toJson(..)` method on RouteHandler only serializes models
// that extend JsonModel in order to avoid inadvertent data leakage.

sealed class JsonModel

data class User(var name: String, var email: String? = null) : JsonModel()
data class UsersResponse(var users: MutableList<User>) : JsonModel()