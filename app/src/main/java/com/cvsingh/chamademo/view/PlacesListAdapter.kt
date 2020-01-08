package com.cvsingh.chamademo.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.databinding.ItemPlaceBinding
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.viewmodel.PlacesViewModel

class PlacesListAdapter: RecyclerView.Adapter<PlacesListAdapter.ViewHolder>() {
    private lateinit var placeModelList:List<PlaceModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesListAdapter.ViewHolder {
        val binding: ItemPlaceBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_place, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesListAdapter.ViewHolder, position: Int) {
        holder.bind(placeModelList[position])
    }

    override fun getItemCount(): Int {
        return if(::placeModelList.isInitialized) placeModelList.size else 0
    }

    fun updatePostList(placeModelList : List<PlaceModel>){
        this.placeModelList = placeModelList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPlaceBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PlacesViewModel()

        fun bind(result:PlaceModel){
            viewModel.bind(result)
            binding.viewModel = viewModel
        }
    }
}