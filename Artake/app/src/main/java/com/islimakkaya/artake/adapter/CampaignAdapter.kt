package com.islimakkaya.artake.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.islimakkaya.artake.databinding.CampaignPageCardDesignBinding
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.fragment.CampaignPageFragmentDirections
import com.islimakkaya.artake.viewmodel.CampaignPageFragmentViewModel
import com.squareup.picasso.Picasso


class CampaignAdapter(var mContext: Context,
                      var campaignList: List<Arts>,
                      var viewModel: CampaignPageFragmentViewModel):
        RecyclerView.Adapter<CampaignAdapter.CategoryDesignHolder>(){

    inner class CategoryDesignHolder(cardDesignBinding: CampaignPageCardDesignBinding)
        : RecyclerView.ViewHolder(cardDesignBinding.root){

        var cardDesignBinding: CampaignPageCardDesignBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design = CampaignPageCardDesignBinding.inflate(layoutInflater, parent, false)
        return CategoryDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CategoryDesignHolder, position: Int) {

        val art = campaignList.get(position)

        val view = holder.cardDesignBinding
        view.campaignObject = art

        val url = art.art_image_url
        Picasso.get().load(url).into(holder.cardDesignBinding.imageViewCampaignProduct)



        holder.cardDesignBinding.buttonCampaignInfo.setOnClickListener {

            val transition = CampaignPageFragmentDirections.campaigntoDetail(art)
            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return campaignList.size
    }

    fun getCampaignPrice(price: Int) {

    }
}
