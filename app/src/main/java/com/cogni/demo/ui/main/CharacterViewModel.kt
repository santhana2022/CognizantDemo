package com.cogni.demo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cogni.demo.core.BaseViewModel
import com.cogni.demo.data.repository.AppRepository
import com.cogni.demo.domain.dto.Character
import com.cogni.demo.domain.dto.Quotes
import com.cogni.demo.utils.launchOnUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel<CharactersIntent, CharactersAction, CharactersState>() {

    val selectedCharacterLiveData: LiveData<Character>
        get() = _selectedCharacterLiveData
    private val _selectedCharacterLiveData = MutableLiveData<Character>()

    val quotesLiveData: LiveData<Quotes>
        get() = _quotesLiveData
    private val _quotesLiveData = MutableLiveData<Quotes>()

    override fun handleAction(action: CharactersAction) {
        launchOnUI {
            when (action) {
                is CharactersAction.LoadAllCharacters -> {
                    appRepository.fetchAllCharacters().collect {
                        mState.postValue(it.reduce())
                    }
                }
                is CharactersAction.SearchCharacter -> {

                }
                is CharactersAction.SelectCharacter -> {
                    _selectedCharacterLiveData.value = action.character
                }
                is CharactersAction.LoadSelectedCharacter -> {
                    mState.value =
                        CharactersState.ResultSelectedCharacter(
                            _selectedCharacterLiveData.value
                        )
                }
                is CharactersAction.LoadCharacterQuotes -> {
                    appRepository.fetchSingleRandomQuote().collect {
                        mState.postValue(it.reduceQuote())
                    }
                }
            }
        }
    }

    override fun intentToAction(intent: CharactersIntent): CharactersAction {
        return when (intent) {
            is CharactersIntent.LoadAllCharacters -> {
                CharactersAction.LoadAllCharacters
            }
            is CharactersIntent.SearchCharacter -> {
                CharactersAction.SearchCharacter("abc")
            }
            is CharactersIntent.SelectCharacter -> {
                CharactersAction.SelectCharacter(intent.character)
            }
            is CharactersIntent.LoadSelectedCharacter -> {
                CharactersAction.LoadSelectedCharacter
            }
            is CharactersIntent.LoadCharacterQuotes -> {
                CharactersAction.LoadCharacterQuotes(selectedCharacterLiveData.value)
            }
        }
    }
}
