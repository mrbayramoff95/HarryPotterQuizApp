package com.example.harrypotterquizapp.ui.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.harrypotterquizapp.App
import com.example.harrypotterquizapp.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Inject

@AndroidEntryPoint
class QuizFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: QuizViewModelFactory

    private var binding: FragmentQuizBinding? = null

    private val viewModel: QuizViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.applicationComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding?.root
    }

}

private fun ApplicationComponentManager?.inject(quizFragment: QuizFragment) = Unit
