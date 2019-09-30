package com.aprendiendo.tktv.presenter

import android.support.v4.app.Fragment
import android.view.MenuItem

import com.aprendiendo.tktv.interfaces.Nav
import com.aprendiendo.tktv.model.NavModel

/**
 * Created by Ana Diaz on 08/03/2018.
 */

class NavPresenter(internal var view: Nav.View) : Nav.Presenter, Nav.Listener {
    internal var model: Nav.Model

    init {
        model = NavModel()
    }

    override fun navigationItemSelected(itemId: Int) {
        model.navigateTo(itemId, this)
    }

    override fun fragmentReplace(fragment: Fragment, index: Int) {
        view.navigateUsingTo(fragment, index)
    }
}
