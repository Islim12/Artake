package com.islimakkaya.artake.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.islimakkaya.artake.MainActivity
import com.islimakkaya.artake.databinding.HomePageCardDesignBinding
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.fragment.HomePageFragmentDirections
import com.islimakkaya.artake.viewmodel.HomePageFragmentViewModel
import com.squareup.picasso.Picasso

class ArtAdapter(var mContext: Context,
                 var artList:List<Arts>,
                 var viewModel: HomePageFragmentViewModel
) : RecyclerView.Adapter<ArtAdapter.CategoryDesignHolder>() {

    inner class CategoryDesignHolder(cardDesignBinding: HomePageCardDesignBinding): RecyclerView.ViewHolder(cardDesignBinding.root){
        var cardDesignBinding: HomePageCardDesignBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design = HomePageCardDesignBinding.inflate(layoutInflater,parent,false)
        return CategoryDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CategoryDesignHolder, position: Int) {

        val art = artList.get(position)

        val view = holder.cardDesignBinding
        view.artObject = art

        val url = art.art_image_url
        Picasso.get().load(url).into(holder.cardDesignBinding.imageViewProduct)

        if (art.is_campaign_product == 1) {
            holder.cardDesignBinding.textViewProductPrice.setTextColor(Color.RED)
        }

        holder.cardDesignBinding.buttonLook.setOnClickListener {
            val transition = HomePageFragmentDirections.toArtsDetailPage(art)
            Navigation.findNavController(it).navigate(transition)
        }

        holder.cardDesignBinding.buttonAddtoCart.setOnClickListener {
            viewModel.addCart(art.art_id, 1)
            val toastTextMsg = "${art.art_name} added to the cart!\uD83C\uDF0C"
            Toast.makeText(mContext, toastTextMsg , Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}