package com.ravimishra.newstar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(fm: FragmentManager?) : FragmentPagerAdapter(
    fm!!
) {
    private val mFragment: MutableList<Fragment> = ArrayList()
    private val TitleList: MutableList<String> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return mFragment[i]
    }

    override fun getCount(): Int {
        return mFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragment.add(fragment)
        TitleList.add(title)
    }
}