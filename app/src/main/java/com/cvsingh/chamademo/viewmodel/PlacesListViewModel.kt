package com.cvsingh.chamademo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import android.widget.Toast
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.api.ApiService
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.utils.extensions.getParentActivity
import com.cvsingh.chamademo.view.adapters.PlacesListAdapter
import com.cvsingh.chamademo.view.listeners.OnRecyclerViewItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlacesListViewModel() : ViewModel(), OnRecyclerViewItemClickListener {

    private lateinit var resultsList: List<PlaceModel>
    val placesListAdapter = PlacesListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {
        subscription =
            ApiService.requestInterface.getPlacesList(
                "28.5355,77.3910",
                15000,
                "Restaurant",
                "AIzaSyDOJEpZU5bnyGrXKPj8gSM4w_IndnyeqRg"
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(

                    { result -> onRetrievePostListSuccess(result.results) },
                    { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(resultsList: List<PlaceModel>) {
        this.resultsList = resultsList
        placesListAdapter.updatePostList(resultsList, this)
    }

    private fun onRetrievePostListError() {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.error_msg
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(view.getParentActivity(), resultsList.get(position).name, Toast.LENGTH_LONG)
            .show()
    }
}