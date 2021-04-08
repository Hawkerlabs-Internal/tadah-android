package com.hawkerlabs.tadah.presentation.list_items.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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

    }

    private fun subscribe() {

    }
}