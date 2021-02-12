package com.jjh.android.tablelayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jjh.android.tablelayoutdemo.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
    }

    fun addProduct(view: View) {
        Log.d(TAG, "addProduct")
        val pId = productId.text.toString()
        val pName = productName.text.toString()
        val pQuantity = productQuantity.text.toString()
        if (pId != "") {
            val product = Product(pId, pName, Integer.parseInt(pQuantity))
            products.add(product)
            clearFields()
            updateProductsView()
        } else {
            productId.setText("Requires an ID")
        }
    }

    fun findProduct(view: View) {
        Log.d(TAG, "findProduct")
    }

    fun deleteProduct(view: View) {
        Log.d(TAG, "deleteProduct")
    }

    private fun clearFields() {
        Log.d(TAG, "clearFields")
        productId.setText("")
        productName.setText("")
        productQuantity.setText("")
    }

    private fun updateProductsView() {
        Log.d(TAG, "updateProductsView")
        Log.d(TAG, products.toString())
        productsView.setText("")
        productsView.setText(products.toString())
    }

}