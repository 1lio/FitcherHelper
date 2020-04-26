package vi.sukov.fitcherhelper.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fr_weather_day.*
import vi.sukov.fitcherhelper.R
import vi.sukov.fitcherhelper.core.repository.FakeRepository
import vi.sukov.fitcherhelper.features.weather.WeatherDay

class DayWeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        super.onCreateView(inflater, group, state)
        return inflater.inflate(R.layout.fr_weather_day, group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = FakeRepository()
        val currentDay = repository.getWeatherOfDay()

        setDayInfo(currentDay)
    }

    private fun setDayInfo(day: WeatherDay) {

        txtTemperature.text = day.temperature
        txtWind.text = day.wing
        txtPressure.text = day.pressure
        txtHumidity.text = day.humidity
        txtCloudiness.text = day.cloudiness
        txtSunriseSunset.text = day.sunriseSunset
    }
}