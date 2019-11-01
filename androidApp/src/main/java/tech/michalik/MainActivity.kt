package tech.michalik

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.properties.Delegates

import org.common.RemoteClient

class MainActivity : AppCompatActivity() {

  private var rootLayout: LinearLayout by Delegates.notNull()

  val remoteClient = RemoteClient()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    rootLayout = findViewById(R.id.main_view) as LinearLayout
    rootLayout.removeAllViews()

    val tv = TextView(this)

    rootLayout.addView(tv)
  }
}