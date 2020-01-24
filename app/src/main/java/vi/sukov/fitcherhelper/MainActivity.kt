package vi.sukov.fitcherhelper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val countDays = 31

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherRecycler: RecyclerView = findViewById(R.id.recycler_weather)
        val adapter = WeatherAdapter()

        weatherRecycler.adapter = adapter
        weatherRecycler.itemAnimator = DefaultItemAnimator()

        val list: List<WeatherItem> = listOf(
            WeatherItem(null, "+10 | -14", "ПН"),
            WeatherItem(null, "+20 | -14", "ВТ"),
            WeatherItem(null, "+21 | -14", "СР"),
            WeatherItem(null, "+30 | -14", "ЧТ"),
            WeatherItem(null, "+25 | -14", "ПТ"),
            WeatherItem(null, "+15 | -14", "СБ"),
            WeatherItem(null, "+6 | -14", "ВС")
        )

        adapter.addAllAndNotify(list)

        val lineView: LineView = findViewById(R.id.line_view)
        initLineView(lineView)
        randomSet(lineView)

        setSupportActionBar(bottom_bar)
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