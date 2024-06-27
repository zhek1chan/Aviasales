package com.example.aviasales.presentation.dashboard

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.R
import com.example.aviasales.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recsAdapter: RecsAdapter
    private val binding get() = _binding!!
    private lateinit var departureFromText: String
    private val viewModel by viewModel<DashboardViewModel>()

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

        onSearchTextChange()

    }

    private fun onTextViewClicked() {
        if (departureFromText.isNotEmpty()) {
            binding.to.setOnClickListener {
                //открыть ботомщит
            }
        }
    }

    private fun getData() {
        val data: List<Recommendation> = viewModel.getData()
        recsAdapter.setItems(data)
        Log.d("SearchIsOk", "Loading has been end")
    }

    private fun onSearchTextChange() {
        binding.from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.from.text.isNotEmpty()) {
                    departureFromText = binding.from.text.toString()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}