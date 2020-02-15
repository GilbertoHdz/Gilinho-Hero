package mx.com.ghg.heroes.characters.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.ghg.heroes.R
import mx.com.ghg.heroes.characters.HeroesListUiModel.HeroDetail
import mx.com.ghg.heroes.characters.HeroesListViewModel
import mx.com.ghg.heroes.characters.view.CharactersAdapter.CharactersViewHolder
import mx.com.ghg.heroes.databinding.HeroesListCharactersItemBinding

class CharactersAdapter(
  private val viewModel: HeroesListViewModel
) : RecyclerView.Adapter<CharactersViewHolder>() {

  private val items = mutableListOf<HeroDetail>()

  private fun ViewGroup.inflate(
    layoutResId: Int
  ): View = LayoutInflater.from(this.context).inflate(layoutResId, this, false)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = CharactersViewHolder(
    parent.inflate(R.layout.heroes_list_characters_item)
  )

  override fun getItemCount(): Int= items.size

  override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
    holder.bindTo(
      uiModel = items[position],
      viewModel = viewModel
    )
  }

  fun updateData(heroes: List<HeroDetail>) {
    items.clear()
    items.addAll(heroes)

    notifyDataSetChanged()
  }

  class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = HeroesListCharactersItemBinding.bind(itemView)

    fun bindTo(
      uiModel: HeroDetail,
      viewModel: HeroesListViewModel
    ) {
      binding.item = uiModel
      binding.viewModel = viewModel
    }
  }
}