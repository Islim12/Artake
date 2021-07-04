package com.islimakkaya.artake.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.islimakkaya.artake.R
import com.islimakkaya.artake.adapter.ArtAdapter
import com.islimakkaya.artake.databinding.FragmentHomePageBinding
import com.islimakkaya.artake.viewmodel.HomePageFragmentViewModel

class HomePageFragment : Fragment() {
    private lateinit var design: FragmentHomePageBinding
    private lateinit var adapter: ArtAdapter
    private lateinit var viewModel: HomePageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        design.toolbarTitle = ""
        viewModel.artsList.observe(viewLifecycleOwner, { list ->
            adapter = ArtAdapter(requireContext(), list, viewModel)
            design.artAdapter = adapter
        })

        design.imageViewToolbar.setOnClickListener {
            val transition = HomePageFragmentDirections.toCartPage()
            Navigation.findNavController(it).navigate(transition)
        }


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}