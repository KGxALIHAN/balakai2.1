package com.example.hakatonkgtu.ui.components

import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import com.example.hakatonkgtu.R
import com.example.hakatonkgtu.ui.theme.GreenGG
import com.example.hakatonkgtu.ui.theme.OrangeGG
import com.example.hakatonkgtu.ui.theme.RedG
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomInfoWindow(mapView: MapView) : InfoWindow(R.layout.custom_info_window, mapView) {

    override fun onOpen(item: Any?) {
        val marker = item as? Marker ?: return
        val titleView = mView.findViewById<TextView>(R.id.title)
        val snippetView = mView.findViewById<TextView>(R.id.snippet)
        val img = mView.findViewById<ImageView>(R.id.img)
        titleView.text = marker.title
        snippetView.text = marker.snippet

        when (marker.snippet) {
            "Перегружено" -> {
                snippetView.setTextColor(RedG.toArgb())
                img.setImageResource(R.drawable.ic_red_book)
            }

            "Заполнено" -> {
                snippetView.setTextColor(OrangeGG.toArgb())
                img.setImageResource(R.drawable.ic_orange_book)
            }

            else -> {
                snippetView.setTextColor(GreenGG.toArgb())
                img.setImageResource(R.drawable.ic_green_book)
            }
        }
    }

    override fun onClose() {
        // Можете очистить данные или сделать что-то при закрытии окна
    }
}
