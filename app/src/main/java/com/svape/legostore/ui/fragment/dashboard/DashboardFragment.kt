package com.svape.legostore.ui.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.svape.legostore.R
import com.svape.legostore.core.Constants
import com.svape.legostore.core.Resource
import com.svape.legostore.data.model.store.Store
import com.svape.legostore.data.remote.StoreDataSource
import com.svape.legostore.databinding.FragmentDashboardBinding
import com.svape.legostore.presentation.StoreViewModel
import com.svape.legostore.presentation.StoreViewModelFactory
import com.svape.legostore.repository.RetrofitClient
import com.svape.legostore.repository.StoreRepositoryImpl
import com.svape.legostore.ui.activity.ProductDetailActivity
import com.svape.legostore.ui.activity.USettingsActivity
import com.svape.legostore.ui.fragment.dashboard.adapters.StoreAdapter
import com.svape.legostore.ui.fragment.dashboard.adapters.concat.StoreConcatAdapter
import com.svape.legostore.ui.fragment.home.HomeViewModel


class DashboardFragment : Fragment(), StoreAdapter.OnStoreClickListener {

    private var _binding: FragmentDashboardBinding? = null

    private val viewModel by viewModels<StoreViewModel> {
        StoreViewModelFactory(
            StoreRepositoryImpl(
                StoreDataSource(RetrofitClient.webservice)
            )
        )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If we want to use the option menu in fragment we need add it.
        setHasOptionsMenu(true)
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenStore().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            StoreConcatAdapter(
                                StoreAdapter(
                                    result.data.products,
                                    this@DashboardFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("SiSe", " ${result.exception}")
                }
                else -> {}
            }
        })

        return root
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.actionSettings -> {
                startActivity(Intent(activity, USettingsActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStoreClick(store: Store) {
        Log.d("Products", "OnClick: $store")
        val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
        intent.putExtra(Constants.keyDetaikProducts, store)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}