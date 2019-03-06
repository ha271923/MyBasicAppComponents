package sample.hawk.kotlinlibrary;
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View

/**
 * Created by ha271 on 2019/3/6.
 *
 * Compare Kotlin with JAVA
 * 1. Java需要在程式碼結束後加上";"，Kotlin不用
 * 2. Java需要宣告變數型態，Kotlin可以不用
 * 3. Java變數型態宣告在前面，Kotlin在後面
 *
 */
open class KotlinVSJavaActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlinvsjava_activity)

    }

    fun onClickKotlinTest(v: View?) {
        var dozen = 3
        Log.d("KotlinVSJavaActivity", "I have ${dozen*12} bottles of beer") // System.out.println("I have " + (dozen*12) + " bottles of beer")
        var name: String = "Cindy" //可變更name的值，變數型態在變數名稱後面
        val name1: String = "Cindy" //不可變更name1的值
        //另外也可以不給變數型態，由Kotlin來判斷
        var name2 = "Cindy" //直接給值，判定為String
        var age = 10 //判定為Int
        var isGirl = true //判定為boolean
        var height = 155.55 //判定為Double
    }

}