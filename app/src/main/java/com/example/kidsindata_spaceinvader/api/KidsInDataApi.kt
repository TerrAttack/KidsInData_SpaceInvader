package com.example.kidsindata_spaceinvader.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KidsInDataApi {

    companion object {
        // The base url off the api.
        private const val baseUrl = "https://kid-trial.azurewebsites.net"

        /**
         * @return [KidsInDataApiService] The service class off the retrofit client.
         */
        fun createApi(): KidsInDataApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val dataJourneyApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit DataJourneyApiService
            return dataJourneyApi.create(KidsInDataApiService::class.java)
        }
    }

}