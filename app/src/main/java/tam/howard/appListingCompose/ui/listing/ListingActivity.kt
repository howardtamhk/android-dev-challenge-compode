package tam.howard.appListingCompose.ui.listing

import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import tam.howard.appListingCompose.base.BaseActivity
import tam.howard.appListingCompose.ui.theme.MyTheme

@AndroidEntryPoint
class ListingActivity : BaseActivity<ListingViewModel>() {
    override val vm: ListingViewModel by viewModels()

    @Composable
    override fun ContentView() {
        MyTheme {
            Scaffold(topBar = {
                TopAppBar {}
            }) {
                Greeting(name = "Test")
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ContentView()
    }
}


