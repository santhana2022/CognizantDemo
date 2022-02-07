package com.cogni.demo.ui.main

import com.cogni.demo.core.ViewAction
import com.cogni.demo.domain.dto.Character

sealed class CharactersAction : ViewAction {
    object LoadAllCharacters : CharactersAction()
    data class SearchCharacter(val name: String) : CharactersAction()
    data class SelectCharacter(val character: Character) : CharactersAction()

    // Detail screen
    object LoadSelectedCharacter : CharactersAction()
    data class LoadCharacterQuotes(val character: Character? = null) : CharactersAction()
}
