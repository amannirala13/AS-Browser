package amansoftdevelopers.com.asbrowser

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var relativeLayout: RelativeLayout? = null
    private var animationDrawable: AnimationDrawable? = null
    private var floatBtn: FloatingActionButton? = null
    private var signinBtn: SignInButton? = null
    private var gso: GoogleSignInOptions? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var RC_SIGN_IN: Int = 1
    private var WelcomeText:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Signing
        signinBtn = findViewById(R.id.googleSIbtn)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(applicationContext, gso!!)

        signinBtn!!.setOnClickListener { view: View? ->
            signInGoogle()
        }

        // init constraintLayout
        relativeLayout = findViewById(R.id.Relativelayout)

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = relativeLayout!!.background as AnimationDrawable

        // setting enter fade animation duration to 5 seconds
        animationDrawable!!.setEnterFadeDuration(5000)

        // setting exit fade animation duration to 2 seconds
        animationDrawable!!.setExitFadeDuration(2000)
        var toast: Toast = Toast.makeText(applicationContext, "Welcome\n Developer: Aman Nirala", Toast.LENGTH_LONG)
        toast.setMargin(0F, 0F)
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

    fun getText(): String {
        return link.text.toString()
    }

    //FireBase Signing Functions
    private fun signInGoogle() {
        var signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            var task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
        private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
                updateUI(account)
            } catch (e: ApiException) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG)
            }
        }
           private fun updateUI(account: GoogleSignInAccount)
            {
              signinBtn!!.visibility=View.INVISIBLE
                WelcomeText=findViewById(R.id.WelcomeTxt)
                WelcomeText!!.text= "Hello "+(account.displayName).toString()+" ! "
                floatBtn!!.visibility=View.VISIBLE
                link.visibility=View.VISIBLE
            }
        }