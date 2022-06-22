package com.android.mvvm.common

import java.util.UUID
import kotlin.random.Random

// Created because in most tests we care about one or two properties and ignore the rest
object RandomData {

    fun randomBoolean() = Random.nextBoolean()

    fun randomString() = UUID.randomUUID().toString()

    fun randomInt() = Random.nextInt()

}