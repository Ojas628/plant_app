package com.example.plantapp.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.plantapp.domain.Classification
import com.example.plantapp.domain.PlantClassifier

class PlantImageAnalyzer(
    private val classifier: PlantClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0){
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(224,224)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)

        }
        frameSkipCounter++


        image.close()
    }
}