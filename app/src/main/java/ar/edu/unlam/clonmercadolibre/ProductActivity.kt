package ar.edu.unlam.clonmercadolibre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.edu.unlam.clonmercadolibre.data.MercadolibreSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
    }

    /*
    private fun setProductInView(body: MercadolibreSearchResult){
        body.let{
            txtArticle.text = body.title
            txtPrice.text = body.price.toString()

            Picasso.get()
                .load(it.thumbnail)
        }
    }
    */
}
