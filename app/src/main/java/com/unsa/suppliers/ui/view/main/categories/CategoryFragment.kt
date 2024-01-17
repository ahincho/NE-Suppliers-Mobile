package com.unsa.suppliers.ui.view.main.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsa.suppliers.data.adapters.main.categories.CategoryAdapter
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.databinding.FragmentCategoryBinding
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: CategoryAdapter
    private val manager = LinearLayoutManager(context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        mainViewModel.categories.observe(viewLifecycleOwner) {
            initRecyclerView(mainViewModel.categories.value ?: emptyList())
        }
        initListeners()
    }
    override fun onResume() {
        super.onResume()
        mainViewModel.getAllCategories()
    }
    private fun initRecyclerView(categories: List<CategoryResponse>) {
        adapter = CategoryAdapter(categories)
        binding.categoriesRecyclerView.layoutManager = manager
        binding.categoriesRecyclerView.adapter = adapter
        binding.categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredCategories = mainViewModel.categories.value?.filter {
                        category -> category.name.contains(newText.orEmpty(), ignoreCase = true)
                }
                adapter.updateCategories(filteredCategories)
                return true
            }
        })
    }
    private fun initListeners() {
        binding.categoryBtnAdd.setOnClickListener {
            val dialog = CategoryAddDialog()
            dialog.show(childFragmentManager, "Category Add Dialog")
        }
    }
}