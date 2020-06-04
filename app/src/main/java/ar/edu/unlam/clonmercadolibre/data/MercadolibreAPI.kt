package ar.edu.unlam.clonmercadolibre.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadolibreAPI {

    @GET("search")
    fun search(@Query("q") q: String): Call<SearchResult>

    /*
    @GET("items/{itemId}")
    fun getItem(@Path("itemId") itemId: String): Call<Article>
     */
}