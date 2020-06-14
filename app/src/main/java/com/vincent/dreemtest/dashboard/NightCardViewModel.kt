package com.vincent.dreemtest.dashboard

import android.content.Context
import com.vincent.dreemtest.R
import com.vincent.dreemtest.domain.entity.Night
import org.koin.java.KoinJavaComponent.inject
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

data class NightCardViewModel(val id: String, val title: String, val sleepScore: String)

internal fun Night.toCardViewModel(): NightCardViewModel {
    val context: Context by inject(Context::class.java)
    val dateFormat = DateTimeFormatter.ofPattern(context.getString(R.string.card_night_date_format))
    return NightCardViewModel(
        id = id,
        title = recordDateRange.endInclusive.format(dateFormat),
        sleepScore = context.getString(R.string.card_night_score, (sleepScore * 100f).roundToInt())
    )
}