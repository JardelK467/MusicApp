package com.example.musicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.adapter.TabAdapter
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.view.ClassicFragment
import com.example.musicapp.view.PopFragment
import com.example.musicapp.view.RockFragment

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabs()
        setContentView(binding.root)

    }

    private fun tabs(){
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(RockFragment())
        adapter.addFragment(ClassicFragment())
        adapter.addFragment(PopFragment())

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)!!.text = "Rock"
        binding.tabs.getTabAt(1)!!.text = "Classic"
        binding.tabs.getTabAt(2)!!.text = "Pop"
    }

}