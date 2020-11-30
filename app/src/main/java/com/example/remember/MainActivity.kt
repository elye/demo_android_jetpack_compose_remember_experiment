package com.example.remember

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isGrayScale by remember { mutableStateOf(false) }
            val colorChoice by remember(isGrayScale) {
                mutableStateOf(
                    if (isGrayScale)
                        ColorChoice(onColor = Color.White, offColor = Color.Black)
                    else
                        ColorChoice(onColor = Color.Green, offColor = Color.Red)
                )
            }
            Box(modifier = Modifier.fillMaxSize().background(colorChoice.color))
            Button(
                modifier = Modifier.padding(64.dp).fillMaxSize(),
                onClick = colorChoice::toggle) {
                Text(
                    text = if (colorChoice.isOn) "On" else "Off",
                    color = colorChoice.color,
                    fontSize = 48.sp
                )
            }
            Switch(
                modifier = Modifier.padding(64.dp).fillMaxWidth(),
                checked = isGrayScale,
                onCheckedChange = {isGrayScale = !isGrayScale}
            )
        }
    }

    class ColorChoice(
        private val onColor: Color,
        private val offColor: Color
    ) {
        var isOn = false
        var color by mutableStateOf(offColor)
        fun toggle() {
            isOn = !isOn
            color = if(isOn) {
                onColor
            } else {
                offColor
            }
        }
    }
}
