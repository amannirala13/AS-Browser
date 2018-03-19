package amansoftdevelopers.com.asbrowser

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var relativeLayout: RelativeLayout? = null
    private var animationDrawable: AnimationDrawable? = null
    private var floatBtn: FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init constraintLayout
        relativeLayout = findViewById(R.id.Relativelayout)

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = relativeLayout!!.background as AnimationDrawable

        // setting enter fade animation duration to 5 seconds
        animationDrawable!!.setEnterFadeDuration(5000)

        // setting exit fade animation duration to 2 seconds
        animationDrawable!!.setExitFadeDuration(2000)
      var toast: Toast=Toast.makeText(applicationContext,"Welcome\n Developer: Aman Nirala", Toast.LENGTH_LONG)
        toast.setMargin(0F,0F)
        toast.show()

        //OnCLick Listener of FloatButton
        floatBtn = findViewById(R.id.fab)
        floatBtn!!.setOnClickListener(ClickListen)

    }


    override fun onResume() {
        super.onResume()
        if (animationDrawable != null && !animationDrawable!!.isRunning) {
            // start the animation
            animationDrawable!!.start()
        }

    }

    override fun onPause() {
        super.onPause()
        if (animationDrawable != null && animationDrawable!!.isRunning) {
            // stop the animation
            animationDrawable!!.stop()
        }
    }
    private var ClickListen: View.OnClickListener? = View.OnClickListener()
    {
       val intent = Intent(this, web_view::class.java)
        intent.putExtra("args", getText())
        startActivity(intent)
    }
    fun getText():String
    {
        return link.text.toString()
    }
}