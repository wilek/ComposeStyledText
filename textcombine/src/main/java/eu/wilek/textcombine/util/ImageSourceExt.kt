package eu.wilek.textcombine.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.appcompat.content.res.AppCompatResources
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.BitmapSource
import eu.wilek.textcombine.TextCombine.ImageSource
import eu.wilek.textcombine.TextCombine.ImageSource.FromBitmap
import eu.wilek.textcombine.TextCombine.ImageSource.FromDrawable
import kotlin.math.roundToInt

internal fun ImageSource.toImage(
    context: Context,
    size: TextCombine.Size? = null,
    margin: TextCombine.Margin? = null
): Drawable {
    val drawable = when (this) {
        is FromBitmap -> bitmapSource.createBitmap(context = context).toDrawable(context = context)
        is FromDrawable -> AppCompatResources.getDrawable(context, drawableResId)!!
    }
    val startMargin = margin?.start?.toPx(context = context)?.toInt() ?: 0
    val topMargin = margin?.top?.toPx(context = context)?.toInt() ?: 0
    val endMargin = margin?.end?.toPx(context = context)?.toInt() ?: 0
    val bottomMargin = margin?.bottom?.toPx(context = context)?.toInt() ?: 0
    val width = size?.width?.toPx(context = context)
    val height = size?.height?.toPx(context = context)
    val drawableWidth = when {
        width != null -> width.toInt()
        height != null -> ((height / drawable.intrinsicHeight) * drawable.intrinsicWidth).roundToInt()
        else -> drawable.intrinsicWidth
    }
    val drawableHeight = when {
        height != null -> height.toInt()
        width != null -> ((width / drawable.intrinsicWidth) * drawable.intrinsicHeight).roundToInt()
        else -> drawable.intrinsicHeight
    }

    return InsetDrawable(drawable, startMargin, topMargin, endMargin, bottomMargin).apply {
        setBounds(0, 0, startMargin + drawableWidth + endMargin, topMargin + drawableHeight + bottomMargin)
    }
}

private fun BitmapSource.createBitmap(context: Context) = when (this) {
    is BitmapSource.FromAssets -> context.getBitmapFromAssets(bitmapFileName = fileName)
    is BitmapSource.FromUri -> context.getBitmapFromUri(bitmapUri = uri)
}

private fun Context.getBitmapFromAssets(bitmapFileName: String): Bitmap {
    return assets.open(bitmapFileName)
        .use { BitmapFactory.decodeStream(it) }
}

private fun Context.getBitmapFromUri(bitmapUri: String): Bitmap {
    val uri = Uri.parse(bitmapUri)
    return when {
        isAndroidP() -> ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
        else -> MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }
}

private fun Bitmap.toDrawable(context: Context): Drawable {
    val drawable = BitmapDrawable(context.resources, this)
    recycle()
    return drawable
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
private fun isAndroidP() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
