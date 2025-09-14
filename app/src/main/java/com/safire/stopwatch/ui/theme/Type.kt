package com.safire.stopwatch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.safire.stopwatch.R

// Default Material 3 typography values
val baseline = Typography()

val figtreeFontFamily = FontFamily(Font(R.font.figtree))

val AppTypography = Typography(
    bodyLarge = baseline.bodyLarge.copy(fontFamily = figtreeFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = figtreeFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = figtreeFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = figtreeFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = figtreeFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = figtreeFontFamily),
)