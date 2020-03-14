package vi.sukov.fitcherhelper.features.location

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.location_view.view.*
import vi.sukov.fitcherhelper.MainActivity
import vi.sukov.fitcherhelper.R

class LocationView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private val activity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[LocationViewModel::class.java]

    init {
        LayoutInflater.from(context).inflate(R.layout.location_view, this)
        viewModel.observeLocationName(activity, Observer { txtLocation.text = it })


        this.setOnClickListener {
            viewModel.setLocationName("Elets Lipetsk oblst.")
        }
    }

}