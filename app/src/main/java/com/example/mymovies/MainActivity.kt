package com.example.mymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymovies.ui.screens.main.MainScreen
import com.example.mymovies.ui.screens.detail.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(navController)
                }
                composable(
                    route = "detail/{mediaId}",
                    arguments = listOf(
                        navArgument("mediaId") { type = NavType.IntType }
                    )
                ) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("mediaId")
                    requireNotNull(id, { "No puede ser nulo porque el detalle siempre necesita un ID" })
                    DetailScreen(id)
                }
            }
        }
    }
}
