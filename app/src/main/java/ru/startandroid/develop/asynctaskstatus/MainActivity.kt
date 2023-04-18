package ru.startandroid.develop.asynctaskstatus

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mt: MyTask? = null
    var tvInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById<View>(R.id.tvInfo) as TextView
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.btnStart -> startTask()
            R.id.btnStatus -> showStatus()
        }
    }

    private fun startTask() {
        mt = MyTask()
        mt!!.execute()
        mt!!.cancel(false)
    }

    fun showStatus() {
        if (mt != null)
            if (mt!!.isCancelled) {
                Toast.makeText(this, mt!!.status.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, mt!!.status.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    internal inner class MyTask :
        AsyncTask<Unit, Unit, Unit>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo!!.text = "Begin"
        }

        override fun doInBackground(vararg params: Unit?): Unit? {
            try {
                for (i in 0..5) {
                    if (isCancelled) return null
                    Thread.sleep(1000)
                }
            } catch (e:InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            tvInfo!!.text = "End"
        }

        override fun onCancelled() {
            super.onCancelled()
            tvInfo!!.text = "Cancel"
        }
    }
}