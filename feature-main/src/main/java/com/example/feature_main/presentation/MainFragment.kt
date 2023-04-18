package com.example.feature_main.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.vmutils.viewModel
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.di.MainComponent
import com.example.feature_main.presentation.adapter.LaunchAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModel()

    private var navController: NavController? = null

    private val adapter: LaunchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        LaunchAdapter {
            val bundle = bundleOf("launchId" to it)
            navController?.navigate(
                com.example.core.R.id.action_mainFragment_to_chooseRecipientFragment,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        injectDependencies()
        setUpRecyclerView()
        initObservers()
    }

    private fun injectDependencies() {
        MainComponent.Initializer.init(getAppComponent())
            .inject(this)
    }

    private fun initObservers() {
        val lifecycle = viewLifecycleOwner.lifecycle
        lifecycle.coroutineScope.launch {
            viewModel.launches.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collectLatest(adapter::submitData)
        }
    }

    private fun setUpRecyclerView() {
        binding.rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLaunches.adapter = adapter
        adapter.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.rvLaunches.isVisible = refreshState != LoadState.Loading
            binding.progress.isVisible = refreshState == LoadState.Loading

            if (refreshState is LoadState.Error) {
                Snackbar.make(
                    binding.root,
                    refreshState.error.localizedMessage?.toString() ?: "",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}