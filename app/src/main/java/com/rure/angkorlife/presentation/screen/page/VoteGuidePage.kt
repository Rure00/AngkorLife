package com.rure.angkorlife.presentation.screen.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rure.angkorlife.R
import com.rure.angkorlife.ui.theme.BoxBackground
import com.rure.angkorlife.ui.theme.GradientBottom
import com.rure.angkorlife.ui.theme.GradientTop
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White

@Composable
fun VoteGuidePage(

) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GradientTop, GradientBottom
                    )
                )
            )
            .padding(horizontal = 16.dp),
        horizontalAlignment =  Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.guide_label),
            color = TextBlue,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 26.sp
        )
        Text(
            text = stringResource(R.string.guide_title),
            color = White,
            fontWeight = FontWeight.W600,
            fontSize = 28.sp,
            lineHeight = 29.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.guide_body),
            color = TextGray2,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth().background(color = BoxBackground, shape = RoundedCornerShape(4.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.period),
                    color = White,
                    fontWeight = FontWeight.W500,
                    fontSize = 13.sp,
                    lineHeight = 15.sp
                )
                Text(
                    text = stringResource(R.string.period_content),
                    color = White,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.how_to_vote),
                    color = White,
                    fontWeight = FontWeight.W500,
                    fontSize = 13.sp,
                    lineHeight = 15.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.vote_guide_content),
                    color = White,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}