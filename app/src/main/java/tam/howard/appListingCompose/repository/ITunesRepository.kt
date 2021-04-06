package tam.howard.appListingCompose.repository

import tam.howard.appListingCompose.model.core.Result
import tam.howard.appListingCompose.model.core.ResultFailure
import tam.howard.appListingCompose.model.iTunes.ITunesItemDetail
import tam.howard.appListingCompose.provider.api.ITunesApiProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ITunesRepository @Inject constructor(private val iTunesApiProvider: ITunesApiProvider) {

    suspend fun getTopFreeApplications(): Result<List<ITunesItemDetail>> {
        return when (val result = iTunesApiProvider.getTopFreeApplicationsList()) {
            is Result.Success -> {
                val idListStr =
                    result.value.feed?.entry?.mapNotNull { it.id.attributes?.id }?.joinToString(",")
                idListStr?.let {
                    when (val detailResult = iTunesApiProvider.getItemsDetail(idListStr)) {
                        is Result.Success -> detailResult.map { it.results }
                        is Result.Failure -> detailResult
                    }

                } ?: Result.Failure(failure = ResultFailure.EmptyBody)
            }
            is Result.Failure -> result
        }
    }

    suspend fun getGrossingApplications(): Result<List<ITunesItemDetail>> {
        return when (val result = iTunesApiProvider.getTopGrossingApplicationsList()) {
            is Result.Success -> {
                val idListStr =
                    result.value.feed?.entry?.mapNotNull { it.id.attributes?.id }?.joinToString(",")
                idListStr?.let {
                    when (val detailResult = iTunesApiProvider.getItemsDetail(idListStr)) {
                        is Result.Success -> detailResult.map { it.results }
                        is Result.Failure -> detailResult
                    }

                } ?: Result.Failure(failure = ResultFailure.EmptyBody)
            }
            is Result.Failure -> result
        }
    }
}