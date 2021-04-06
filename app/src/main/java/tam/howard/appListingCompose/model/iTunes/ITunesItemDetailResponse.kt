package tam.howard.appListingCompose.model.iTunes

import kotlinx.serialization.Serializable

@Serializable
data class ITunesItemDetailResponse(val results: List<ITunesItemDetail> = listOf())