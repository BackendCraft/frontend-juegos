package com.ebc.loteriamulti.views.navviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ebc.loteriamulti.R
import com.ebc.loteriamulti.ui.theme.LoteriaMultiTheme

@Composable
fun MainView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lotería Clásica
            GameOption(
                idRaw = R.raw.lottie_loteria,
                label = stringResource(R.string.lottery),
                onClick = { navController.navigate("loteria") }
            )

            // Adivina el Número
            GameOption(
                idRaw = R.raw.lottie_adivina,
                label = stringResource(R.string.guess_number),
                onClick = { navController.navigate("adivina") }
            )

            // Par/Impar
            GameOption(
                idRaw = R.raw.lottie_parimpar,
                label = stringResource(R.string.par_impar),
                onClick = { navController.navigate("parimpar") }
            )
        }
    }
}

@Composable
fun GameOption(
    idRaw: Int = R.raw.lottie_loteria,
    label: String = "Opción",
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(idRaw)
        )
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    LoteriaMultiTheme {
        MainView(rememberNavController())
    }
}