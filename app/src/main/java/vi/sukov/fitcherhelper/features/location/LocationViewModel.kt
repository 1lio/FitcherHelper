package vi.sukov.fitcherhelper.features.location

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    private val locationName = MutableLiveData<String>()

    init {
        locationName.value = "Search location..."
    }

    fun observeLocationName(owner: LifecycleOwner, observer: Observer<String>){
        locationName.observe(owner, observer)
    }


    fun setLocationName(name: String){
        locationName.value = name
    }

}