package vi.sukov.fitcherhelper.features.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.fr_weather_week.*
import vi.sukov.fitcherhelper.R
import vi.sukov.fitcherhelper.core.repository.FakeRepository

class WeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        super.onCreateView(inflater, group, state)
        return inflater.inflate(R.layout.fr_weather_week, group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WeatherAdapter()
        weekWeatherRecycler.adapter = adapter
        weekWeatherRecycler.itemAnimator = DefaultItemAnimator()

        val repository = FakeRepository()
        val list = repository.getWeatherOfWeek()

        adapter.addAllAndNotify(list)
    }
}