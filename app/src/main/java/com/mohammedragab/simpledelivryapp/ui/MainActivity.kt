package com.mohammedragab.simpledelivryapp.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.mohammedragab.simpledelivryapp.MyApplication
import com.mohammedragab.simpledelivryapp.NotificationReceiver
import com.mohammedragab.simpledelivryapp.R
import com.mohammedragab.simpledelivryapp.databinding.ActivityMainBinding
import com.mohammedragab.simpledelivryapp.di.HomeComponent
import com.mohammedragab.simpledelivryapp.presentationlayer.ShareDataBetweenFragmentViewModel
import com.mohammedragab.simpledelivryapp.utility.MapUtils
import com.mohammedragab.simpledelivryapp.utility.Utile

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "exampleChannel"
    private  var  binding: ActivityMainBinding?=null
    lateinit var  homeComponent: HomeComponent
    private lateinit var  navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val shareDataBetweenFragmentViewModel: ShareDataBetweenFragmentViewModel by viewModels()


    private var notificationManager: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent=    (application as MyApplication).applicationComponent.homeComponent().create()
        homeComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
       Utile.setInsects(binding!!.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setSupportActionBar(binding!!.toolbarId)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_fragment)

        initObserver()
     appBarConfiguration = AppBarConfiguration(setOf(R.id.deliveryOrdersFragment,R.id.mapsFragment))

        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){

                R.id.deliveryOrdersFragment->{

                    binding!!.toolbarId.visibility= View.VISIBLE

                }
                R.id.mapsFragment->{

                    binding!!.toolbarId.visibility= View.GONE

                }





            }
        })
        

       setupActionBarWithNavController(navController, appBarConfiguration)

        notificationManager = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Example Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(channel)
        }

    }
    private  fun initObserver(){
        shareDataBetweenFragmentViewModel.arrived.observe(this, Observer {
            it?.let {
                if (it) {
                    try {
                        showNotification(getString(R.string.pyment_succes_text))

                    }catch (ex:Exception){

                    }                }else{
                    shareDataBetweenFragmentViewModel.setBase(false)
                }
            }


        })

        shareDataBetweenFragmentViewModel.paymentSuccess.observe(this, Observer {
            it?.let {
                if (it) {
                    try {
                        showNotification(getString(R.string.pyment_succes_text))

                    }catch (ex:Exception){

                    }
                }else{
                    shareDataBetweenFragmentViewModel.setBase(false)
                }
            }


        })
    }

    open fun showNotification(body:String) {
       // Toast.makeText(this, "mfkdlgnflk", Toast.LENGTH_SHORT).show()
        val collapsedView = RemoteViews(packageName,
            R.layout.notification_collapsed
        )
        val expandedView = RemoteViews(packageName,
            R.layout.notification_expanded
        )
        val clickIntent = Intent(this, NotificationReceiver::class.java)
        val clickPendingIntent = PendingIntent.getBroadcast(this,
            0, clickIntent, 0)
        collapsedView.setTextViewText(R.id.text_view_collapsed_1, getString(R.string.new_message))
        collapsedView.setTextViewText(R.id.text_view_collapsed_2, body)


        expandedView.setImageViewResource(
            R.id.image_view_expanded,
            R.drawable.pharmcy_image
        )
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification: Notification = NotificationCompat.Builder(this, MyApplication().CHANNEL_ID)
            .setSmallIcon(R.drawable.motor_logo)
            .setCustomContentView(collapsedView)
            .setSound(defaultSoundUri)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)


            .setCustomBigContentView(expandedView).setCategory(NotificationCompat.CATEGORY_CALL)

            .setOngoing(true)


            //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
            .build()
        notificationManager!!.notify(1, notification)
    }

}