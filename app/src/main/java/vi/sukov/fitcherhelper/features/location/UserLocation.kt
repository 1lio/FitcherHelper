package vi.sukov.fitcherhelper.features.location

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat


class UserLocation(val context: Context) : LocationListener {

    companion object {
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10f
        private const val MIN_TIME_BW_UPDATES: Long = 1000 * 60 * 1
    }

    private var checkGPS: Boolean = false
    private var checkNetwork: Boolean = false
     var canGetLocation: Boolean = false

     lateinit var loc: Location
    private var locManager: LocationManager =
        context.getSystemService(LOCATION_SERVICE) as LocationManager

     var latitude: Double = 0.0
     var longitude: Double = 0.0

    init {

        // GPS Status
        checkGPS = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        // Network status
        checkNetwork = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        try {

            if (!checkGPS && !checkNetwork) {
                Toast.makeText(context, "No Service Provider is available", Toast.LENGTH_SHORT)
                    .show()
            } else {
                canGetLocation = true

                if (checkGPS) {
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                        != PackageManager.PERMISSION_GRANTED
                    ) {

                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }

                    locManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES,
                        this
                    )

                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    latitude = loc.latitude
                    longitude = loc.longitude
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showSettingsAlert() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton(
            "Yes"
        ) { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            context.startActivity(intent)
        }
        alertDialog.setNegativeButton(
            "No"
        ) { dialog, _ -> dialog.cancel() }
        alertDialog.show()
    }

    fun stopListener() {
        if (locManager != null) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locManager.removeUpdates(this)
        }
    }

    override fun onLocationChanged(location: Location?) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

}