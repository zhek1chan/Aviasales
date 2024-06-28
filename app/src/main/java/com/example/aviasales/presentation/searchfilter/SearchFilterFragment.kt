package com.example.aviasales.presentation.searchfilter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aviasales.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFilterFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var fromText: String
    private lateinit var toText: String
    private val viewModel by viewModel<FilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fromText = arguments?.getString("from")!!
        toText = arguments?.getString("to")!!
        binding.from.text = fromText
        binding.to.text = toText
        onSearchTextChangeListener()
        onCloseClickListener()

    }

    //по клику enter запускается экран поиска
    private fun onSearchTextChangeListener() {
        binding.to.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.to.text.isNotEmpty()) {
                    toText = binding.to.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


    private fun onCloseClickListener() {
        binding.close.setOnClickListener {
            binding.to.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}