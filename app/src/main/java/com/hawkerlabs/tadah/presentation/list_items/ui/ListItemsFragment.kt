package com.hawkerlabs.tadah.presentation.list_items.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.data.database.model.List
import com.hawkerlabs.tadah.databinding.DialogCreateListBinding
import com.hawkerlabs.tadah.databinding.FragmentListItemsBinding
import com.hawkerlabs.tadah.presentation.MainActivity
import com.hawkerlabs.tadah.presentation.list.ui.ListsAdapter
import com.hawkerlabs.tadah.presentation.list.viewmodel.DialogListViewModel
import com.hawkerlabs.tadah.presentation.list_items.viewmodel.ListItemsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListItemsFragment : Fragment() {
    private lateinit var binding: FragmentListItemsBinding
    private val viewModel by viewModels<ListItemsFragmentViewModel>()
    private val dialogListViewModel by viewModels<DialogListViewModel>()

    private var rotate = false
    private lateinit var list: List

    private lateinit var listItemsAdapter: ListItemsAdapter

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
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

        list = arguments?.get("list") as List

        //Init adapter
        listItemsAdapter = ListItemsAdapter {
            viewModel.updateItem(it)
        }
        binding.itemsRecyclerView.adapter = listItemsAdapter

        val dividerItemDecoration = DividerItemDecoration(requireActivity(),
                LinearLayout.VERTICAL)
        binding.itemsRecyclerView.addItemDecoration(dividerItemDecoration)

        //Fetch items for this list
        viewModel.getItems(list.id)

        binding.item.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.addItem(list.id)
                return@setOnKeyListener true;
            }
            return@setOnKeyListener false;
        }
        binding.settingsFab.setOnClickListener { v ->
            toggleFabMode(v)
        }
        //delete list
        binding.delete.setOnClickListener {
            showAlert()
        }
        //Edit dialog
        binding.edit.setOnClickListener {
            showEditDialog(list)
        }
    }

    private fun showEditDialog(list: List) {
        val dialog = Dialog(requireActivity())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        val dialogCreateListBinding: DialogCreateListBinding = DataBindingUtil.inflate(dialog.layoutInflater, R.layout.dialog_create_list,
                null, false);

        dialog.setContentView(dialogCreateListBinding.root);

        dialogCreateListBinding.viewModel = dialogListViewModel  //set the viewmodel
        dialogCreateListBinding.lifecycleOwner = this

        dialog.setCancelable(true)


        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT


        dialog.show()
        dialog.window!!.attributes = lp


        dialogListViewModel.initEdit(list) // initialize edit mode
        dialogCreateListBinding.buttonSave.setOnClickListener {
            dialogListViewModel.editList() // edit the list
            dialog.dismiss()
            toggleFabMode(binding.settingsFab)
        }
    }

    private fun subscribe() {

        viewModel.items.observe(viewLifecycleOwner) {
            listItemsAdapter.submitList(it?.toMutableList())
        }
    }

    private fun showAlert() {
        AlertDialog.Builder(requireContext())
                .setTitle("Are you sure you want to delete?")
                .setMessage("Selected item and all related information will be deleted.")
                .setPositiveButton("OK") { dialog, _ ->
                    viewModel.deleteList(list)
                    dialog.cancel()
                    findNavController().popBackStack()
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