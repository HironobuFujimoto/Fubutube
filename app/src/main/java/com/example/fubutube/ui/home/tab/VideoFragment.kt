package com.example.fubutube.ui.home.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fubutube.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A fragment representing a list of Items.
 */

abstract class VideoFragment : Fragment() {

    private val viewModel : VideoFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideoFragmentAdapter

    // ★ サブクラスでカテゴリだけ実装する
    protected abstract fun getCategory(): String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_video_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        // 1回だけadapterを作成、空のリストで初期化
        adapter = VideoFragmentAdapter(emptyList(), emptySet()) { video, newState ->
            viewModel.toggleFavorite(video, newState, getCategory())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()

        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.loadMovies(getCategory())
                viewModel.loadFavorite()
            }
        }

        viewModel.videos.observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }
        viewModel.favoriteIds.observe(viewLifecycleOwner) {
            adapter.updateFavoriteIds(it)
        }

        return view
    }
}