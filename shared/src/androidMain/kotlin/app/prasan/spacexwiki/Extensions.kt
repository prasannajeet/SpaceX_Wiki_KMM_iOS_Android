@file:JvmName("KotlinExtensions")

package app.prasanpani.dogsapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

@ColorInt
fun Context.getThemeColor(@AttrRes attribute: Int) =
    TypedValue().let { theme.resolveAttribute(attribute, it, true); it.data }

fun Context.openFile(
    fileBytes: ByteArray,
    prefix: String = "",
    fileType: String = ".pkpass",
    mimeType: String = "application/vnd.apple.pkpass",
    onNoApplications: () -> Unit
) {
    getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.let { filePath ->
        val file = File.createTempFile(prefix, fileType, filePath)
        val fos = FileOutputStream(file)
        fos.write(fileBytes)
        fos.close()
        val intent = Intent(ACTION_VIEW).apply {
            setDataAndType(
                FileProvider.getUriForFile(
                    this@openFile, applicationContext.packageName + ".provider",
                    file
                ), mimeType
            )

            flags = Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        }

        if (packageManager.queryIntentActivities(intent, 0).isEmpty()) {
            onNoApplications()
        } else {
            startActivity(Intent.createChooser(intent, "Open File"))
            file.deleteOnExit()
        }
    }
}

inline fun <reified T : Activity> Context.startActivity(bundle: Bundle? = null) {
    Intent(this, T::class.java).apply {
        bundle?.let {
            this.putExtras(it)
        }
    }.also {
        startActivity(it)
    }
}

fun Context.hideKeyboard(windowToken: IBinder) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken, InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun Int.dpToPx(context: Context): Float {
    val scale = context.resources.displayMetrics.density
    return (this*scale + 0.5f)
}