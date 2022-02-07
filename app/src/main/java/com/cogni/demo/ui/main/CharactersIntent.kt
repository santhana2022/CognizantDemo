package com.cogni.demo.ui.main

import com.cogni.demo.core.ViewIntent
import com.cogni.demo.domain.dto.Character

sealed class CharactersIntent : ViewIntent {
    object LoadAllCharacters : CharactersIntent()
    data class SearchCharacter(val name: String) : CharactersIntent()
    data class SelectCharacter(val character: Character) : CharactersIntent()

    // Detail screen
    object LoadSelectedCharacter : CharactersIntent()
    data class LoadCharacterQuotes(val character: Character? = null) : CharactersIntent()
}