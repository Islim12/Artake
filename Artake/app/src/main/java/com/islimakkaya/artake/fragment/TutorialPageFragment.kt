package com.islimakkaya.artake.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.islimakkaya.artake.R
import com.islimakkaya.artake.adapter.TutorialAdapter
import com.islimakkaya.artake.databinding.FragmentTutorialPageBinding
import com.islimakkaya.artake.entity.Tutorial


class TutorialPageFragment() : Fragment() {
    private lateinit var design: FragmentTutorialPageBinding
    private lateinit var tutorialList:ArrayList<Tutorial>
    private lateinit var adapter: TutorialAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_tutorial_page, container, false)


        val t1 = Tutorial(1,"Where to start?", getString(R.string.text_tutorial), "img_watercolor")
        val t2 = Tutorial(2,"1.The Basic Characteristics of Watercolors & Watercolor Painting", getString(R.string.text_tutorial_watercolor), "img_painting")
        val t3 = Tutorial(3,"2.Paintbrushes for Watercolors", getString(R.string.text_tutorial_watercolor_brush), "img_brushes")
        val t4 = Tutorial(4,"3.Why We Need to Use Watercolor Paper Specially?", getString(R.string.text_tutorial_watercolor_paper), "img_rabbit")

        tutorialList = ArrayList()
        tutorialList.add(t1)
        tutorialList.add(t2)
        tutorialList.add(t3)
        tutorialList.add(t4)

        adapter = TutorialAdapter(requireContext(), tutorialList)
        design.tutorialAdapter = adapter

        return design.root
    }

}