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
import com.example.aviasales.R
import com.example.aviasales.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recsAdapter: RecsAdapter
    private val binding get() = _binding!!
    private lateinit var departureFromText: String
    private lateinit var departureToText: String
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
        }
        recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recsAdapter
        onSearchTextChange()
        onDestinationTextViewClicked()

    }

    private fun onDestinationTextViewClicked() {
        val bottomSheetContainer = binding.searchBottomsheet
        val bottomSheetBehavior =
            BottomSheetBehavior.from(bottomSheetContainer)
        if (departureFromText.isNotEmpty()) {
            binding.to.setOnClickListener {
                binding.searchBottomsheet.visibility = View.VISIBLE
                bottomSheetBehavior.apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        } else bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_HIDDEN
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
        binding.toBottomsheet.setOnEditorActionListener { v, actionId, event ->
            if (actionId === EditorInfo.IME_ACTION_DONE) {
                val bundle = Bundle()
                bundle.putString("from", departureFromText)
                bundle.putString("to", departureToText)
                findNavController().navigate(R.id.navigation_search_filter, bundle)
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun ondepartureToTextClick() {
        if (departureFromText.isNotEmpty()) {
            binding.to.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("from", departureFromText)
                findNavController().navigate(R.id.navigation_search_filter, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}