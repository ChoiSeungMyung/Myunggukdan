package com.makeus.android.myunggukdan.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.ActMainBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.ui.fragment.AddWasteItemFragment
import com.makeus.android.myunggukdan.ui.fragment.home.HomeFragment
import com.makeus.android.myunggukdan.ui.fragment.parent.RankingFragment
import com.makeus.android.myunggukdan.ui.fragment.setting.SettingFragment
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import com.makeus.android.myunggukdan.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActMainBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val historyViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private val homeFragment by lazy {
        HomeFragment.getInstance(historyViewModel)
    }

    private val addWasteItemFragment by lazy {
        AddWasteItemFragment.newInstance(historyViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        binding = DataBindingUtil.setContentView(this, R.layout.act_main)
        binding.run {
            historyViewModelBinding = historyViewModel
            lifecycleOwner = this@MainActivity
        }

        lifecycleScope.launchWhenResumed {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content_layout, homeFragment)
                .commit()
        }

        main_bottom_navigation.setOnNavigationItemSelectedListener(this)

        historyViewModel.run {
            addWastedItem.observe(this@MainActivity, Observer {
                when (it) {
                    true -> {
                        supportFragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_content_layout, addWasteItemFragment)
                            .commit()
                    }
                }
            })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeItem -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_content_layout, HomeFragment(
                            historyViewModel
                        )
                    )
                    .commit()
            }
            R.id.rankItem -> {
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

    override fun onStop() {
        binding.lifecycleOwner = null
        super.onStop()
    }
}