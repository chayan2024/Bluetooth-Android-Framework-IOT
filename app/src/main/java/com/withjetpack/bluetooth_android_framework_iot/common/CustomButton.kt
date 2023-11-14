package com.withjetpack.bluetooth_android_framework_iot.common
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier
        .background(Color.Red, shape = RectangleShape)
        .clickable {
        }
        .padding(20.dp)
    ) {

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}