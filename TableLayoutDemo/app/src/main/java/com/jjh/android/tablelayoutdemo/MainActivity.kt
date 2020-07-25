package com.jjh.android.tablelayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.jjh.android.tablelayoutdemo.model.Product

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var id: EditText
    private lateinit var productName: EditText
    private lateinit var productQuantity: EditText
    private lateinit var productsView: EditText

    private val products: MutableList<Product> = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.productId)
        productName = findViewById(R.id.productName)
        productQuantity = findViewById(R.id.productQuantity)
        productsView = findViewById(R.id.productsView)
        productsView.isEnabled = false // Make the text field non editable

    }

    fun addProduct(view: View) {
        Log.d(TAG, "addProduct")
        val pId = id.text.toString()
        val pName = productName.text.toString()
        val pQuantity = productQuantity.text.toString()
        if (pId != "") {
            val product = Product(pId, pName, Integer.parseInt(pQuantity))
            products.add(product)
            clearFields()
            updateProductsView()
        } else {
            id.setText("Requires an ID")
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
        id.setText("")
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