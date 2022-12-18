package com.example.asyncdemo

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.os.AsyncTask
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    lateinit var tv1 : TextView
    lateinit var tv2 : TextView
    lateinit var btn : Button
    lateinit var ed1 : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1=findViewById(R.id.tv1)
        tv2=findViewById(R.id.tv2)
        btn=findViewById(R.id.btn)
        ed1=findViewById(R.id.ed1)

        btn.setOnClickListener {

            var asyn=AsyncTest()
            var ht=ed1.text.toString()
            asyn.execute(ht)

        }


    }

    inner class AsyncTest : AsyncTask<String,String,String>(){

        lateinit var res:String
        lateinit var pd:ProgressDialog

        override fun doInBackground(vararg p0: String?): String {
            try {
                publishProgress("on Hold...")
                var t=Integer.parseInt(p0[0])*1000
                Thread.sleep(t.toLong())
                res="on Hold for"+p0[0]

            }catch (e:InternalError){
                res=e.message.toString()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            pd.dismiss()
            tv2.text=result
        }

        override fun onPreExecute() {
            pd=ProgressDialog.show(this@MainActivity,"Progess","Wait for "
                    +ed1.text.toString()+" seconds")
        }

        override fun onProgressUpdate(vararg values: String?) {
            tv2.text=values[0]
        }


    }
}