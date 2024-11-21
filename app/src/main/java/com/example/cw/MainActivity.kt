package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.cw.domain.di.appModule
import com.example.cw.screens.home.HomeScreen
import com.example.cw.screens.home.HomeViewModel
import com.example.cw.ui.theme.CwTheme
import com.google.firebase.FirebaseApp
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            CwTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val homeViewModel: HomeViewModel = get()
                    Column(
                        modifier = Modifier.padding(
                            innerPadding
                        )
                    ) {
                        HomeScreen(viewModel = homeViewModel)
                    }
                }
            }
        }

    }
}