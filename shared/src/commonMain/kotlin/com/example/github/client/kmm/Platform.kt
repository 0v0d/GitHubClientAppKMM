package com.example.github.client.kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform