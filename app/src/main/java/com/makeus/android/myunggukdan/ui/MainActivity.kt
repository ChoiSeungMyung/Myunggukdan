package com.makeus.android.myunggukdan.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.ui.fragment.home.HomeFragment
import com.makeus.android.myunggukdan.ui.fragment.parent.RankingFragment
import com.makeus.android.myunggukdan.ui.fragment.setting.SettingFragment
import com.makeus.android.myunggukdan.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        lifecycleScope.launchWhenResumed {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content_layout, HomeFragment())
                .commit()
        }

        main_bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeItem -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content_layout, HomeFragment())
                    .commit()
            }
            R.id.statisticsItem -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content_layout, RankingFragment())
                    .commit()
            }
            R.id.settingItem -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content_layout, SettingFragment())
                    .commit()
            }
        }
        return true
    }
}