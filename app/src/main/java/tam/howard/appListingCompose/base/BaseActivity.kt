package tam.howard.appListingCompose.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    abstract fun ContentView()
}