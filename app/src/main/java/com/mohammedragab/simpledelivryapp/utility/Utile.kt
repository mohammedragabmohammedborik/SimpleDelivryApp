package com.mohammedragab.simpledelivryapp.utility

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

object Utile {
     fun setInsects(rootView: View){
         ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
             val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
             // Apply the insets as a margin to the view. Here the system is setting
             // only the bottom, left, and right dimensions, but apply whichever insets are
             // appropriate to your layout. You can also update the view padding
             // if that's more appropriate.
             view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                 leftMargin = insets.left
                 bottomMargin = insets.bottom
                 rightMargin = insets.right
             }

             // Return CONSUMED if you don't want want the window insets to keep being
             // passed down to descendant views.
             WindowInsetsCompat.CONSUMED
         }

//         ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
//             val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
//             // Apply the insets as padding to the view. Here we're setting all of the
//             // dimensions, but apply as appropriate to your layout. You could also
//             // update the views margin if more appropriate.
//             view.updatePadding(insets.left, 0, insets.right, insets.bottom)
//             // Return CONSUMED if we don't want the window insets to keep being passed
//             // down to descendant views.
//             WindowInsetsCompat.CONSUMED
//         }

     }
}