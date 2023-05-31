package com.inin.learn.composelearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inin.learn.composelearn.ui.theme.AnsongLearnTheme

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TitleBar("Android Compose Learn")
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ComposeMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}

@Composable
fun TitleBar(title: String) {
    Row(modifier = Modifier.background(Color.Blue).height(56.dp).fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnsongLearnTheme {
        Greeting("Android")
    }
}