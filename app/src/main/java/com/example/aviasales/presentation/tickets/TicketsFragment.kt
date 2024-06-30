package com.example.aviasales.presentation.tickets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aviasales.R
import com.example.aviasales.databinding.FragmentTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!
    private lateinit var fromText: String
    private lateinit var toText: String
    private lateinit var info: String
    private lateinit var recyclerView: RecyclerView
    private var ticketsAdapter = TicketsAdapter()
    private val viewModel by viewModel<TicketsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fromText = arguments?.getString("from")!!
        toText = arguments?.getString("to")!!
        info = arguments?.getString("date")!!
        info = info.dropLast(4)
        val route = getString(R.string.route_tickets_screen, fromText, toText)
        binding.fromTo.text = route
        recyclerView = binding.rvTickets
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ticketsAdapter
        getData(fromText, toText)
        binding.info.text = info + getString(R.string.passenger)
        backButtonListener()
    }

    private fun getData(from: String, to: String) {
        //val data: List<Ticket> = viewModel.getData(from, to)
        //ticketsAdapter.setItems(data)
        Log.d("TicketsFragment", "Data was passed to adapter")
    }


    private fun backButtonListener() {
        binding.buttonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("from", fromText)
            bundle.putString("to", toText)
            findNavController().navigate(R.id.navigation_search_filter, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}