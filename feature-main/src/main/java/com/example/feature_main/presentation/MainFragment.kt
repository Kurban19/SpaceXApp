package com.example.feature_main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.vmutils.viewModel
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.di.MainComponent
import com.example.feature_main.presentation.adapter.LaunchAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModel()

    private var launchAdapter: LaunchAdapter? = null

    private var navController: NavController? = null

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
        launchAdapter = LaunchAdapter {
            val bundle = bundleOf("launchId" to it)
            navController?.navigate(com.example.core.R.id.action_mainFragment_to_chooseRecipientFragment, bundle)
        }
        binding.rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLaunches.adapter = launchAdapter
    }
}