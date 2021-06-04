package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.rafael.yokota.androidApp.R
import me.rafael.yokota.androidApp.databinding.RowItemWeatherBinding
import me.rafael.yokota.shared.model.Daily

class ClimaAdapter : RecyclerView.Adapter<ClimaViewHolder>() {

    private var mList: List<Daily> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClimaViewHolder {
        val rowItemWeatherBinding = RowItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClimaViewHolder(rowItemWeatherBinding)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: ClimaViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun updateList(list: List<Daily>){
        println(list.toString())
        mList= list
        notifyDataSetChanged()
    }

}