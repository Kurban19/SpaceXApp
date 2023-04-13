package com.example.feature_main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.vmutils.viewModel
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.di.MainComponent
import com.example.feature_main.domain.entity.Launch
import com.example.feature_main.presentation.adapter.LaunchAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModel()

    private var launchAdapter: LaunchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            viewModel.launches
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { launcnes ->
                    launcnes?.let {
                        launchAdapter?.submitList(it)
                    }
                }
        }
    }

    private fun setUpRecyclerView() {
        launchAdapter = LaunchAdapter()
        binding.rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLaunches.adapter = launchAdapter
    }
}