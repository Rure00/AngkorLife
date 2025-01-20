package com.rure.angkorlife.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rure.angkorlife.R
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateList
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White

@Composable
fun ProfileView(
    candidateProfile: CandidateList,
    isVoted: Boolean,
    onProfileClick: () -> Unit,
    onVote: (Boolean) -> Unit
) {
    val tag = "ProfileView"

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(candidateProfile.profileUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(R.drawable.profile_placeholder),
            modifier = Modifier.size(156.dp).clickable { onProfileClick() },
            onSuccess = { Log.d(tag, "Image load success") },
            onError = { Log.d(tag, "Image load Error: ${it.result.throwable.message}") },
        )
        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = candidateProfile.name,
            color = White,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = candidateProfile.voteCnt,
            color = TextBlue,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 19.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        if(isVoted) {
            RoundButton(
                label = stringResource(R.string.vote_str),
                color = White,
                textColor = TextBlue,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        } else {
            RoundButton(
                label = stringResource(R.string.vote_str),
                color = TextBlue,
                modifier = Modifier.fillMaxWidth()
            ) {
                onVote(true)
            }
        }
    }


}