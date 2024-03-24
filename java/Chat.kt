package com.example.assignment2

data class Chat(
    var sender: String,
    var receiver: String,
    var message: String
) {
    constructor() : this("", "", "")
}
