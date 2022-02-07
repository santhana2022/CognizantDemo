package com.cogni.demo.ui.main

import com.cogni.demo.data.common.CallResult
import com.cogni.demo.domain.dto.Characters
import com.cogni.demo.domain.dto.Quotes

/**
 * Result --> State
 */
fun CallResult<Characters>.reduce(): CharactersState {
    return when (this) {
        is CallResult.Success -> {
            CharactersState.ResultAllCharacter(this.data)
        }
        is CallResult.Error -> {
            CharactersState.Exception(exception)
        }
        is CallResult.Loading -> {
            CharactersState.Loading
        }
    }
}


fun CallResult<Quotes>.reduceQuote(): CharactersState {
    return when (this) {
        is CallResult.Success -> {
            CharactersState.ResultCharacterQuotes(this.data)
        }
        is CallResult.Error -> {
            CharactersState.Exception(exception)
        }
        is CallResult.Loading -> {
            CharactersState.Loading
        }
    }
}