package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.delivryprocess
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.ClientDetailsBinding
import com.mohammedragab.simpledelivryapp.databinding.OrderDetailsBinding
import com.mohammedragab.simpledelivryapp.presentationlayer.ShareDataBetweenFragmentViewModel


class ClientDetailsFragmentDialog: BottomSheetDialogFragment() {

    private var binding: ClientDetailsBinding?=null

    private val shareDataBetweenFragmentViewModel:ShareDataBetweenFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // bind xml file to the fragment
        binding = ClientDetailsBinding.inflate(
            inflater,
            container,
            false)


        binding!!.closeId.setOnClickListener( View.OnClickListener {
            shareDataBetweenFragmentViewModel.startNavigate(true)
            dismiss()
        })


        return binding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NO_TITLE, R.style.CustomBottomSheetDialogThemeC);

    }



    override fun onAttach(activity: Context) {
        super.onAttach(activity)
      //  (activity as MainActivity).homeComponent.inject(this)

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null

    }
}
