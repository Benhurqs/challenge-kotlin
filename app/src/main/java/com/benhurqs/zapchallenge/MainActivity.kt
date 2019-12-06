package com.benhurqs.zapchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Actions.openListIntent(this))

    }
}
