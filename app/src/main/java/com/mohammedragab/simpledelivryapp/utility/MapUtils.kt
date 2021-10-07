package com.mohammedragab.simpledelivryapp.utility

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.mohammedragab.simpledelivryapp.R
import kotlin.math.abs
import kotlin.math.atan

object MapUtils {

    fun getCarBitmap(context: Context): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_car_mab)
        return Bitmap.createScaledBitmap(bitmap, 50, 100, false)
    }

    fun getMarkerForSeclctionPosition(context: Context): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.marker)
        return Bitmap.createScaledBitmap(bitmap, 50, 100, false)
    }

    fun getOriginDestinationMarkerBitmap(context: Context): Bitmap {
//        val height = 20
//        val width = 20
//        val bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565)
//        val canvas = Canvas(bitmap)
//        val paint = Paint()
//        paint.color = Color.parseColor("#543CC6")
//        paint.style = Paint.Style.FILL
//        paint.isAntiAlias = true
//        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
        //   canvas.drawCircle(0F, 0F, width.toFloat(), height.toFloat(), paint)
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.marker)
        return Bitmap.createScaledBitmap(bitmap, 50, 100, false)
        return bitmap
    }
//    fun getOriginDestinationMarkerBitmap(context: Context): Bitmap {
//        val height = 40
//        val width = 40
//        val bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ALPHA_8)
//        val canvas = Canvas(bitmap)
//        val paint = Paint()
//        paint.color = Color.parseColor("#0064fe")
//        paint.style = Paint.Style.FILL
//        paint.isAntiAlias = true
//        val filter: ColorFilter = PorterDuffColorFilter(
//            ContextCompat.getColor(context, R.color.blue),
//            PorterDuff.Mode.SRC_IN
//        )
//        paint.colorFilter = filter
//        // canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
//        canvas.drawCircle(20F, 20F, 20F, paint)
//
//        return bitmap
//    }

    fun getRotation(start: LatLng, end: LatLng): Float {
        val latDifference: Double = abs(start.latitude - end.latitude)
        val lngDifference: Double = abs(start.longitude - end.longitude)
        var rotation = -1F
        when {
            start.latitude < end.latitude && start.longitude < end.longitude -> {
                rotation = Math.toDegrees(atan(lngDifference / latDifference)).toFloat()
            }
            start.latitude >= end.latitude && start.longitude < end.longitude -> {
                rotation = (90 - Math.toDegrees(atan(lngDifference / latDifference)) + 90).toFloat()
            }
            start.latitude >= end.latitude && start.longitude >= end.longitude -> {
                rotation = (Math.toDegrees(atan(lngDifference / latDifference)) + 180).toFloat()
            }
            start.latitude < end.latitude && start.longitude >= end.longitude -> {
                rotation =
                    (90 - Math.toDegrees(atan(lngDifference / latDifference)) + 270).toFloat()
            }
        }
        return rotation
    }

    /**
     * This function returns the list of locations of Car during the trip i.e. from Origin to Destination
     */
    fun getListOfLocations(): MutableList<LatLng> {
        val mutableListLatLang = mutableListOf<LatLng>()
//        mutableListLatLang!!.add(LatLng(29.29163,30.60311))
//        mutableListLatLang!!.add(LatLng(29.29147,30.60336))
//        mutableListLatLang!!.add(LatLng(29.29104,30.60404))
//        mutableListLatLang!!.add(LatLng(29.29071,30.60456))
//        mutableListLatLang!!.add(LatLng(29.29065,30.60466))
//        mutableListLatLang!!.add(LatLng(29.29051,30.60489))
//        mutableListLatLang!!.add(LatLng(29.28999,30.60571))
//        mutableListLatLang!!.add(LatLng(29.28994,30.6058))
//        mutableListLatLang!!.add(LatLng(29.2896,30.60633))
//        mutableListLatLang!!.add(LatLng(29.28916,30.60702))
//        mutableListLatLang!!.add(LatLng(29.28907,30.60716))
//        mutableListLatLang!!.add(LatLng(29.28863,30.60788))
//        mutableListLatLang!!.add(LatLng(29.28815,30.60862))
//        mutableListLatLang!!.add(LatLng(29.28806,30.60874))
//        mutableListLatLang!!.add(LatLng(29.2879,30.60894))
//        mutableListLatLang!!.add(LatLng(29.2875,30.60945))
//        mutableListLatLang!!.add(LatLng(29.2871,30.60996))
//        mutableListLatLang!!.add(LatLng(29.28698,30.6101))
//        mutableListLatLang!!.add(LatLng(29.28673,30.61041))
//        mutableListLatLang!!.add(LatLng(29.28618,30.6111))
//        mutableListLatLang!!.add(LatLng(29.28612,30.61117))
//        mutableListLatLang!!.add(LatLng(29.28494,30.61264))
//        mutableListLatLang!!.add(LatLng(29.2847,30.61293))
//        mutableListLatLang?.add(LatLng(29.28466,30.61299))
//        mutableListLatLang?.add(LatLng(29.28437,30.61331))
//        mutableListLatLang?.add(LatLng (29.28428,30.61341))
//        mutableListLatLang?.add(LatLng (29.28417,30.61353))
//        mutableListLatLang?.add(LatLng (29.28399,30.61373))
//        mutableListLatLang?.add(LatLng (29.28364,30.61415))
//        mutableListLatLang?.add(LatLng (29.28353,30.61429))
//        mutableListLatLang?.add(LatLng (29.28337,30.61449))
//        mutableListLatLang?.add(LatLng (29.28334,30.61452))
        mutableListLatLang?.add(LatLng (29.28303,30.61493))
        mutableListLatLang?.add(LatLng (29.28296,30.61503))
        mutableListLatLang?.add(LatLng (29.28283,30.61524))
        mutableListLatLang?.add(LatLng (29.28281,30.61528))
        mutableListLatLang?.add(LatLng (29.28278,30.61535))
        mutableListLatLang?.add(LatLng (29.2827,30.61552))
        mutableListLatLang?.add(LatLng (29.28262,30.61572))
        mutableListLatLang?.add(LatLng (29.28254,30.616))
        mutableListLatLang?.add(LatLng (29.28248,30.61629))
        mutableListLatLang?.add(LatLng (29.28236,30.61712))
        mutableListLatLang?.add(LatLng (29.28225,30.61795))
        mutableListLatLang?.add(LatLng (29.28212,30.61902))
        mutableListLatLang?.add(LatLng (29.28208,30.61949))
        mutableListLatLang?.add(LatLng (29.28207,30.61958))
//        mutableListLatLang?.add(LatLng (29.28206,30.61964))
//        mutableListLatLang?.add(LatLng (29.28204,30.61972))
//        mutableListLatLang?.add(LatLng (29.282,30.61984))
//        mutableListLatLang?.add(LatLng (29.28197,30.61994))
//        mutableListLatLang?.add(LatLng (29.28194,30.62001))
//        mutableListLatLang?.add(LatLng (29.28186,30.62022))
//        mutableListLatLang?.add(LatLng (29.28174,30.62045))
//        mutableListLatLang?.add(LatLng (29.28154,30.62073))
//        mutableListLatLang?.add(LatLng (29.28131,30.62098))
//        mutableListLatLang?.add(LatLng (29.28099,30.62127))
//        mutableListLatLang?.add(LatLng (29.28096,30.6213))

        return mutableListLatLang
    }

}