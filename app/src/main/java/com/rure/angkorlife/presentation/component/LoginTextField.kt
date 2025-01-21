package com.rure.angkorlife.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rure.angkorlife.R
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White

@Composable
fun LoginTextField(
    content: String,
    onChange: (String) -> Unit
) {
    BasicTextField(
        value = content,
        onValueChange = {
            onChange(it)
        },
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .border(border = BorderStroke(1.dp, White), shape = RoundedCornerShape(8.dp))
            .padding(vertical = 15.dp, horizontal = 16.dp),
        singleLine = true
    ) {
        if(content.isEmpty()) {
            Text(
                text = stringResource(R.string.enter_id),
                color = TextGray2,
                fontWeight = FontWeight.W400,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        } else {
            Text(
                text = content,
                color = White,
                fontWeight = FontWeight.W400,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}