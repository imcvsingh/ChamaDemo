package com.cvsingh.chamademo.view.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.databinding.ItemPlaceBinding
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.view.listeners.OnRecyclerViewItemClickListener
import com.cvsingh.chamademo.viewmodel.PlacesViewModel

class PlacesListAdapter: RecyclerView.Adapter<PlacesListAdapter.ViewHolder>() {
    private lateinit var placeModelList:List<PlaceModel>
    private lateinit var listener: OnRecyclerViewItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPlaceBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_place, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(placeModelList[position])
    }

    override fun getItemCount(): Int {
        return if(::placeModelList.isInitialized) placeModelList.size else 0
    }

    fun updatePostList(placeModelList : List<PlaceModel>, listener: OnRecyclerViewItemClickListener){
        this.placeModelList = placeModelList
        this.listener = listener
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPlaceBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private val viewModel = PlacesViewModel()

        fun bind(result:PlaceModel){
            viewModel.bind(result)
            binding.viewModel = viewModel
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(binding.root, adapterPosition)
        }
    }
}