package com.baddevelopergames.eventsapp.eventslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.main.MainActivity
import com.baddevelopergames.eventsapp.utils.*
import kotlinx.android.synthetic.main.fragment_events_page.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.Serializable

class EventsPageFragment : Fragment(), EventsAdapterInteractions {
    companion object {
        @JvmStatic
        fun newInstance(list: List<EventsAppItem>) = EventsPageFragment().apply {
            val args = Bundle()
            args.putList(list)
            arguments = args
        }

        private const val TAG_LIST = "TAG_LIST"
    }

    private val adapter: EventsAdapter by inject { parametersOf(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(argumentsNonNull().getList())
    }

    private fun initAdapter(list: MutableList<EventsAppItem>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
        RecyclerViewBuilder.Builder(contextNonNull())
            .with(events_recycler_view)
            .setAdapter(adapter)
            .build()
    }

    override fun showMessage(message: String) {
        SnackbarHelper.showSnackbar(snackbar_container, message)
    }

    private fun Bundle.putList(list: List<EventsAppItem>) {
        this.putSerializable(TAG_LIST, list as Serializable)
    }

    @Suppress("UNCHECKED_CAST")
    private fun Bundle.getList() = get(TAG_LIST) as MutableList<EventsAppItem>

    override fun onImageClicked(item: EventsAppItem) {
        (activityNonNull() as MainActivity).onImageClicked(item.imageUrl)
    }
}