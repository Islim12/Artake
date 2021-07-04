package com.islimakkaya.artake.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.islimakkaya.artake.databinding.CartPageCardDesignBinding
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.viewmodel.CartPageFragmentViewModel
import com.squareup.picasso.Picasso

class CartAdapter(var mContext: Context,
                  var cartList: List<Arts>,
                  var viewModel: CartPageFragmentViewModel)
     : RecyclerView.Adapter<CartAdapter.CategoryDesignHolder>() {

    inner class CategoryDesignHolder(cardDesignBinding: CartPageCardDesignBinding)
        : RecyclerView.ViewHolder(cardDesignBinding.root){

        var cardDesignBinding: CartPageCardDesignBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design = CartPageCardDesignBinding.inflate(layoutInflater,parent,false)
        return CategoryDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CategoryDesignHolder, position: Int) {

        val art = cartList.get(position)

        val view = holder.cardDesignBinding
        view.cartObject = art

        val url = art.art_image_url
        Picasso.get().load(url).into(holder.cardDesignBinding.imageViewCartArt)

        if (art.is_campaign_product == 1) {
            holder.cardDesignBinding.textViewCartArtCampaignPrice.isVisible = true

        }
        
        holder.cardDesignBinding.buttonRemoveCart.setOnClickListener {
            viewModel.deleteFromCart(art.art_id, 0)
        }

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}