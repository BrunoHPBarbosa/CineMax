package com.devspacecinenow

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devspacecinenow.detail.presentation.MovieDetailScreen
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.list.presentation.ui.MovieListScreen

@Composable
fun CineMaxApp(
    listViewModel : MovieListViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Movie List") {
        composable(route = "Movie List") {
            MovieListScreen(navController,listViewModel)
        }
        composable(route = "Movie Detail" + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.StringType
            })

            ) { backStackEntry ->
            val movieId = requireNotNull( backStackEntry.arguments?.getString("itemId"))
            MovieDetailScreen(movieId, navController)
        }
    }

}