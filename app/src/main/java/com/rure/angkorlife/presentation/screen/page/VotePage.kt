package com.rure.angkorlife.presentation.screen.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rure.angkorlife.R
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateList
import com.rure.angkorlife.presentation.component.ProfileView
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.TextBlue
import com.rure.angkorlife.ui.theme.TextGray2
import com.rure.angkorlife.ui.theme.White
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun VotePage(
    toProfileScreen: (CandidateList) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val votedCandidateList = remember {
        //TODO: 고치기
        mutableStateOf(listOf<CandidateDetail>())
    }
    val candidateList = remember {
        //TODO: 고치기
        mutableStateOf(listOf<CandidateList>())
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.heightIn(min = 0.dp, max = 9999.dp).fillMaxWidth().padding(horizontal = 19.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        item {
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

        itemsIndexed(candidateList.value) { index, item ->
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

        item {
            Spacer(modifier = Modifier.height(28.dp))
        }
    }

//    Column(
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        Spacer(modifier = Modifier.height(50.dp))
//        Spacer(modifier = Modifier.height(3.dp).width(20.dp).background(color = TextBlue))
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Text(
//            text = stringResource(R.string.candidate_title),
//            color = White,
//            fontWeight = FontWeight.W600,
//            fontSize = 28.sp,
//            lineHeight = 29.sp
//        )
//        Spacer(modifier = Modifier.height(25.dp))
//
//        Text(
//            text = stringResource(R.string.vote_info),
//            color = TextGray2,
//            fontWeight = FontWeight.W400,
//            fontSize = 14.sp,
//            lineHeight = 18.sp
//        )
//        Spacer(modifier = Modifier.height(40.dp))
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier.fillMaxWidth().padding(horizontal = 19.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalArrangement = Arrangement.spacedBy(40.dp)
//        ) {
//            itemsIndexed(candidateList.value) { index, item ->
//                ProfileView(
//                    candidateProfile = item,
//                    isVoted = votedCandidateList.value.any {
//                        item.id == it.id
//                    },
//                    onProfileClick = {
//                        toProfileScreen(item)
//                    },
//                    onVote = {
//                        //TODO: Do Vote
//
//                    }
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(28.dp))
//    }
}