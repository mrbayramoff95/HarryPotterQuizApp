package com.example.harrypotterquizapp.ui.startquiz

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.harrypotterquizapp.R
import com.example.harrypotterquizapp.databinding.FragmentStartQuizBinding
import com.example.harrypotterquizapp.ui.quiz.QuizFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StartQuizFragment() : Fragment(), Parcelable {

    private val viewModel: StartQuizViewModel by viewModels()

    private lateinit var binding: FragmentStartQuizBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startQuiz.setOnClickListener {
            viewModel.getQuizResponse(
                binding.countQuestionsSlider.value.toInt(),
                binding.difficultySwitcher.getCurrentValue()
            )
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNotFinishedQuiz().collectLatest {
                    setList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.quiz.collect {
                    if (it.isNotEmpty()) {
                        parentFragmentManager.beginTransaction()
                            .add(R.id.container, QuizFragment.getFragment(it))
                            .addToBackStack(QuizFragment.TAG)
                            .commit()
                    }
                }
            }
        }
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StartQuizFragment> {
        override fun createFromParcel(parcel: Parcel): StartQuizFragment {
            return StartQuizFragment(parcel)
        }

        override fun newArray(size: Int): Array<StartQuizFragment?> {
            return arrayOfNulls(size)
        }
    }
}

