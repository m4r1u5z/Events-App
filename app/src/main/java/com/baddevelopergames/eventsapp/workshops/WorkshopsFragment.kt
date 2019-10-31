package com.baddevelopergames.eventsapp.workshops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.baddevelopergames.eventsapp.main.MainActivity
import com.baddevelopergames.eventsapp.eventslist.EventsAdapter
import com.baddevelopergames.eventsapp.eventslist.EventsAdapterInteractions
import com.baddevelopergames.eventsapp.utils.*
import kotlinx.android.synthetic.main.fragment_workshops.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class WorkshopsFragment : Fragment(), EventsAdapterInteractions, WorkshopsPresenter.WorkshopsView {
    companion object {
        @JvmStatic
        fun newInstance() = WorkshopsFragment()
    }

    private val presenter: WorkshopsPresenter by injectFragment()
    private val adapter: EventsAdapter by inject { parametersOf(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workshops, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }

    override fun showMessage(message: String) {
        SnackbarHelper.showSnackbar(snackbar_container, message)
    }

    override fun onImageClicked(item: EventsAppItem) {
        (activityNonNull() as MainActivity).onImageClicked(item.imageUrl)
    }

    override fun initRecyclerView(list: MutableList<EventsAppItem>) {
        adapter.list = list
        adapter.notifyDataSetChanged()

        RecyclerViewBuilder.Builder(contextNonNull())
            .with(workshops_rv)
            .setAdapter(adapter)
            .build()
    }
}
