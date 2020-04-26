package vi.sukov.fitcherhelper.core.repository

import vi.sukov.fitcherhelper.features.weather.WeatherDay
import vi.sukov.fitcherhelper.features.weather.WeatherItem

class FakeRepository {

    fun getWeatherOfDay(): WeatherDay {
        return WeatherDay(
            "+10 | +14",
            "ЮЗ",
            "750 мм р.с.",
            "3%",
            "7%",
            "06:44 | 18:26"
        )
    }

    fun getWeatherOfWeek(): List<WeatherItem> {
        return listOf(
            WeatherItem(
                null,
                "+10 | -14",
                "ПН"
            ),
            WeatherItem(
                null,
                "+20 | -14",
                "ВТ"
            ),
            WeatherItem(
                null,
                "+21 | -14",
                "СР"
            ),
            WeatherItem(
                null,
                "+30 | -14",
                "ЧТ"
            ),
            WeatherItem(
                null,
                "+25 | -14",
                "ПТ"
            ),
            WeatherItem(
                null,
                "+15 | -14",
                "СБ"
            ),
            WeatherItem(
                null,
                "+6 | -14",
                "ВС"
            )
        )
    }

    fun getLocation(): String = "Липецкая обл., г. Елец"

}