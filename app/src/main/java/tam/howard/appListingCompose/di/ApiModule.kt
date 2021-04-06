package tam.howard.appListingCompose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import tam.howard.appListingCompose.BuildConfig
import tam.howard.appListingCompose.provider.api.ITunesApiProvider
import tam.howard.appListingCompose.provider.api.ResultCallAdapterFactory
import tam.howard.appListingCompose.util.config.EnvironmentConstant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.SHOW_LOG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }.build()
    }

    @Provides
    fun providesResultCallAdapterFactory(): ResultCallAdapterFactory {
        return ResultCallAdapterFactory()
    }

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        resultCallAdapterFactory: ResultCallAdapterFactory,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(EnvironmentConstant.API_BASE_URL).client(okHttpClient)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }.asConverterFactory("application/json".toMediaType())
            ).addCallAdapterFactory(resultCallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesITunesApiProvider(retrofit: Retrofit): ITunesApiProvider {
        return retrofit.create(ITunesApiProvider::class.java)
    }
}