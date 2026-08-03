package org.example.user

data class NewUser(val username: String, val password: String)

data class User(val id: Int, val username: String, val password: String)

class UsernameTakenException : Exception()
