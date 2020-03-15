package vi.sukov.fitcherhelper

import android.Manifest.permission
import android.annotation.TargetApi
import android.content.DialogInterface
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var permissionsToRequest: ArrayList<String> = arrayListOf()
    private val permissionsRejected: ArrayList<String> = arrayListOf()
    private val permissions = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        permissions.add(permission.ACCESS_FINE_LOCATION)
        permissions.add(permission.ACCESS_COARSE_LOCATION)

        permissionsToRequest = findUnAskedPermissions(permissions)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size > 0)
                requestPermissions(permissionsToRequest.toTypedArray(), ALL_PERMISSIONS_RESULT)
        }
    }

    private fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()
        for (perm in wanted) if (hasPermission(perm)) result.add(perm)
        return result
    }

    private fun hasPermission(permission: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) checkSelfPermission(permission) != PERMISSION_GRANTED else false

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (requestCode == ALL_PERMISSIONS_RESULT) {

            for (perms in permissionsToRequest) {
                if (hasPermission(perms)) permissionsRejected.add(perms)
            }

            if (permissionsRejected.size > 0 ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale((permissionsRejected[0]))) {
                        showMessageOKCancel(DialogInterface.OnClickListener { _, _ -> requestPermissions((permissionsRejected.toTypedArray()), ALL_PERMISSIONS_RESULT) })
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
            .setMessage("These permissions are mandatory for the application. Please allow access.")
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    companion object {
        private const val ALL_PERMISSIONS_RESULT = 101
    }
}