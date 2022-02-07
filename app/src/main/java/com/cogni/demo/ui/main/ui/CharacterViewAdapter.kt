package com.cogni.demo.ui.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cogni.demo.R
import com.cogni.demo.databinding.CharacterItemBinding
import com.cogni.demo.domain.dto.Character

/**
 *
 */
class CharacterViewAdapter(private val onCharacterClick: (selectedChar: Character) -> Unit) :
    ListAdapter<Character, CharacterViewAdapter.CharacterViewHolder>(
        CharactersItemDiffCallback()
    ) {

    class CharacterViewHolder(
        private val binding: CharacterItemBinding,
        val onCharacterClick: (selectedChar: Character) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(characterData: Character, index: Int) {
            binding.characterItem = characterData
            binding.characterImage.setImageBitmap(characterData.imageData)
            val statusColor = if (characterData.status == "Alive") {
                android.R.color.holo_green_dark
            } else if (characterData.status == "Deceased") {
                android.R.color.holo_red_dark
            } else {
                android.R.color.holo_blue_dark
            }
            binding.characterStatusImage.setBackgroundResource(statusColor)
            binding.setClickListener {
                onCharacterClick(characterData)
            }
            binding.characterImage.load(characterData.img) {
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, onCharacterClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        getItem(position)?.let { item ->
            holder.itemView.tag = position
            holder.bind(item, position)
        }

    }
}

class CharactersItemDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem && oldItem.imageData == newItem.imageData

    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name && (newItem.imageData?.equals(oldItem.imageData)
            ?: true)
    }


}