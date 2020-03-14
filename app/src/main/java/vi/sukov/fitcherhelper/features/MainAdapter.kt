package vi.sukov.fitcherhelper.features

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import vi.sukov.fitcherhelper.R
import vi.sukov.fitcherhelper.core.BaseAdapter
import vi.sukov.fitcherhelper.core.BaseViewHolder
import vi.sukov.fitcherhelper.features.main.MainItem

class MainAdapter : BaseAdapter<MainItem, MainAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_container, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    inner class ViewHolder(v: View) : BaseViewHolder<MainItem>(v) {

        val txtName: TextView = itemView.findViewById(R.id.txtNameBlock)
       // val fragment: Fragment = itemView.findViewById(R.id.fragmentBlock)

        override fun bind(item: MainItem) {
            txtName.text = item.name
           // fragment.childFragmentManager.beginTransaction().replace(R.id.fragmentBlock, item.fragment)
        }
    }
}