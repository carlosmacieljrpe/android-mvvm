package com.android.mvvm.presentation.drinks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvm.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.mvvm.databinding.FragmentDrinkBinding
import com.android.mvvm.domain.model.Drinks
import com.android.mvvm.presentation.common.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrinksFragment : Fragment(R.layout.fragment_drink) {

    private val viewModel: DrinksViewModel by viewModels()

    private lateinit var binding: FragmentDrinkBinding

    //TODO: Pagination
    //private val adapter: DrinksPagingAdapter = DrinksPagingAdapter()
    private val adapter: DrinksAdapter = DrinksAdapter()

    private lateinit var initialList: List<Drinks>
    private lateinit var searchView: SearchView

    //TODO: Pagination
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentDrinkBinding.bind(view)
//        binding.drinkRecyclerView.adapter =
//            adapter.withLoadStateFooter(FooterAdapter(adapter::retry))
//        lifecycleScope.launch {
//            viewModel.requestDrinksPaginated().collectLatest {
//                setUiState(it)
//            }
//        }
//        initAdapter()
//        setHasOptionsMenu(true)
//        viewModel.requestDrinks()
//    }
//
//    private suspend fun setUiState(pagingData: PagingData<Drinks>) {
//        binding.loadingContainer.visibility = View.GONE
//        binding.emptyContainer.visibility = View.GONE
//        binding.drinkRecyclerView.visibility = View.VISIBLE
//        adapter.submitData(pagingData)
//        binding.drinkRecyclerView.requestLayout()
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDrinkBinding.bind(view)
        binding.drinkRecyclerView.adapter = adapter
        initAdapter()
        setHasOptionsMenu(true)
        lifecycleScope.launch {
            viewModel.drinksFlowState.collectLatest { setUiState(it) }
        }
        viewModel.requestDrinksFlow()
    }

    private fun setUiState(it: UiState<List<Drinks>>) {
        when (it) {
            is UiState.Success -> {
                binding.loadingContainer.visibility = View.GONE
                initialList = it.data
                if (initialList.isEmpty()) {
                    binding.drinkRecyclerView.visibility = View.GONE
                    binding.emptyContainer.visibility = View.VISIBLE
                } else {
                    binding.emptyContainer.visibility = View.GONE
                    binding.drinkRecyclerView.visibility = View.VISIBLE
                    adapter.submitList(null)
                    adapter.submitList(initialList)
                    binding.drinkRecyclerView.requestLayout()
                }
            }
            is UiState.Loading -> {
                binding.loadingAnimation.setAnimation(R.raw.loading)
                binding.loadingContainer.visibility = View.VISIBLE
                binding.drinkRecyclerView.visibility = View.GONE
                binding.emptyContainer.visibility = View.GONE
            }
            is UiState.Error -> {
                binding.loadingContainer.visibility = View.GONE
                binding.drinkRecyclerView.visibility = View.GONE
                binding.emptyContainer.visibility = View.GONE
                Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterDrinks(newText ?: "")
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        binding.drinkRecyclerView.layoutManager = layoutManager
        binding.drinkRecyclerView.adapter = adapter
        binding.drinkRecyclerView.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        val dividerDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.divider_layer,
            null
        )
        if (dividerDrawable != null) {
            dividerItemDecoration.setDrawable(dividerDrawable)
        }
        binding.drinkRecyclerView.addItemDecoration(dividerItemDecoration)
    }
}