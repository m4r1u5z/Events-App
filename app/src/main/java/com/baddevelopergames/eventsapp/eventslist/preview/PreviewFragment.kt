package com.baddevelopergames.eventsapp.eventslist.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.utils.activityNonNull
import com.baddevelopergames.eventsapp.utils.argumentsNonNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preview.*

class PreviewFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(imageUrl: String) = PreviewFragment().apply {
            val args = Bundle()
            args.putString(TAG_IMAGE_URL, imageUrl)
            arguments = args
        }

        private const val TAG_IMAGE_URL = "TAG_IMAGE_URL"
        const val TAG = "PreviewFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = argumentsNonNull().getString(TAG_IMAGE_URL)
        Picasso.get().load(imageUrl).into(preview_photo_view)
        preview_close.setOnClickListener {
            activityNonNull().supportFragmentManager.popBackStack()
        }
    }
}