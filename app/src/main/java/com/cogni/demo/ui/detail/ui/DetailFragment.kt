package com.cogni.demo.ui.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.cogni.demo.core.BaseFragment
import com.cogni.demo.databinding.DetailFragmentBinding
import com.cogni.demo.ui.main.CharacterViewModel
import com.cogni.demo.ui.main.CharactersAction
import com.cogni.demo.ui.main.CharactersIntent
import com.cogni.demo.ui.main.CharactersState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<CharactersIntent, CharactersAction, CharactersState, CharacterViewModel>() {

    private lateinit var viewBinding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = DetailFragmentBinding.inflate(inflater)
        viewBinding.detailImage.setImageResource(android.R.drawable.ic_lock_idle_low_battery)

        (activity as AppCompatActivity).let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        return viewBinding.root
    }

    override fun getScreenViewModel(): CharacterViewModel {
        return ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
    }

    override fun render(state: CharactersState) {
        when (state) {
            is CharactersState.ResultSelectedCharacter -> {
                viewBinding.detailName.text = state.character?.name
                state.character?.img?.let { imageURI ->
                    viewBinding.detailImage.load(imageURI) {
                        placeholder(android.R.drawable.ic_lock_idle_charging)
                    }
                }
            }
            is CharactersState.ResultCharacterQuotes -> {
                viewBinding.characterQuote.text = state.quotes[0].quote
            }
        }
    }

    override fun initDATA() {
        dispatchIntent(CharactersIntent.LoadSelectedCharacter)
        dispatchIntent(CharactersIntent.LoadCharacterQuotes())
    }

    override fun initEVENT() {
    }
}