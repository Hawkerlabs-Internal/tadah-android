package com.hawkerlabs.tadah.presentation.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.FragmentListsBinding
import com.hawkerlabs.tadah.domain.tasks.model.TasksResponse
import com.hawkerlabs.tadah.presentation.MainActivity
import com.hawkerlabs.tadah.presentation.list.viewmodel.TasksListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListsFragment : Fragment() {

    private lateinit var binding: FragmentListsBinding
    private val viewModel by viewModels<TasksListViewModel>()
    private lateinit var listsAdapter: ListsAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lists, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    private fun initialize() {
        (activity as? MainActivity)?.hideHomeEnabled("Tadah Smart Todo")
        binding.addTaskFab.setOnClickListener {
            findNavController().navigate(R.id.addTaskFragment)
        }
        listsAdapter = ListsAdapter {
            findNavController().navigate(R.id.listItemsFragment)
        }

        binding.tasksRecyclerView.adapter = listsAdapter
    }

    private fun subscribe() {

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is TasksResponse.Success -> listsAdapter.submitList(it.data.toMutableList())
            }
        }


    }

}