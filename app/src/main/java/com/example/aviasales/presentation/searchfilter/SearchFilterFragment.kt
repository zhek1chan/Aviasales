package com.example.aviasales.presentation.searchfilter

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aviasales.R
import com.example.aviasales.databinding.FragmentSearchBinding
import kotlinx.datetime.Month
import java.text.SimpleDateFormat
import java.util.Date

class SearchFilterFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var fromText: String
    private lateinit var toText: String
    private var y = 0
    private var m = 0
    private var d = 0

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
        binding.fromSearch.text = fromText
        binding.toSearch.text = toText

        val cal = Calendar.getInstance()
        y = cal.get(Calendar.YEAR)
        m = cal.get(Calendar.MONTH)
        d = cal.get(Calendar.DAY_OF_MONTH)

        val fullMonth = Month(m + 1).toString().lowercase()
        val smallMonth = "${fullMonth[0]}${fullMonth[1]}${fullMonth[2]}"
        val simpledateformat = SimpleDateFormat("EEEE")
        val date = Date(y, m, d - 1)
        val bigDayOfWeek = simpledateformat.format(date).lowercase()
        val smallDayOfWeek = "${bigDayOfWeek[0]}${bigDayOfWeek[1]}"

        val text =
            "<font color=#FFFFFFFF>$d $smallMonth</font><font color=#5E5F61>, $smallDayOfWeek</font>"
        binding.date.setText(
            Html.fromHtml(text)
        )
        onSearchTextChangeListener()
        destinationButtonsListener()

    }

    private fun onSearchTextChangeListener() {
        binding.toSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.toSearch.text.isNotEmpty()) {
                    toText = binding.toSearch.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


    private fun destinationButtonsListener() {
        binding.change.setOnClickListener {
            val newFrom = binding.fromSearch.text
            val newTo = binding.toSearch.text
            binding.toSearch.text = newFrom
            binding.fromSearch.text = newTo
        }

        binding.close.setOnClickListener {
            binding.toSearch.text = ""
        }

        binding.buttonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("expand", true)
            findNavController().navigate(R.id.navigation_search_main, bundle)
        }

        binding.buttonBackRoute.setOnClickListener{
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->

                },
                y,
                m,
                d
            )

            datePickerDialog.show()
        }

        binding.buttonDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    val fullMonth = Month(monthOfYear).toString().lowercase()
                    val smallMonth = "${fullMonth[0]}${fullMonth[1]}${fullMonth[2]}"
                    val simpledateformat = SimpleDateFormat("EEEE")
                    val date = Date(year, monthOfYear, dayOfMonth - 1)
                    val bigDayOfWeek = simpledateformat.format(date).lowercase()
                    val smallDayOfWeek = "${bigDayOfWeek[0]}${bigDayOfWeek[1]}"
                    val text =
                        "<font color=#FFFFFFFF>$dayOfMonth $smallMonth</font><font color=#5E5F61>, $smallDayOfWeek</font>"
                    binding.date.setText(
                        Html.fromHtml(text)
                    )
                },
                y,
                m,
                d
            )

            datePickerDialog.show()
        }

        binding.buttonShowMoreCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("from", binding.fromSearch.text.toString())
            bundle.putString("to", binding.toSearch.text.toString())
            bundle.putString("date", binding.date.text.toString())
            findNavController().navigate(R.id.ticketsFragment, bundle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}