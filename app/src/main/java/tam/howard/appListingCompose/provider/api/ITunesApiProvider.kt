package tam.howard.appListingCompose.provider.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tam.howard.appListingCompose.model.core.Result
import tam.howard.appListingCompose.model.iTunes.ITunesItemDetailResponse
import tam.howard.appListingCompose.model.iTunes.ITunesListingResponse

interface ITunesApiProvider {

    @GET("rss/topfreeapplications/limit={limit}/json")
    suspend fun getTopFreeApplicationsList(@Path("limit") limit: Int = 100): Result<ITunesListingResponse>

    @GET("rss/topgrossingapplications/limit={limit}/json")
    suspend fun getTopGrossingApplicationsList(@Path("limit") limit: Int = 10): Result<ITunesListingResponse>

    @GET("lookup")
    suspend fun getItemsDetail(@Query("id") appIdList: String): Result<ITunesItemDetailResponse>

}