package vi.sukov.fitcherhelper

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity.*
import vi.sukov.fitcherhelper.features.view.TopSheetBehavior

class MainActivity : AppCompatActivity() {

    /*    private var permissionsToRequest: ArrayList<String> = arrayListOf()
        private val permissionsRejected: ArrayList<String> = arrayListOf()
        private val permissions = ArrayList<String>()*/
    private lateinit var sheetBehavior: TopSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        sheetBehavior = TopSheetBehavior.from(test3)

        sheetBehavior.setTopSheetCallback(object : TopSheetBehavior.TopSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float, isOpening: Boolean?) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    TopSheetBehavior.STATE_HIDDEN -> { }
                    TopSheetBehavior.STATE_EXPANDED -> {
                        test4.invalidate()
                    }
                    TopSheetBehavior.STATE_COLLAPSED -> {
                        test4.invalidate()
                    }
                    TopSheetBehavior.STATE_DRAGGING -> {
                        test4.invalidate()
                    }
                    TopSheetBehavior.STATE_SETTLING -> {
                        test4.invalidate()
                    }
                }
            }
        })


        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        /*  permissions.add(permission.ACCESS_FINE_LOCATION)
          permissions.add(permission.ACCESS_COARSE_LOCATION)

          permissionsToRequest = findUnAskedPermissions(permissions)

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              if (permissionsToRequest.size > 0)
                  requestPermissions(permissionsToRequest.toTypedArray(), ALL_PERMISSIONS_RESULT)
          }*/
    }

    /* private fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
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
 */
    /*   private fun showMessageOKCancel(okListener: DialogInterface.OnClickListener) {
           AlertDialog.Builder(this@MainActivity)
               .setMessage("These permissions are mandatory for the application. Please allow access.")
               .setPositiveButton("OK", okListener)
               .setNegativeButton("Cancel", null)
               .create()
               .show()
       }

       companion object {
           private const val ALL_PERMISSIONS_RESULT = 101
       }*/
}