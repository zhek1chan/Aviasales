package com.example.aviasales.presentation.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aviasales.R
import com.example.aviasales.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.divider.MaterialDividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recsAdapter: RecsAdapter
    private val binding get() = _binding!!
    private lateinit var departureFromText: String
    private var departureToText = ""
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        departureFromText = viewModel.getDepartureCity()
        Log.d("MainFragment", "Got $departureFromText from sp")
        binding.from.setText(departureFromText)
        recsAdapter = RecsAdapter()
        recyclerView = binding.rv
        val divider = MaterialDividerItemDecoration(
            recyclerView.context,
            RecyclerView.HORIZONTAL
        )
        recyclerView.adapter = recsAdapter
        recyclerView.addItemDecoration(
            divider.apply {
                setDividerThicknessResource(requireContext(), R.dimen.padding_of_rec_rv)
                setDividerColorResource(requireContext(), R.color.black)
                isLastItemDecorated = false
            }
        )
        if (arguments?.getBoolean("expand") != null) {
            val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchBottomsheet)
            bottomSheetBehavior.apply {
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        getData()
        changeImg()
        onSearchTextChange()
        onDestinationTextViewClicked()

    }

    private fun onDestinationTextViewClicked() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchBottomsheet)
        if (departureFromText.isNotEmpty() or binding.from.text.isNotEmpty()) {
            binding.to.setOnClickListener {
                binding.fromBottomsheet.text = binding.from.text
                binding.searchBottomsheet.visibility = View.VISIBLE
                binding.mainPage.isClickable = false
                bottomSheetBehavior.apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
                onDepartureToTextChange()
                onClearClickListener()
                bottomSheetButtonsListener()
                onRecDestinationItemClick()
            }
        } else bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            binding.mainPage.isClickable = true
        }
    }

    private fun getData() {
        viewModel.getData()
        viewModel.observeOffers().observe(viewLifecycleOwner) { offers ->
            recsAdapter.setItems(offers)
        }
    }

    private fun onSearchTextChange() {
        binding.from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                departureFromText = p0.toString()
                binding.fromBottomsheet.text = departureFromText
                Log.d("MainFragment", "Saved $departureFromText to sp")
                viewModel.setDepartureCity(departureFromText)
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun onDepartureToTextChange() {
        binding.toBottomsheet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.toBottomsheet.text.isNotEmpty()) {
                    departureToText = binding.toBottomsheet.text.toString()
                    navigateToSearchFilter()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.toBottomsheet.setOnClickListener {
            if (binding.toBottomsheet.text.isNotEmpty()) {
                departureToText = binding.toBottomsheet.text.toString()
                navigateToSearchFilter()
            }
        }
    }

    private fun navigateToSearchFilter() {
        binding.toBottomsheet.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val bundle = Bundle()
                bundle.putString("from", departureFromText)
                bundle.putString("to", departureToText)
                findNavController().navigate(R.id.navigation_search_filter, bundle)
                return@setOnEditorActionListener true
            } else {
                false
            }
        }
    }

    private fun onRecDestinationItemClick() {
        binding.stambul.setOnClickListener {
            binding.toBottomsheet.setText(R.string.istambul)
        }
        binding.sochi.setOnClickListener {
            binding.toBottomsheet.setText(R.string.sochi)
        }
        binding.phuket.setOnClickListener {
            binding.toBottomsheet.setText(R.string.phuket)
        }
    }

    private fun onClearClickListener() {
        binding.close.setOnClickListener {
            binding.toBottomsheet.text.clear()
        }
    }

    private fun bottomSheetButtonsListener() {
        binding.fireSaleContainer.setOnClickListener {
            findNavController().navigate(R.id.hotSalesFragment)
        }

        binding.hardDirectionsContainer.setOnClickListener {
            findNavController().navigate(R.id.hardRouteFragment)
        }

        binding.weekendsContainer.setOnClickListener {
            findNavController().navigate(R.id.weekendsFragment)
        }

        binding.goEverywhereContainer.setOnClickListener {
            binding.toBottomsheet.setText(R.string.go_everywhere)
        }

    }

    private fun changeImg() {
        val cornerPixelSize = resources.getDimensionPixelSize(R.dimen.photo_cover_corner_radius)
        Glide.with(this)
            .load(R.drawable.istambul)
            .placeholder(R.drawable.search_rounded_icons)
            .transform(CenterCrop(), RoundedCorners(cornerPixelSize))
            .into(binding.stambulCover)
        Glide.with(this)
            .load(R.drawable.sochi)
            .placeholder(R.drawable.search_rounded_icons)
            .transform(CenterCrop(), RoundedCorners(cornerPixelSize))
            .into(binding.sochiCover)
        Glide.with(this)
            .load(R.drawable.phuket)
            .placeholder(R.drawable.search_rounded_icons)
            .transform(CenterCrop(), RoundedCorners(cornerPixelSize))
            .into(binding.phuketCover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}