package com.luxsh.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luxsh.model.PostOfficeItem
import com.luxsh.reelimigration.PostOfficeDetailActivity
import com.luxsh.reelimigration.databinding.PostOfficeItemsBinding

/**
 * Created by pinal-leza on 1/19/2018.
 */


class ItemListAdapter(
    private val arrListProductsData: ArrayList<PostOfficeItem>?,
    private val context: Activity
) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {
    lateinit var dialogBinding: PostOfficeItemsBinding
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = arrListProductsData!![position]
        dialogBinding.txtname.text = p.Name

        dialogBinding.linMainHolder.setOnClickListener {
             val i = Intent(context, PostOfficeDetailActivity::class.java)
            i.putExtra("model",p)
             context.startActivity(i)
        }

    }


    override fun getItemCount(): Int = arrListProductsData!!.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        dialogBinding = PostOfficeItemsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dialogBinding)
    }

    class ViewHolder(view: PostOfficeItemsBinding) : RecyclerView.ViewHolder(view.root) {


    }

}