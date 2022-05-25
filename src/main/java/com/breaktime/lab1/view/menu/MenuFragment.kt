package com.breaktime.lab1.view.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentMenuBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(
            inflater, container, false
        ).apply {
            signInButton.setOnClickListener {
                if (getLastSignedInAccount() != null)
                    signOut()
                else
                    signIn()
            }
            info.setOnClickListener {
                findNavController().navigate(R.id.infoFragment)
            }
            achievements.setOnClickListener {
                findNavController().navigate(R.id.achievementsFragment)
            }
            feed.setOnClickListener {
                findNavController().navigate(R.id.userSelectionFragment)
            }

            about.setOnClickListener {
                findNavController().navigate(R.id.aboutFragment)
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val account = getLastSignedInAccount()
        updateUI(account)
        return binding.root
    }

    private fun getLastSignedInAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(requireContext())
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
        updateUI(null)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            updateUI(null)
            Log.e("ERROR", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account == null) {
            binding.signInButton.text = getString(R.string.sign_in)
            binding.signText.visibility = View.GONE
        } else {
            binding.signInButton.text = getString(R.string.sign_out)
            binding.signText.visibility = View.VISIBLE
            binding.name.text = account.displayName
            binding.email.text = account.email

        }
    }
}