package com.cogni.demo.ui.main

import com.cogni.demo.core.ViewState
import com.cogni.demo.data.common.CallError
import com.cogni.demo.domain.dto.Character
import com.cogni.demo.domain.dto.Characters
import com.cogni.demo.domain.dto.Quotes

sealed class CharactersState : ViewState {
    object Loading : CharactersState()
    data class ResultAllCharacter(val characters: Characters) : CharactersState()
    data class ResultSearchedCharacter(val characters: Characters) : CharactersState()
    data class Exception(val apiError: CallError) : CharactersState()

    data class ResultSelectedCharacter(val character: Character? = null) : CharactersState()
    data class ResultCharacterQuotes(val quotes: Quotes) : CharactersState()
    object Finished : CharactersState()
}