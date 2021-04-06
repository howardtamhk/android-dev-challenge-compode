package tam.howard.appListingCompose.ui.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tam.howard.appListingCompose.base.BaseViewModel
import tam.howard.appListingCompose.model.core.Result
import tam.howard.appListingCompose.model.iTunes.ITunesItemDetail
import tam.howard.appListingCompose.repository.ITunesRepository
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val iTunesRepository: ITunesRepository) :
    BaseViewModel() {

    val topFreeApplication: LiveData<List<ITunesItemDetail>> = liveData {
        when (val result = iTunesRepository.getTopFreeApplications()) {
            is Result.Success -> result.value
            is Result.Failure -> listOf()
        }
    }


}