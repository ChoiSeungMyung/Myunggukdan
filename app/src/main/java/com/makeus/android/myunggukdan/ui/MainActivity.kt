package com.makeus.android.myunggukdan.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.ActMainBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.ui.fragment.AddWasteItemFragment
import com.makeus.android.myunggukdan.ui.fragment.SplashFragment
import com.makeus.android.myunggukdan.ui.fragment.home.HomeFragment
import com.makeus.android.myunggukdan.ui.fragment.parent.RankingFragment
import com.makeus.android.myunggukdan.ui.fragment.setting.SettingFragment
import com.makeus.android.myunggukdan.ui.fragment.sign.ChoiceSignFragment
import com.makeus.android.myunggukdan.ui.fragment.sign.SignUpFragment
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import com.makeus.android.myunggukdan.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.act_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActMainBinding

    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val signViewModel by lazy {
        ViewModelProvider(this).get(SignViewModel::class.java)
    }

    private val historyViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private val homeFragment by lazy {
        HomeFragment.getInstance(historyViewModel)
    }

    private val addWasteItemFragment by lazy {
        AddWasteItemFragment.newInstance(homeFragment.historyViewModel)
    }

    private val splashFragment by lazy {
        SplashFragment.newInstance(signViewModel)
    }

    private val choiceSignFragment by lazy {
        ChoiceSignFragment.newInstance(signViewModel)
    }

    private val signUpFragment by lazy {
        SignUpFragment.newInstance(signViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        binding = DataBindingUtil.setContentView(this, R.layout.act_main)
        binding.run {
            historyViewModelBinding = homeFragment.historyViewModel
            loginViewModelBinding = signViewModel
            lifecycleOwner = this@MainActivity
        }

        lifecycleScope.launchWhenResumed {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content_layout, splashFragment)
                .commit()
        }

        signViewModel.signState.observe(this@MainActivity, {
            when(it) {
                SignViewModel.SignState.SignSuccess -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content_layout, homeFragment)
                        .commit()
                }

                SignViewModel.SignState.SignIn -> {
                    // 회원가입 프래그먼트
                }

                SignViewModel.SignState.SignUp -> {
                    // 로그인 프래그먼트
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content_layout, signUpFragment)
                        .commit()
                }

                else -> {
                    // 로그인, 회원가입 선택프래그먼트 띄우기
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content_layout, choiceSignFragment)
                        .commit()
                }
            }
        })

        main_bottom_navigation.setOnNavigationItemSelectedListener(this)

        homeFragment.historyViewModel.addWastedItem.observe(this, {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeItem -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_content_layout, homeFragment
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