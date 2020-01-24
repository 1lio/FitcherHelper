package vi.sukov.fitcherhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView

class WeatherAdapter : BaseAdapter<WeatherItem, WeatherAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    inner class ViewHolder(v: View) : BaseViewHolder<WeatherItem>(v) {

        val icon: AppCompatImageView = itemView.findViewById(R.id.icon)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val day: TextView = itemView.findViewById(R.id.day)

        override fun bind(item: WeatherItem) {
            icon.setImageResource(R.drawable.ic_c3)
            temperature.text = item.temperature
            day.text = item.day
        }
    }
}