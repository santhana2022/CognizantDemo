package com.cogni.demo.ui.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cogni.demo.R
import com.cogni.demo.core.BaseFragment
import com.cogni.demo.databinding.CharacterListFragmentBinding
import com.cogni.demo.domain.dto.Character
import com.cogni.demo.ui.main.CharacterViewModel
import com.cogni.demo.ui.main.CharactersAction
import com.cogni.demo.ui.main.CharactersIntent
import com.cogni.demo.ui.main.CharactersState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment :
    BaseFragment<CharactersIntent, CharactersAction, CharactersState, CharacterViewModel>() {

    private lateinit var binding: CharacterListFragmentBinding

    private val adapter by lazy {
        CharacterViewAdapter {
            onCharacterClicked(it)
        }
    }

    private fun onCharacterClicked(character: Character) {
        dispatchIntent(CharactersIntent.SelectCharacter(character))

        Navigation.findNavController(binding.recycler)
            .navigate(R.id.action_mainFragment_to_detailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun getScreenViewModel(): CharacterViewModel {
        val viewModel: CharacterViewModel by activityViewModels()
        return viewModel
    }

    override fun initDATA() {
        dispatchIntent(CharactersIntent.LoadAllCharacters)
    }

    override fun initEVENT() {

    }

    override fun render(state: CharactersState) {
        when (state) {
            is CharactersState.Loading -> {
                Toast.makeText(requireContext(), "Loading Characters...", Toast.LENGTH_SHORT).show()
            }
            is CharactersState.ResultAllCharacter -> {
                adapter.submitList(state.characters)
            }
            is CharactersState.ResultSearchedCharacter -> {

            }
            is CharactersState.Exception -> {
                Toast.makeText(requireContext(), "Error Loading Characters!", Toast.LENGTH_SHORT)
                    .show()
            }
            is CharactersState.Finished -> {
                Toast.makeText(
                    requireContext(),
                    "Characters loaded successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
