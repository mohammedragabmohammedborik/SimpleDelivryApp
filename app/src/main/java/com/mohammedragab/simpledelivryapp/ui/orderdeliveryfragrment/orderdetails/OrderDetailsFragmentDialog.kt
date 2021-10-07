package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.orderdetails
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.OrderDetailsBinding


class OrderDetailsFragmentDialog: BottomSheetDialogFragment() {

    private var binding: OrderDetailsBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // bind xml file to the fragment
        binding = OrderDetailsBinding.inflate(
            inflater,
            container,
            false)


        binding!!.closeId.setOnClickListener( View.OnClickListener {

            dismiss()
        })


        return binding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NO_TITLE, R.style.CustomBottomSheetDialogTheme);

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
