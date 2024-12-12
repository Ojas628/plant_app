package com.example.plantapp.domain

import android.graphics.Bitmap

interface PlantClassifier {

    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}