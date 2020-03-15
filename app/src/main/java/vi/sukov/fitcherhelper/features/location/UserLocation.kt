package vi.sukov.fitcherhelper.features.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog

class UserLocation(private val  context: Context) : LocationListener {

    // flag for GPS status
    private var isGPSEnabled = false

    // flag for network status
    private var isNetworkEnabled = false
    private var canGetLocation = false
    private var location: Location? = null
    private var latitude = 0.0
    private var longitude = 0.0

    // Declaring a Location Manager
    private var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        try {
            locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager?

            // getting GPS status
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                canGetLocation = true

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    Log.i("GPS", "from gps")
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BY_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                        )
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (locationManager != null) {
                            location =
                                locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                }
                // second get location from Network Provider
                if (isNetworkEnabled) {
                    Log.i("GPS", "from net")
                    locationManager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BY_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    Log.d("Network", "Network")
                    if (locationManager != null) {
                        location =
                            locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return location
    }


    override fun onLocationChanged(location: Location) {
    /*    this.location = location
        val ed: Editor = MainActivity.getSPref().edit()
        ed.putFloat("lat", location.getLatitude() as Float)
        ed.putFloat("lon", location.getLongitude() as Float)
        ed.commit()*/
    }


    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}

    fun getLatitude(): Double {
        if (location != null) latitude = location!!.latitude
        return latitude
    }


    fun getLongitude(): Double {
        if (location != null) longitude = location!!.longitude
        return longitude
    }

    fun stopUsingGPS() {
        if (locationManager != null) locationManager!!.removeUpdates(this@UserLocation)
    }

    fun showSettingsAlert() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            context.startActivity( Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
        alertDialog.setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        alertDialog.show()
    }

    fun canGetLocation() = canGetLocation

    companion object {

        // The minimum distance to change Updates in meters
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 5 // 5 meters

        // The minimum time between updates in milliseconds
        private const val MIN_TIME_BY_UPDATES: Long = 1000
    }

    init { getLocation() }
}