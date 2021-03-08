package com.example.breakingbadcharacters.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.domain.model.BBCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detailed.*

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val model = intent.getParcelableExtra<BBCharacter>("bbcharacter")
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.download_wheel)
            .error(R.drawable.loaderror)
            .transforms(
                FitCenter(),
                RoundedCorners(26)
            )

        model?.apply {
            Glide.with(this@DetailedActivity)
                .applyDefaultRequestOptions(requestOptions)
                .load(img)
                .into(bbccharacter_image)

            bbccharacter_name.coloredText =
                getResource(R.string.detailed_activity_name).bold + " " + name.trim().redColor
            val builder = StringBuilder()
            for (item in occupation) {
                builder.append(item + "\n")
            }
            bbccharacter_occupation.coloredText =
                getResource(R.string.detailed_activity_Occupation).bold + " " + builder.toString()
                    .trim().redColor
            bbccharacter_status.coloredText =
                getResource(R.string.detailed_activity_Status).bold + " " + status.trim().redColor
            bbccharacter_nickname.coloredText =
                getResource(R.string.detailed_activity_nickname).bold + " " + nickname.trim().redColor
            val builder2 = StringBuilder()
            for (item in appearance) {
                builder2.append(item.toString() + ",")
            }
            if (builder2.length > 0) builder2.deleteCharAt(builder2.length - 1)

            bbccharacter_season_appearance.coloredText =
                getResource(R.string.detailed_activity_Season_Appearance).bold + " " + builder2.toString()
                    .trim().redColor
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }
}