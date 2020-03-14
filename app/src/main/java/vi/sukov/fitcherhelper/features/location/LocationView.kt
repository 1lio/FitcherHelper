package vi.sukov.fitcherhelper.features.location

import android.content.Context
import android.location.Geocoder
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.location_view.view.*
import vi.sukov.fitcherhelper.MainActivity
import vi.sukov.fitcherhelper.R
import java.util.*

class LocationView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private val activity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[LocationViewModel::class.java]
    private val userLocation: UserLocation = UserLocation(activity)

    init {
        LayoutInflater.from(context).inflate(R.layout.location_view, this)
        viewModel.observeLocationName(activity, Observer { txtLocation.text = it })

        this.setOnClickListener {

            val geocoder = Geocoder(activity, Locale.ENGLISH)

            val latitude: Double = userLocation.latitude
            val longitude: Double = userLocation.longitude
            val location = geocoder.getFromLocation(latitude, longitude, 1)

            if (userLocation.canGetLocation) {

                viewModel.setLocationName("${location[0].locality}, ${location[0].adminArea}")

            } else {
                userLocation.showSettingsAlert()
            }

        }
    }

}