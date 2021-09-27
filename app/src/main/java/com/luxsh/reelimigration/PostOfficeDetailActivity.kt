package com.luxsh.reelimigration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luxsh.model.PostOfficeItem
import com.luxsh.reelimigration.databinding.ActivityPostOfficeDetail2Binding
import com.luxsh.reelimigration.databinding.ActivityPostOfficeDetailBinding

class PostOfficeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostOfficeDetail2Binding
    private lateinit var data:PostOfficeItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostOfficeDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        data= intent.getSerializableExtra("model") as PostOfficeItem
        binding.txtName.text=data.Name
        binding.txtBranch.text=data.BranchType
        binding.txtTaluka.text=data.Taluk
        binding.txtDist.text=data.District
        binding.txtDivision.text=data.Division
        binding.txtPin.text=data.PINCode
    }
}