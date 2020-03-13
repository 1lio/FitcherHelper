package vi.sukov.fitcherhelper.features.weather

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.test.*
import vi.sukov.fitcherhelper.R
import java.util.ArrayList

class WeatherFragment : Fragment() {

    private val countDays = 31

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WeatherAdapter()

        recycler_weather.adapter = adapter
        recycler_weather.itemAnimator = DefaultItemAnimator()

        val list: List<WeatherItem> = listOf(
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

        adapter.addAllAndNotify(list)

        initLineView(line_view)
        randomSet(line_view)
    }


    private fun initLineView(lineView: LineView) {

        val test = ArrayList<String>()

        for (i in 0 until countDays) {
            test.add((i + 1).toString())
        }

        lineView.setBottomTextList(test)
        lineView.setColorArray(intArrayOf(Color.parseColor("#473770")))
        lineView.setDrawDotLine(true)
        lineView.setShowPopup(LineView.SHOW_POPUPS_All)

    }


    private fun randomSet(lineView: LineView) {

        val dataList = ArrayList<Int>()

        for (i in 0 until countDays) dataList.add((Math.random() * 700).toInt())

        val dataLists = ArrayList<ArrayList<Int>>()
        dataLists.add(dataList)

        lineView.setDataList(dataLists)
    }
}