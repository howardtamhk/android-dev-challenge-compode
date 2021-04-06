package tam.howard.appListingCompose.model.iTunes

import kotlinx.serialization.Serializable

@Serializable
data class ITunesListingResponse(
    val feed: ITunesListingResponseFeedModel?
)

@Serializable
data class ITunesListingResponseFeedModel(val entry: List<ITunesListingItem> = listOf())