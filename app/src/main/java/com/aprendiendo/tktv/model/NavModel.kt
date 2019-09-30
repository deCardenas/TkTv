package com.aprendiendo.tktv.model

import android.support.v4.app.Fragment
import android.view.MenuItem

import com.aprendiendo.tktv.R
import com.aprendiendo.tktv.interfaces.Nav
import com.aprendiendo.tktv.view.fragment.MovieFragment
import com.aprendiendo.tktv.view.fragment.TvShowFragment

/**
 * Created by Ana Diaz on 08/03/2018.
 */

class NavModel : Nav.Model {
    override fun navigateTo(itemId: Int, listener: Nav.Listener) {
        when (itemId) {
            R.id.nav_movies -> listener.fragmentReplace(MovieFragment.newInstance(), 0)
            R.id.nav_tv_shows -> listener.fragmentReplace(TvShowFragment.newInstance(), 1)
            R.id.nav_people -> listener.fragmentReplace(Fragment(), 2)
        }
    }
}
