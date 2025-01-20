package com.rure.angkorlife.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rure.angkorlife.ui.theme.ButtonBlue
import com.rure.angkorlife.ui.theme.White

@Composable
fun RoundButton(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.background(color = color, shape = RoundedCornerShape(999.dp)).clickable {
            onClick()
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = White,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}