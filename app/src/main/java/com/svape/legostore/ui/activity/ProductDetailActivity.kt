package com.svape.legostore.ui.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.svape.legostore.R
import com.svape.legostore.core.Constants
import com.svape.legostore.data.model.store.Store
import com.svape.legostore.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    companion object {
        const val PRODUCT_KEY = Constants.keyDetaikProducts
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val product = intent.getSerializableExtra(PRODUCT_KEY) as? Store
        product?.let { setupProductDetails(it) }

        // Comenta o elimina estas líneas que causan el error
        // setSupportActionBar(binding.toolbar)
        // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // supportActionBar?.setDisplayShowTitleEnabled(false)

        // Configura la toolbar manualmente sin setSupportActionBar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        // Configurar botones
        binding.btnAddToCart.setOnClickListener {
            // Implementar lógica para añadir al carrito
        }

        binding.fabFavorite.setOnClickListener {
            // Implementar lógica para compartir o marcar como favorito
        }

        binding.imgProductDetail.setOnClickListener {
            showFullImageDialog(product?.image)
        }
    }

    private fun setupProductDetails(product: Store) {
        binding.tvProductName.text = product.name
        binding.tvProductPrice.text = "$ ${product.unitPrice}"
        binding.tvProductDescription.text = product.stock.toString() ?: "No hay descripción disponible para este producto."


        Glide.with(this)
            .load(product.image)
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.imgProductDetail)
    }


    private fun showFullImageDialog(imageUrl: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fullscreen_image)

        val imgFullScreen = dialog.findViewById<ImageView>(R.id.img_fullscreen)

        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(android.R.drawable.ic_dialog_alert)
            .into(imgFullScreen)

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.Gray))

        imgFullScreen.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}