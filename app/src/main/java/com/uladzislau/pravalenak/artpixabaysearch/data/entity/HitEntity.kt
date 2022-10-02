package com.uladzislau.pravalenak.artpixabaysearch.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("type")
    val type: String,
    @SerialName("tags")
    val tags: String,
    @SerialName("previewURL")
    val url: String,
    @SerialName("views")
    val viewsCount: Int,
    @SerialName("downloads")
    val downloadsCount: Int,
    @SerialName("likes")
    val likesCount: Int,
    @SerialName("comments")
    val commentsCount: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("user")
    val userName: String,
    @SerialName("userImageURL")
    val userImageURL: String,
    @SerialName("largeImageURL")
    val largeImageURL: String
)
//{
//    "type": "photo",
//    "tags": "blossom, bloom, flower",
//    "previewURL": "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg"
//    "previewWidth": 150,
//    "previewHeight": 84,
//    "webformatURL": "https://pixabay.com/get/35bbf209e13e39d2_640.jpg",
//    "webformatWidth": 640,
//    "webformatHeight": 360,
//    "largeImageURL": "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg",
//    "fullHDURL": "https://pixabay.com/get/ed6a9369fd0a76647_1920.jpg",
//    "imageURL": "https://pixabay.com/get/ed6a9364a9fd0a76647.jpg",
//    "imageWidth": 4000,
//    "imageHeight": 2250,
//    "imageSize": 4731420,
//    "views": 7671,
//    "downloads": 6439,
//    "likes": 5,
//    "comments": 2,
//    "user_id": 48777,
//    "user": "Josch13",
//    "userImageURL": "https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg",
//}