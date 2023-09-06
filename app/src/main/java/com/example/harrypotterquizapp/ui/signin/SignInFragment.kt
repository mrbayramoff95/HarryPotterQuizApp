package com.example.harrypotterquizapp.ui.signin

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.harrypotterquizapp.R
import com.example.harrypotterquizapp.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var binding: FragmentSignInBinding? = null

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEmailFocusListener()
        setupPasswordFocusListener()

        binding?.sigInButton?.setOnClickListener {
            if (validate()) {

            }
        }
    }

    private fun setupEmailFocusListener() {
        binding?.emailEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.emailTextInputLayout?.helperText = validateEmail()
            }
        }
    }

    private fun validateEmail(): String? {
        val emailText = binding?.emailEditText?.text.toString()
        if (emailText.isEmpty()) {
            return getString(R.string.email_required)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.invalid_email_address)
        }
        return null
    }

    private fun setupPasswordFocusListener() {
        binding?.passwordEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.passwordTextInputLayout?.helperText = validatePassword()
            }
        }
    }

    private fun validatePassword(): String? {
        val passwordText = binding?.passwordEditText?.text.toString()
        if (passwordText.isEmpty()) {
            return getString(R.string.password_required)
        } else if (passwordText.length < 8) {
            return getString(R.string.must_be_8_to_16_characters)
        } else if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return getString(R.string.must_contain_1_uppercase_character)
        }
        return null
    }

    private fun validate(): Boolean {
        val validEmailError = validateEmail()
        val validPasswordError = validatePassword()

        binding?.emailTextInputLayout?.helperText = validEmailError
        binding?.passwordTextInputLayout?.helperText = validPasswordError

        return validEmailError == null && validPasswordError == null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}