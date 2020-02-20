package com.cvsingh.chamademo.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.api.ApiService
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.utils.extensions.getParentActivity
import com.cvsingh.chamademo.view.adapters.PlacesListAdapter
import com.cvsingh.chamademo.view.listeners.OnRecyclerViewItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlacesListViewModel : ViewModel(), OnRecyclerViewItemClickListener {


    private lateinit var resultsList: List<PlaceModel>
    val placesListAdapter = PlacesListAdapter()

    val type: MutableLiveData<String> = MutableLiveData()

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
                "RESTRAURANT",
                //"cruise",
                "API_KEY"
            )
         .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .doOnError { throwable ->Log.e("Hello", "Error: ${throwable.message}") }
                .subscribe(

                    { result ->Log.e("Hello", "Success: ${result.status} results")},
                    { Log.e("Hello", "Error: ") }
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
        Log.e("Hello", "Success: $resultsList")
        this.resultsList = resultsList
        placesListAdapter.updatePostList(resultsList, this)
    }

    private fun onRetrievePostListError() {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.error_msg
        Log.e("Hello", "Error: ")
    }


    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(view.getParentActivity(), resultsList.get(position).name, Toast.LENGTH_LONG)
            .show()
    }
}
