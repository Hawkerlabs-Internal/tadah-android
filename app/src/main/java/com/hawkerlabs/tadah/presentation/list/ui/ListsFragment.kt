package com.hawkerlabs.tadah.presentation.list.ui

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.DialogCreateListBinding
import com.hawkerlabs.tadah.databinding.FragmentListsBinding
import com.hawkerlabs.tadah.domain.lists.model.TasksResponse
import com.hawkerlabs.tadah.presentation.MainActivity
import com.hawkerlabs.tadah.presentation.list.viewmodel.CreateListViewModel
import com.hawkerlabs.tadah.presentation.list.viewmodel.ListsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListsFragment : Fragment() {

    private lateinit var binding: FragmentListsBinding
    private val viewModel by viewModels<ListsViewModel>()
    private val createListViewModel by viewModels<CreateListViewModel>()

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

            val dialog = Dialog(requireActivity())

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            val dialogCreateListBinding: DialogCreateListBinding = DataBindingUtil.inflate(dialog.layoutInflater, R.layout.dialog_create_list,
                    null, false);

            dialog.setContentView(dialogCreateListBinding.root);

            dialogCreateListBinding.viewModel = createListViewModel
            dialogCreateListBinding.lifecycleOwner = this

            dialog.setCancelable(true)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT


            dialog.show()
            dialog.window!!.attributes = lp

            dialogCreateListBinding.buttonSave.setOnClickListener {
                createListViewModel.saveTask()
                dialog.dismiss()
            }
        }

        //List adapter takes a lambda for the item click listener
        listsAdapter = ListsAdapter { list ->
            var bundle = bundleOf("list" to list)
            findNavController().navigate(R.id.listItemsFragment, bundle)
        }


        //set up the adapter
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