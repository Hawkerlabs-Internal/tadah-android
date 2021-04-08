package com.hawkerlabs.tadah.presentation.create_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.FragmentCreateListBinding
import com.hawkerlabs.tadah.presentation.MainActivity
import com.hawkerlabs.tadah.presentation.create_list.viewmodel.CreateListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListFragment : Fragment() {
    private lateinit var binding: FragmentCreateListBinding
    private val viewModel by viewModels<CreateListViewModel>()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    private fun initialize() {
        binding.buttonSave.setOnClickListener{
            viewModel.saveTask()
            findNavController().popBackStack()

        }

        (activity as? MainActivity)?.showHomeEnabled("Add Task")
    }

    private fun subscribe() {

    }
}