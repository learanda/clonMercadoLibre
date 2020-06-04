package ar.edu.unlam.clonmercadolibre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ar.edu.unlam.clonmercadolibre.data.Api
import ar.edu.unlam.clonmercadolibre.data.Article
import ar.edu.unlam.clonmercadolibre.data.SearchResult
import ar.edu.unlam.clonmercadolibre.utils.hideKeyboard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnBuscar
import kotlinx.android.synthetic.main.activity_main.editTextField
import kotlinx.android.synthetic.main.activity_main.imageBTNCarrito
import kotlinx.android.synthetic.main.activity_main.txtArticle
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageBTNCarrito.setOnClickListener{
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        btnBuscar.setOnClickListener { search(editTextField.editText?.text.toString()) }
        btnGoToProduct.setOnClickListener { goToProduct() }
    }

    private fun search(q: String){
        hideKeyboard()
        Api().search(q, object : Callback<SearchResult> {

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ocurrió un error",Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {

                when (response.code()) {
                    in 200..299 -> setArticleValues(response.body()!!)
                    404 -> Toast.makeText(
                        this@MainActivity,
                        R.string.resource_not_found,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    400 -> Toast.makeText(
                        this@MainActivity,
                        R.string.bad_request,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    in 500..599 -> Toast.makeText(
                        this@MainActivity,
                        R.string.server_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    else -> Toast.makeText(
                        this@MainActivity,
                        R.string.unknown_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

        })
    }


            private fun setArticleValues(body: SearchResult) {
                if (body.results.isNotEmpty()) {
                        txtArticle.text = body.toString()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.not_found,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

            private fun goToProduct(){
                startActivity(Intent(this,ProductActivity::class.java))
                setContentView(R.layout.activity_product)
            }


            /*private fun setArticleValues(body: SearchResult) {
                if (body.paging.total > 0) {
                    body.results.get(0).let { firstArticle ->
                        txtArticle.text = firstArticle.title
                        txtPrice.text = firstArticle.price.toString()

                        Picasso.get()
                            .load(firstArticle.thumbnail)
                            .error(R.drawable.ic_error)
                            .into(imgArticle)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.not_found,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }*/






            /*override fun onFailure(call: Call<Article>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ocurrió un error",Toast.LENGTH_LONG)
                    .show()
            }*/

            /*override fun onResponse(call: Call<Article>, response: Response<Article>) {

                if(response.isSuccessful){
                    val article = response.body()
                    txtArticle.text = "Titulo:${article?.title} Precio:${article?.price}"

                    Picasso.get()
                        .load(article?.thumbnail)
                        .into(imgArticle)
                }else{
                    Toast.makeText(this@MainActivity, "Item no encontrado",Toast.LENGTH_LONG)
                        .show()
                }
            }*/


}
