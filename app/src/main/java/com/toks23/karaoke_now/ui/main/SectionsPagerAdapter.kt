package com.toks23.karaoke_now.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.toks23.karaoke_now.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_0,
    R.string.tab_text_A,
    R.string.tab_text_B,
    R.string.tab_text_C,
    R.string.tab_text_D,
    R.string.tab_text_E,
    R.string.tab_text_F,
    R.string.tab_text_G,
    R.string.tab_text_H,
    R.string.tab_text_I,
    R.string.tab_text_J,
    R.string.tab_text_K,
    R.string.tab_text_L,
    R.string.tab_text_M,
    R.string.tab_text_N,
    R.string.tab_text_O,
    R.string.tab_text_P,
    R.string.tab_text_Q,
    R.string.tab_text_R,
    R.string.tab_text_S,
    R.string.tab_text_T,
    R.string.tab_text_U,
    R.string.tab_text_V,
    R.string.tab_text_W,
    R.string.tab_text_X,
    R.string.tab_text_Y,
    R.string.tab_text_Z
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 27
    }
}