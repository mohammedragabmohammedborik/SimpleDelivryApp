package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.util.Util
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.HomeLayoutBinding
import com.mohammedragab.simpledelivryapp.modellayer.ModelOrderDelivery
import com.mohammedragab.simpledelivryapp.presentationlayer.HomeViewModel
import com.mohammedragab.simpledelivryapp.presentationlayer.ViewModelFactory
import com.mohammedragab.simpledelivryapp.ui.MainActivity
import com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.adapter.ClickeDelivery
import com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.adapter.HomeAdapter
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DeliveryOrdersFragment : Fragment() {
    private  val TAG = "HomeFragment"
    lateinit var binding: HomeLayoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).homeComponent.inject(this)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeLayoutBinding.inflate(layoutInflater, container, false)
     val view=   binding.root

        homeViewModel.getCategoryAuctionHomeData()
        initAdapter()
        observer()
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    private  fun  initAdapter(){
        homeAdapter= HomeAdapter(requireActivity(),object: ClickeDelivery {

            override fun startDelivery(id: Int) {
                findNavController().navigate(R.id.to_map)
            }

            override fun priceDetails(model: ModelOrderDelivery) {
               findNavController().navigate(R.id.to_price_details)
            }

            override fun orderDetails(model: ModelOrderDelivery) {
               findNavController().navigate(R.id.to_order_details)
            }

            override fun callPhone(phne: String) {
                call(phne)
            }
        })

        binding.list.adapter=homeAdapter
        binding.list.setHasFixedSize(true)

    }
    fun call(phone: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + "$phone")
        startActivity(dialIntent)
    }
    private  fun  observer(){
        homeViewModel.orderlist.observe(viewLifecycleOwner, Observer {

            homeAdapter.submitList(it)
        })
                homeViewModel.loading.observe(viewLifecycleOwner, Observer {

//            if (it){
//                binding.progressId.progress.visibility=View.VISIBLE
//            }else{
//                binding.progressId.progress.visibility=View.GONE
//
//            }


        })


    }

}