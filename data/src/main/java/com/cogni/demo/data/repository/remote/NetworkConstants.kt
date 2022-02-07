package com.cogni.demo.data.repository.remote

import android.net.Uri

/**
 * The Breaking Bad API is a collection of information on the Vince Gilligan series Breaking Bad, as well as his incredible spin-off Better Call Saul.
 * This site is designed to make it easy on the developer to see what HTTP requests are possible, and what information is available.
 */
const val BASE_URL = "https://www.breakingbadapi.com/api/"

const val PATH_CHARACTERS = "characters"
const val PATH_QUOTES = "quotes"
const val PATH_QUOTE = "quote"
const val PATH_RANDOM = "random"

const val CHARACTER_URL = BASE_URL + PATH_CHARACTERS
const val QUOTES_URL = BASE_URL + PATH_QUOTES
const val QUOTE_URL = BASE_URL + PATH_QUOTE

const val MAX_RETRIES = 3L
private const val INITIAL_BACKOFF = 2000L

fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)