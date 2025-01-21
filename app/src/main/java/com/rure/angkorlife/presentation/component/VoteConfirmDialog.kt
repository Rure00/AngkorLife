package com.rure.angkorlife.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rure.angkorlife.R
import com.rure.angkorlife.ui.theme.BorderGray
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.White

@Composable
fun VoteConfirmDialog(
    onClickConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { onClickConfirm() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Column(
            modifier = Modifier.width(280.dp).wrapContentHeight()
                .background(color = White, shape = RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(R.string.vote_complete),
                color = Color.Black,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
            Text(
                text = stringResource(R.string.thank_voting),
                color = Color.Black,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(18.dp))

            Spacer(modifier = Modifier.fillMaxWidth().height((0.5).dp).background(color = BorderGray))
            Box(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { onClickConfirm() }.padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.confirm_str),
                    color = TextBlue,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}