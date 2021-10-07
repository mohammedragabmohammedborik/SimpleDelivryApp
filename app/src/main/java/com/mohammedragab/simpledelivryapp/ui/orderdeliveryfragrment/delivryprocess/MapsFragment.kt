package com.mohammedragab.simpledelivryapp.ui.orderdeliveryfragrment.delivryprocess

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.ConfirmDeliveryPositionDialogBinding
import com.mohammedragab.simpledelivryapp.databinding.FragmentMapsBinding
import com.mohammedragab.simpledelivryapp.databinding.PaymentConfirmationBinding
import com.mohammedragab.simpledelivryapp.databinding.SuccessOrderDialogBinding
import com.mohammedragab.simpledelivryapp.presentationlayer.ShareDataBetweenFragmentViewModel
import com.mohammedragab.simpledelivryapp.ui.MainActivity
import com.mohammedragab.simpledelivryapp.utility.AnimationUtils
import com.mohammedragab.simpledelivryapp.utility.MapUtils

class MapsFragment : Fragment() {

    private var binding:FragmentMapsBinding?=null
    private lateinit var map: GoogleMap
    var PERMISSION_ALL = 1
    var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private  var polylineOptions:PolylineOptions?=null
    private var polyline: Polyline? = null
    private var originMarker: Marker? = null
    private var destinationMarker: Marker? = null
    private var movingCabMarker: Marker? = null
    private var previousLatLng: LatLng? = null
    private var currentLatLng: LatLng? = null
    private var valueAnimator: ValueAnimator? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val shareDataBetweenFragmentViewModel: ShareDataBetweenFragmentViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map=googleMap
        findNavController().navigate(R.id.to_client_details)
        try {

            showPath(MapUtils.getListOfLocations())

        }catch (ex:Exception){}
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasPermissions(requireContext(), *PERMISSIONS)) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        }


    }
    fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMapsBinding.inflate(layoutInflater,container,false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        initObserver()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ALL) {
            if (grantResults.size > 0) {

                for (item in permissions.indices) {
                    if (grantResults[item] == PackageManager.PERMISSION_GRANTED) {

//                        getCurrentLocation()
//                        foregroundOnlyLocationService?.subscribeToLocationUpdates()


                    } else {
                 //       Toast.makeText(requireContext(), "You will dissimss this features", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
    }

    // #######  .....  . . . . . . . . . . . .. .
    private fun showPath(latLngList: MutableList<LatLng>) {
//        if (polyline!=null){
//            polyline!!.remove()
//        }


        val builder = LatLngBounds.Builder()
        for (latLng in latLngList) {
            builder.include(latLng)
        }
        val bounds = builder.build()
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 2))

//        val points = polyline!!.points
//        points.addAll(latLngList)
//        polyline!!.points = points

        if (polylineOptions==null) {
            polylineOptions = PolylineOptions()
            polylineOptions!!.color(Color.parseColor("#0066CC"))
            polylineOptions!!.width(10f)
            polylineOptions!!.addAll(latLngList)


            //  map.addPolyline(polylineOptions).remove()

            polyline=  map.addPolyline(polylineOptions)
        }else{
            polyline!!.points = latLngList
        }

        if (originMarker!=null){
            originMarker!!.remove()
        }

        destinationMarker = addOriginDestinationMarkerAndGet(latLngList[latLngList.size - 1],"Destination")
        destinationMarker?.setAnchor(0.5f, 0.5f)
        destinationMarker?.isFlat=true







    }
    private  fun initObserver(){
        shareDataBetweenFragmentViewModel.startNavigate.observe(viewLifecycleOwner, Observer {
         it?.let {
             if (it) {
                // Log.w("TAG", "initObserver:  startna" )
                    try {
                        showMovingCab(MapUtils.getListOfLocations())

                    }catch (ex:Exception){

                    }
             }else{
                 shareDataBetweenFragmentViewModel.startNavigate(false)
             }
         }


        })
    }
    // show path2



    private fun addOriginDestinationMarkerAndGet(latLng: LatLng, title: String): Marker? {
        val bitmapDescriptor =
            BitmapDescriptorFactory.fromBitmap(
                MapUtils.getOriginDestinationMarkerBitmap(
                    requireActivity()
                )
            )
        return map?.addMarker(
            MarkerOptions().position(latLng).title(title).flat(true).icon(bitmapDescriptor)
        )
    }

    private fun showMovingCab(cabLatLng: List<LatLng>) {
        updateCarLocation(cabLatLng[0])

        handler = Handler()
        var index = 0
       runnable = Runnable {
            run {

                if (index < cabLatLng.size-1) {

                    updateCarLocation(cabLatLng[index])


                    handler.postDelayed(runnable, 3000)
                    ++index
               } else {
                    showDialogArrived(requireActivity())

                    map.clear()


                //    Toast.makeText(requireActivity(), "Trip Ends", Toast.LENGTH_LONG).show()
              }
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun updateCarLocation(latLng: LatLng) {

        if (movingCabMarker == null) {
            movingCabMarker = addCarMarkerAndGet(latLng)
        }
        if (previousLatLng == null) {
            currentLatLng = latLng
            previousLatLng = currentLatLng
            movingCabMarker?.position = currentLatLng
            movingCabMarker?.setAnchor(0.5f, 0.5f)
            animateCamera(previousLatLng!!, currentLatLng!!)
        } else {
            previousLatLng = currentLatLng
            currentLatLng = latLng

            if(valueAnimator ==null) {
                valueAnimator = AnimationUtils.carAnimator()
            }
           // valueAnimator!!.duration=1000

            valueAnimator!!.addUpdateListener { va ->
                if (currentLatLng != null && previousLatLng != null) {
                    val multiplier = va.animatedFraction
                    val nextLocation = LatLng(
                        multiplier * currentLatLng!!.latitude + (1 - multiplier) * previousLatLng!!.latitude,
                        multiplier * currentLatLng!!.longitude + (1 - multiplier) * previousLatLng!!.longitude
                    )
                    movingCabMarker?.position = nextLocation
                    val rotation = MapUtils.getRotation(previousLatLng!!, nextLocation)
                    if (!rotation.isNaN()) {
                        movingCabMarker?.rotation = rotation
                    }
                    movingCabMarker?.setAnchor(0.5f, 0.5f)
                    animateCamera(previousLatLng!!, nextLocation)
                    Log.w("TAG", "updateCarLocation: ${latLng.latitude}" )
                }

            }


            // if(!checkAnimationStart){
            valueAnimator!!.start()
            //      checkAnimationStart=true
            //   }



        }
    }

    private fun addCarMarkerAndGet(latLng: LatLng): Marker? {

        val bitmapDescriptor =
            BitmapDescriptorFactory.fromBitmap(MapUtils.getCarBitmap(requireActivity()))
        return map?.addMarker(
            MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )
    }
    private fun animateCamera(clatLng: LatLng, platLng: LatLng) {
        // Log.w(TAG, "animateCamera: ${getBearing(platLng, clatLng)}" )
        val cameraPosition = CameraPosition.Builder()
        cameraPosition.target(platLng).bearing(getBearing(clatLng, platLng)).tilt(0F)
//                                   // Sets the tilt of the camera to 30 degrees
        var chez = false
        if (!chez) {
            cameraPosition.zoom(15.5f)
            chez = true
        }

        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition.build()))

    }
    private fun animateCamera(position: LatLng) {
        val cameraPosition = CameraPosition.Builder()
        cameraPosition.target(position).bearing(0F).tilt(0F)
//                                   // Sets the tilt of the camera to 30 degrees
        var chez = false
        if (!chez) {
            cameraPosition.zoom(15.5f)
            chez = true
        }

        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition.build()))

    }
    private fun getBearing(begin: LatLng, end: LatLng): Float {
        val dLon = end.longitude - begin.longitude
        val x = Math.sin(Math.toRadians(dLon)) * Math.cos(Math.toRadians(end.latitude))
        val y = (Math.cos(Math.toRadians(begin.latitude)) * Math.sin(Math.toRadians(end.latitude))
                - Math.sin(Math.toRadians(begin.latitude)) * Math.cos(Math.toRadians(end.latitude)) * Math.cos(
            Math.toRadians(dLon)
        ))
        val bearing = Math.toDegrees(Math.atan2(x, y))
        return bearing.toFloat()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }

    fun showDialogArrived(context: Context){


    val    dialogBinding = ConfirmDeliveryPositionDialogBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
       val  dilog=    MaterialAlertDialogBuilder(context).setView(dialogBinding.root)

            .show()



        dialogBinding.cancel.setOnClickListener {
            Toast.makeText(requireActivity(), getString(R.string.not_applicavle), Toast.LENGTH_SHORT).show()
           // dilog.dismiss()
        }
        dialogBinding.confirm.setOnClickListener {
            shareDataBetweenFragmentViewModel.setBase(true)
            object : CountDownTimer(3000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {

                    showDialogPayment(requireActivity())

                }
            }.start()

            dilog.dismiss()


        }


        // Single-choice items (initialized with checked item)
    }
    //
    fun showDialogPayment(context: Context){


        val    dialogBinding = PaymentConfirmationBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
        val  dilog=    MaterialAlertDialogBuilder(context).setView(dialogBinding.root)

            .show()


        dialogBinding.imageView.setOnClickListener {
            Toast.makeText(requireActivity(), getString(R.string.not_applicavle), Toast.LENGTH_SHORT).show()

        }

        dialogBinding.button.setOnClickListener {
            val valueAmount=dialogBinding.editAmount.text.toString().trim()
            if (!valueAmount.isEmpty()){
                dialogBinding.editAmount.error=getString(R.string.must_filled)
                dilog.dismiss()

                showDialogSuccess(requireActivity())
            }
        }

    }


    /**
     * show dialog success
     */

    fun showDialogSuccess(context: Context){


        shareDataBetweenFragmentViewModel.paymrntSuccess(true)

        val    dialogBinding = SuccessOrderDialogBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
        val  dilog=    MaterialAlertDialogBuilder(context).setView(dialogBinding.root)

            .show()
        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                dilog.dismiss()

                startActivity(Intent(requireActivity(),MainActivity::class.java))


            }
        }.start()

        dialogBinding.textView14.setOnClickListener {


            }
        }


    }

