package com.rure.angkorlife.presentation.screen.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rure.angkorlife.R
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.presentation.MainActivity
import com.rure.angkorlife.presentation.component.ProfileView
import com.rure.angkorlife.presentation.state.ProfileData
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White

@Composable
fun VotePage(
    toProfileScreen: (ProfileData) -> Unit,
    mainViewModel: MainViewModel = viewModel(LocalContext.current as MainActivity)
) {
    val tag = "VotePage"

    val candidateProfiles by mainViewModel.candidateProfileData.collectAsState()

    val maxHeight = remember { mutableStateOf(9999) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().heightIn(0.dp, maxHeight.value.dp).padding(horizontal = 19.dp)
            .onSizeChanged { maxHeight.value = it.height },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        userScrollEnabled = false
    ) {
        Log.d(tag, "State list: ${candidateProfiles.joinToString { it.name }}")

        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Column {
                Spacer(modifier = Modifier.height(50.dp))
                Spacer(modifier = Modifier.height(3.dp).width(20.dp).background(color = TextBlue))
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.candidate_title),
                    color = White,
                    fontWeight = FontWeight.W600,
                    fontSize = 28.sp,
                    lineHeight = 29.sp
                )
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = stringResource(R.string.vote_info),
                    color = TextGray2,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        itemsIndexed(candidateProfiles) { index, item ->
            ProfileView(
                candidateProfile = item,
                isVoted = item.voted,
                onProfileClick = {
                    toProfileScreen(item)
                },
                onVote = {
                    mainViewModel.vote(item.candidateId.toString())
                }
            )
        }

        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Spacer(modifier = Modifier.height(28.dp).background(color = Color.Black))
            Image(
                painter = painterResource(R.drawable.copy_right),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}