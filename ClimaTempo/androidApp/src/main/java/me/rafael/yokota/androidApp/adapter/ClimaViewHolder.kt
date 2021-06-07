package adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.rafael.yokota.androidApp.databinding.RowItemWeatherBinding
import me.rafael.yokota.androidApp.util.DateFormatter
import me.rafael.yokota.shared.constants.Constants
import me.rafael.yokota.shared.model.Daily

class ClimaViewHolder(private var binding: RowItemWeatherBinding, var daily: Daily? = null) :

RecyclerView.ViewHolder(binding.root) {
    private val formatter = DateFormatter()
    fun bindData(daily: Daily) {
        binding.dateWeather.text = formatter.formatDate(daily.dt.toString())
        binding.description.text = daily.weather[0].description
        Picasso.get().load(Constants.API.basUrlIcon+daily.weather[0].icon+"@2x.png").into(binding.icon);
        binding.minimax.text = formatter.getMinMaxFormatString(daily.temp.min.toString(), daily.temp.max.toString())


    }

}
