package com.example.harrypotterquizapp.ui.signup

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.harrypotterquizapp.R
import com.example.harrypotterquizapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }


    private fun validateEmail(): String? {
        val emailText = binding?.emailEditText?.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.invalid_email_address)
        }
        return null
    }

    private fun passwordFocusListener() {
        binding?.passwordEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.passwordTextInputLayout?.helperText = validatePassword()
            }
        }
    }

    private fun validatePassword(): String? {
        val passwordText = binding?.passwordEditText?.text.toString()
        if (passwordText.length < 6) {
            return getString(R.string.must_be_6_to_10_characters)
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return getString(R.string.must_contain_1_uppercase_character)
        }
        return null
    }

}