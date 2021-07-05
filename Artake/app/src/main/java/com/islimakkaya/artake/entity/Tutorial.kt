package com.islimakkaya.artake.entity

import java.io.Serializable

data class Tutorial(var tutorial_id: Int,
                    var tutorial_name: String,
                    var tutorial_description: String,
                    var tutorial_image_name: String): Serializable {
}