package vi.sukov.fitcherhelper.features.location

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import vi.sukov.fitcherhelper.core.repository.FakeRepository

class LocationViewModel : ViewModel() {

    private val locationName = MutableLiveData<String>()
    private val repository = FakeRepository()

    init {
        locationName.value = repository.getLocation()
    }

    fun observeLocationName(owner: LifecycleOwner, observer: Observer<String>) {
        locationName.observe(owner, observer)
    }


    fun setLocationName(name: String) {
        locationName.value = name
    }

}