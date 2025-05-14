package com.codez.sportsapp.presentation.match.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codez.sportsapp.R
import com.codez.sportsapp.presentation.match.viewdata.MatchDetailsViewData
import com.codez.sportsapp.presentation.match.viewdata.MatchInfoViewData
import com.codez.sportsapp.presentation.match.viewdata.dummyMatch

@Composable
fun MatchInfoView(
    modifier: Modifier = Modifier,
    matchInfoViewData: MatchInfoViewData
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DetailsText(
            text = stringResource(
                R.string.match_details_card_kick_off,
                matchInfoViewData.kickOff
            )
        )
        DetailsText(text = matchInfoViewData.date)
        DetailsText(text = matchInfoViewData.location)
        matchInfoViewData.attendance?.let { attendance ->
            DetailsText(text = stringResource(R.string.match_details_card_attendance, attendance))
        }
        matchInfoViewData.referee?.let { referee ->
            DetailsText(text = stringResource(R.string.match_details_card_referee, referee))
        }
    }
}

@Composable
private fun DetailsText(
    text: String
) {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
private fun MatchInfoViewPreview() {
    MatchInfoView(
        modifier = Modifier.background(Color.White),
        dummyMatch().matchInfo
    )
}