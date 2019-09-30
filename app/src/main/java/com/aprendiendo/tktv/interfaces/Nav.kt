package com.aprendiendo.tktv.interfaces

import android.support.v4.app.Fragment
import android.view.MenuItem

/**
 * Created by Ana Diaz on 07/03/2018.
 */

interface Nav {
    interface View {
        fun navigateUsingTo(fragment: Fragment, index: Int)
    }

    interface Presenter {
        fun navigationItemSelected(itemId: Int)
    }

    interface Model {
        fun navigateTo(itemId: Int, listener: Listener)
    }

    interface Listener {
        fun fragmentReplace(fragment: Fragment, index: Int)
    }
}
