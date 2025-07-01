package com.example.fubutube.ui.home.tab

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OceanFragment : VideoFragment() {
    override fun getCategory(): String = "Ocean"
}