import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafilme.repository.model.Movie

class CustomViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)


class BookAdapterForFilm : ListAdapter<Movie, CustomViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieBinding.inflate(inflater, parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentMovie = getItem(position)
        val itemBinding = holder.binding as ItemBookBinding
        itemBinding.movie = currentMovie
        itemBinding.executePendingBindings()
    }
}