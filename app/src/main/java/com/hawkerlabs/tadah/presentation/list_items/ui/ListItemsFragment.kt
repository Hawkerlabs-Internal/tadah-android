package com.hawkerlabs.tadah.presentation.list_items.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.FragmentListItemsBinding
import com.hawkerlabs.tadah.presentation.MainActivity
import com.hawkerlabs.tadah.presentation.list_items.viewmodel.ListItemsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListItemsFragment : Fragment() {
    private lateinit var binding: FragmentListItemsBinding
    private val viewModel by viewModels<ListItemsFragmentViewModel>()
    private var rotate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_items, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Navigation.findNavController(binding.root).popBackStack()
                return true
            }

        }
        return false
    }

    private fun initialize() {
        (activity as? MainActivity)?.showHomeEnabled("Tasks")
        binding.addItemFab.setOnClickListener { v ->
            toggleFabMode(v)
        }
        binding.delete.setOnClickListener {
            showAlert()
        }
    }

    private fun subscribe() {

    }

    private fun showAlert() {
        AlertDialog.Builder(requireContext())
                .setTitle("Are you sure you want to delete?")
                .setMessage("Selected item and all related information will be deleted.")
                .setPositiveButton("OK") { dialog, _ ->

                    dialog.cancel()
                }
                .setNegativeButton("Cancel") { dialog, _ ->

                    dialog.cancel()
                }
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()


    }


    private fun toggleFabMode(v: View) {
        rotate = !rotate
        v.animate().setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .rotation(if (rotate) 135f else 0f)

        if (rotate) {
            showIn(binding.delete)
            showIn(binding.edit)
        } else {
            showOut(binding.delete)
            showOut(binding.edit)
        }

    }

    private fun showIn(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 0f
        v.translationY = v.height.toFloat()
        v.animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(1f)
                .start()
    }

    private fun showOut(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 1f
        v.translationY = 0f
        v.animate()
                .setDuration(200)
                .translationY(v.height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        v.visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                }).alpha(0f)
                .start()
    }

    fun initShowOut(v: View) {
        v.visibility = View.GONE
        v.translationY = v.height.toFloat()
        v.alpha = 0f
    }
}