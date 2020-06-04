package ar.edu.unlam.clonmercadolibre.data

import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private val argentina = "MLA"

    private fun getApi(): MercadolibreAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/$argentina/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(MercadolibreAPI::class.java)
    }

    fun search(q: String, callback: Callback<SearchResult>) {
        getApi().search(q.replace(" ", "+")).enqueue(callback)
    }

    /*
    fun getArticle(id: String, callback: Callback<Article>){
        getApi().getItem(id).enqueue(callback)
    }
     */
}