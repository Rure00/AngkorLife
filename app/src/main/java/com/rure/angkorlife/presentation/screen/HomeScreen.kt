package com.rure.angkorlife.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.rure.angkorlife.R
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateList
import com.rure.angkorlife.presentation.component.CountDownView
import com.rure.angkorlife.presentation.component.ProfileView
import com.rure.angkorlife.presentation.screen.page.HomeCountPage
import com.rure.angkorlife.presentation.screen.page.VoteGuidePage
import com.rure.angkorlife.presentation.screen.page.VotePage
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White

@Composable
fun HomeScreen(
    toProfileScreen: (CandidateList) -> Unit
) {
    val listState = rememberLazyGridState()
    val votedCandidateList = remember {
        //TODO: 고치기
        mutableStateOf(listOf<CandidateDetail>())
    }
    val candidateList = remember {
        //TODO: 고치기
        //mutableStateOf(listOf<CandidateList>())
        listOf(
            CandidateList(
                id = 8310,
                candidateNumber = 6789,
                name = "Austin McCormick",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "vix"
            ),
            CandidateList(
                id = 7914,
                candidateNumber = 2086,
                name = "Reyna Mayo",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "sed"
            ),
            CandidateList(
                id = 5562,
                candidateNumber = 2694,
                name = "Alisha Lamb",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "habitant"
            ),
            CandidateList(
                id = 2158,
                candidateNumber = 7228,
                name = "Bernadette Tillman",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "porro"
            ),
            CandidateList(
                id = 5857,
                candidateNumber = 5807,
                name = "Enid Price",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "mandamus"
            ),
            CandidateList(
                id = 2390,
                candidateNumber = 6986,
                name = "Noreen Hudson",
                profileUrl = "https://angkorchat-bucket.s3.ap-southeast-1.amazonaws.com/candidate/47/7c21605a2fd04df796a047b86effcbcb.png",
                voteCnt = "graeci"
            )
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,
        modifier = Modifier.fillMaxWidth().background(color = Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Column(
                modifier = Modifier.fillMaxWidth().background(color = Color.Black)
            ) {
                HomeCountPage()
                VoteGuidePage()
            }
        }
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(color = Color.Black)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
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

        itemsIndexed(candidateList) { index, item ->
            ProfileView(
                candidateProfile = item,
                isVoted = votedCandidateList.value.any {
                    item.id == it.id
                },
                onProfileClick = {
                    toProfileScreen(item)
                },
                onVote = {
                    //TODO: Do Vote

                }
            )
        }

        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
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
}