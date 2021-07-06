package com.islimakkaya.artake.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.islimakkaya.artake.databinding.TutorialCardDesignBinding
import com.islimakkaya.artake.entity.Tutorial

class TutorialAdapter(var mContext: Context,
                      var tutorialList:List<Tutorial>): RecyclerView.Adapter<TutorialAdapter.CategoryDesignHolder>() {
    inner class CategoryDesignHolder(cardDesignBinding: TutorialCardDesignBinding): RecyclerView.ViewHolder(cardDesignBinding.root){
        var cardDesignBinding: TutorialCardDesignBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design= TutorialCardDesignBinding.inflate(layoutInflater,parent,false)
        return CategoryDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CategoryDesignHolder, position: Int) {

        val tutorial = tutorialList.get(position)

        holder.cardDesignBinding.imageViewTutorial.setImageResource(mContext.resources.getIdentifier(tutorial.tutorial_image_name, "drawable", mContext.packageName))

        val view = holder.cardDesignBinding
        view.tutorialObject = tutorial

    }

    override fun getItemCount(): Int {
        return tutorialList.size
    }
}