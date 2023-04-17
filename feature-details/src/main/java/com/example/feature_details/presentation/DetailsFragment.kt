package com.example.feature_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.date.DateConversion
import com.example.core.presentation.date.DateFormatter
import com.example.core.presentation.vmutils.viewModel
import com.example.feature_details.databinding.FragmentDetailsBinding
import com.example.feature_details.di.DetailsComponent
import com.example.feature_details.presentation.adapter.CrewAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private lateinit var binding: FragmentDetailsBinding

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: DetailsViewModel by viewModel()

    private var crewAdapter: CrewAdapter? = null

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initView()
        injectDependencies()
        setUpRecyclerView()
        initObservers()
    }

    private fun initView() {;
        binding.toolbar.setNavigationOnClickListener {
            navController?.navigateUp()
        }
    }

    private fun injectDependencies() {
        DetailsComponent.Initializer.init(
            getAppComponent(),
            arguments?.getString("launchId") ?: "1"
        ).inject(this)
    }

    private fun initObservers() {
        val lifecycle = viewLifecycleOwner.lifecycle
        lifecycle.coroutineScope.launch {
            viewModel.launch
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { launcn ->
                    launcn?.let {
                        binding.ivLaunch.load(it.links.patch.large)
                        binding.tvName.text = it.name
                        binding.tvSuccess.text = if (it.success) "Успешно" else "Провал"
                        binding.tvLaunchDate.text =
                            DateFormatter().format(
                                it.fireDateUtc,
                                DateConversion.DETAILS_DISPLAY_DATE
                            )
                        binding.tvFlight.text = it.cores.sumOf { it.flight }.toString()
                        binding.tvDetails.text = it.details
                    }
                }
        }

        lifecycle.coroutineScope.launch {
            viewModel.crew
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { crews ->
                    crews?.let {
                        crewAdapter?.submitList(it)
                    }
                }
        }
    }

    private fun setUpRecyclerView() {
        crewAdapter = CrewAdapter()
        binding.rvCrew.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCrew.adapter = crewAdapter
    }
}
