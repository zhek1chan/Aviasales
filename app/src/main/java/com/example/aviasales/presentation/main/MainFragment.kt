package com.example.aviasales.presentation.main

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aviasales.R
import com.example.aviasales.data.Recommendation
import com.example.aviasales.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        if (sharedPref.getString(getString(R.string.preference_file_key), "") != null) {
            departureFromText = sharedPref.getString(getString(R.string.preference_file_key), "")!!
            binding.from.setText(departureFromText)
            binding.fromBottomsheet.text = departureFromText
        }
        recsAdapter = RecsAdapter()
        recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recsAdapter
        if (arguments?.getBoolean("expand") != null) {
            val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchBottomsheet)
            bottomSheetBehavior.apply {
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        changeImg()
        onSearchTextChange()
        onDestinationTextViewClicked()

    }

    private fun onDestinationTextViewClicked() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchBottomsheet)
        if (departureFromText.isNotEmpty() or binding.from.text.isNotEmpty()) {
            binding.to.setOnClickListener {
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
        val data: List<Recommendation> = viewModel.getData()
        recsAdapter.setItems(data)
        Log.d("MainFragment", "Data was passed to adapter")
    }

    private fun onSearchTextChange() {
        binding.from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.from.text.isNotEmpty()) {
                    departureFromText = binding.from.text.toString()
                    binding.fromBottomsheet.text = departureFromText
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
                    with (sharedPref.edit()) {
                        putString(getString(R.string.preference_file_key), departureFromText)
                        apply()
                    }
                }
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
        binding.toBottomsheet.setOnEditorActionListener { v, actionId, event ->
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