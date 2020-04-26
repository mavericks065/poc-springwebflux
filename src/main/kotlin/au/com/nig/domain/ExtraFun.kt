package au.com.nig.domain

import java.util.*

fun <T> Optional<T>.unwrap(msg: String): T = orElseThrow { RuntimeException(msg) }
fun <T> Optional<T>.unwrap(): T = orElse(null)
