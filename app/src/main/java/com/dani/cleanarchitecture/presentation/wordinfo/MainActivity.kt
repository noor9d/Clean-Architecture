package com.dani.cleanarchitecture.presentation.wordinfo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dani.cleanarchitecture.core.extension.*
import com.dani.cleanarchitecture.databinding.ActivityMainBinding
import com.dani.cleanarchitecture.presentation.songs.MusicActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val wordViewModel: WordViewModel by viewModels()
    private var wordInfoAdapter: WordInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.title = "Dictionary"
        wordInfoAdapter = WordInfoAdapter()

        binding.apply {

            imgSearchWord click {
                val text = edtWord.text
                if (text?.isNotEmpty() == true) {
                    wordViewModel.getWordInfo(text.toString())
                    hideKeyboard()
                } else
                    showToast("Please enter word")

            }

            fabMusic click {
                startAppActivity<MusicActivity>()
            }

            edtWord.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    val text = binding.edtWord.text?.trim().toString()
                    if (text.isNotEmpty()) {
                        wordViewModel.getWordInfo(text)
                        true
                    } else
                        false
                } else false


            }

            wordRecyclerView.adapter = wordInfoAdapter

        }

        lifecycleScope.launch {
            wordViewModel.state.collect { wordInfoState ->

                when {
                    wordInfoState.isLoading -> {
                        withContext(Dispatchers.Main) {
                            binding.dicProgress.show()
                            wordInfoAdapter?.setData(wordInfoState.wordInfoItems)
                        }

                    }
                    wordInfoState.wordInfoItems.isNotEmpty() -> {
                        withContext(Dispatchers.Main) {
                            binding.dicProgress.hide()
                            wordInfoAdapter?.setData(wordInfoState.wordInfoItems)
                        }

                    }
                    else -> {
                        if (wordInfoState.error.isNotEmpty())
                            withContext(Dispatchers.Main) {
                                binding.dicProgress.hide()
                                showToast(wordInfoState.error)
                            }

                    }
                }

            }
        }

    }

}