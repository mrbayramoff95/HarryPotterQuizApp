package com.example.harrypotterquizapp.di

import com.example.harrypotterquizapp.MainActivity
import com.example.harrypotterquizapp.network.NetworkModule
import com.example.harrypotterquizapp.reciever.NetworkReceiver
import com.example.harrypotterquizapp.ui.quiz.QuizFragment
import com.example.harrypotterquizapp.ui.quizresult.QuizResultFragment
import com.example.harrypotterquizapp.ui.signin.SignInFragment
import com.example.harrypotterquizapp.ui.signup.SignUpFragment
import com.example.harrypotterquizapp.ui.startquiz.StartQuizFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
abstract class ApplicationComponent {

    abstract fun inject(activity: MainActivity)

    abstract fun inject(receiver: NetworkReceiver)

    abstract fun inject(fragment: StartQuizFragment)
    abstract fun inject(fragment: SignInFragment)
    abstract fun inject(fragment: SignUpFragment)
    abstract fun inject(fragment: QuizFragment)
    abstract fun inject(fragment: QuizResultFragment)
}