package com.rure.angkorlife.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rure.angkorlife.R
import com.rure.angkorlife.ui.theme.BackgroundBlack
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.TextGray3
import com.rure.angkorlife.ui.theme.White
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun CountDownView(
    goal: Duration,
    modifier: Modifier = Modifier
) {
    val day = goal.toDays().toInt()
    val hour = goal.toHours().toInt() - day * 24
    val min = goal.toMinutes().toInt() - goal.toHours().toInt() * 60
    val sec = goal.toSeconds().toInt()- goal.toMinutes().toInt() * 60

    Row(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NumberBox(day, "DAY")
        ColonText()
        NumberBox(hour, "HR")
        ColonText()
        NumberBox(min, "MIN")
        ColonText()
        NumberBox(sec, "SEC")
    }
}

@Composable
private fun NumberBox(
    number: Int,
    fieldName: String
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(48.dp).background(color = BackgroundBlack, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = White,
                fontWeight = FontWeight.W500,
                fontSize = 22.sp,
                lineHeight = 26.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = fieldName,
            color = TextGray3,
            fontWeight = FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 12.sp
        )
    }

}

@Composable
private fun ColonText() {
    Text(
        text = stringResource(R.string.colon),
        color = White,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
        lineHeight = 26.sp
    )
}