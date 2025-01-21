package com.rure.angkorlife.presentation.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.rure.angkorlife.R
import com.rure.angkorlife.presentation.MainActivity
import com.rure.angkorlife.presentation.component.RoundButton
import com.rure.angkorlife.presentation.component.VoteConfirmDialog
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.BackgroundBlack2
import com.rure.angkorlife.ui.theme.BoxBackground
import com.rure.angkorlife.ui.theme.ButtonBlue
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.ToggleWhite
import com.rure.angkorlife.ui.theme.TransparentBlack
import com.rure.angkorlife.ui.theme.TransparentWhite
import com.rure.angkorlife.ui.theme.White

@Composable
fun ProfileScreen(
    candidateId: Int,
    mainViewModel: MainViewModel = viewModel(LocalContext.current as MainActivity)
) {
    val tag = "ProfileScreen"

    val bottomPadding = remember { mutableStateOf(0) }

    val candidateProfiles by mainViewModel.candidateProfileData.collectAsState()
    val targetCandidate = remember {
        derivedStateOf {
            candidateProfiles.firstOrNull {
                it.candidateId == candidateId
            } ?: throw Exception("ProfileScreen: Wrong CandidateId.")
        }
    }

    val showDialog = remember { mutableStateOf(false) }
    val onVote = { id: String, context: Context ->
        if(mainViewModel.vote(id)) { showDialog.value = true }
        else Toast.makeText(context, context.getString(R.string.no_more_vote), Toast.LENGTH_SHORT).show()
    }

    val pagerState = rememberPagerState(0, 0.0f) { targetCandidate.value.profileUrls.size }
    val scrollState = rememberScrollState()

    if(showDialog.value) {
        VoteConfirmDialog {
            showDialog.value = false
        }
    }

    Box(
        modifier = Modifier.wrapContentSize().background(color = BackgroundBlack2),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth().height(LocalConfiguration.current.screenWidthDp.dp),
                    state = pagerState,
                    userScrollEnabled = true
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(targetCandidate.value.profileUrls[pagerState.currentPage])
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.profile_placeholder),
                        modifier = Modifier.fillMaxSize()
                            .background(color = BoxBackground),
                        error = painterResource(R.drawable.fail_profile),
                        contentScale = ContentScale.Crop,
                        onSuccess = { Log.d(tag, "Image load success") },
                        onError = { Log.d(tag, "Image load Error: ${it.result.throwable.message}") },
                    )
                }

                Row(
                    modifier = Modifier.offset(y = (-10).dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (0..<pagerState.pageCount).forEach {
                        val color = if(it == pagerState.currentPage) ButtonBlue
                        else ToggleWhite

                        Spacer(modifier = Modifier.size(8.dp).background(color = color, shape = CircleShape))
                    }
                }
            }
            Spacer(modifier = Modifier.height(26.dp))

            Column(
                modifier = Modifier.wrapContentSize().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = targetCandidate.value.name,
                    color = White,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    lineHeight = 26.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(R.string.entry_num, targetCandidate.value.candidateNumber),
                    color = TextBlue,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(26.dp))


            Column(
                modifier = Modifier.wrapContentSize()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .background(color = TransparentWhite, shape = RoundedCornerShape(6.dp)),
                horizontalAlignment = Alignment.Start
            ) {
                FieldToValueText(stringResource(R.string.education), targetCandidate.value.education)
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(BoxBackground))
                FieldToValueText(stringResource(R.string.major), targetCandidate.value.major)
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(BoxBackground))
                FieldToValueText(stringResource(R.string.hobbies), targetCandidate.value.hobby)
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(BoxBackground))
                FieldToValueText(stringResource(R.string.talent), targetCandidate.value.talent)
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(BoxBackground))
                FieldToValueText(stringResource(R.string.ambition), targetCandidate.value.ambition)
            }

            Spacer(modifier = Modifier.height(26.dp))
            Image(
                painter = painterResource(R.drawable.copy_right),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height((bottomPadding.value/2).dp))
        }

        Box(
            modifier = Modifier.background(color = TransparentBlack).padding(top = 12.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                .onSizeChanged {
                    bottomPadding.value = it.height
                },
            contentAlignment = Alignment.Center
        ) {
            if(targetCandidate.value.voted) {
                RoundButton(
                    label = stringResource(R.string.vote_str),
                    color = White,
                    textColor = ButtonBlue,
                    drawableId = R.drawable.voted,
                    modifier = Modifier.fillMaxWidth()
                ) {

                }
            } else {
                val context = LocalContext.current
                RoundButton(
                    label = stringResource(R.string.vote_str),
                    color = ButtonBlue,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    onVote(candidateId.toString(), context)
                }
            }
        }


    }
}

@Composable
private fun FieldToValueText(field: String, value: String) {
    Column(
        modifier = Modifier.wrapContentSize().padding(vertical = 12.dp,  horizontal = 14.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = field,
            color = TextGray,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = value,
            color = White,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 19.sp
        )

    }
}