package vi.sukov.fitcherhelper.features.location

import android.content.Context
import android.location.Geocoder
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.location_view.view.*
import vi.sukov.fitcherhelper.MainActivity
import vi.sukov.fitcherhelper.R
import vi.sukov.fitcherhelper.core.repository.LocalRepository
import java.util.*


class LocationView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private val activity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[LocationViewModel::class.java]
    private val repository = LocalRepository(activity)
    private val userLocation = UserLocation(context)

    init {
        LayoutInflater.from(context).inflate(R.layout.location_view, this)
        viewModel.observeLocationName(activity, Observer { txtLocation.text = it })

        checkLocation()

        val frameView = FrameLayout(context)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle("My title")
            .setMessage("Enter password")
            .setView(frameView)

        val alertDialog: AlertDialog = builder.create()

        val inflater: LayoutInflater = alertDialog.layoutInflater
        val dialoglayout: View = inflater.inflate(R.layout.alert_map, frameView)


        this.setOnClickListener {
            alertDialog.show()
        }
    }

    private fun checkLocation() {

        val isEmptyLocation: Boolean = repository.getLocation() == LOADING

        if (isEmptyLocation) {

            // Кидаем сообщение о поиске мета
            viewModel.setLocationName(context.getString(R.string.search_location))

            if (userLocation.canGetLocation()) {

                // При наличии прав грузим локацию, иначе просим права
                viewModel.setLocationName(getLocationName())

            } else {
                // userLocation.showSettingsAlert()
            }
        }
    }

    private fun getLocationName(): String {
        val geocoder = Geocoder(activity, Locale.ENGLISH)

        val latitude: Double = userLocation.getLatitude()
        val longitude: Double = userLocation.getLongitude()

        val location = geocoder.getFromLocation(latitude, longitude, 1)

        return try {
            Log.d("LOCATION", location.toString())
            "${location[0].locality}, ${location[0].adminArea}"

        } catch (e: Exception) {

            e.printStackTrace()
            // userLocation.showSettingsAlert()
            context.getString(R.string.search_location)
        }
    }

    private companion object {
        const val LOADING = "Loading..."
    }
}