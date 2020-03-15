package vi.sukov.fitcherhelper.features.main

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.view_container.view.*
import vi.sukov.fitcherhelper.MainActivity
import vi.sukov.fitcherhelper.R

class FragmentContainer : ConstraintLayout {

    private lateinit var fm: FragmentManager

    private companion object {
        const val TAG: String = "FragmentContainer"
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    val activity = context as MainActivity

    var isHideBlock: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_container, this)

        btnHide.setOnClickListener {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentBlock, MainFragment())
                .commit()

            Log.d(TAG, "btnHideClick")
        }

    }

}