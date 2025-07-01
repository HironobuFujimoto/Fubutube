package com.example.fubutube.ui.home.tab

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TigerFragment : VideoFragment() {
    override fun getCategory(): String = "Tigers"
}