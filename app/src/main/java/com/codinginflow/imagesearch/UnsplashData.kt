package com.codinginflow.imagesearchapp

import java.io.Serializable

data class UnsplashData(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
) : Serializable {
    data class Result(
        val blur_hash: String,
        val color: String,
        val created_at: String,
        val current_user_collections: List<Any>,
        val description: String,
        val height: Int,
        val id: String,
        val liked_by_user: Boolean,
        val likes: Int,
        val links: Links,
        val urls: Urls,
        val user: User,
        val width: Int
    ) : Serializable {
        data class Links(
            val download: String,
            val html: String,
            val self: String
        ) : Serializable

        data class Urls(
            val full: String,
            val raw: String,
            val regular: String,
            val small: String,
            val thumb: String
        ) : Serializable

        data class User(
            val first_name: String,
            val id: String,
            val instagram_username: String,
            val last_name: String,
            val links: Links,
            val name: String,
            val portfolio_url: String,
            val profile_image: ProfileImage,
            val twitter_username: String,
            val username: String
        ) : Serializable {
            data class Links(
                val html: String,
                val likes: String,
                val photos: String,
                val self: String
            ) : Serializable

            data class ProfileImage(
                val large: String,
                val medium: String,
                val small: String
            ) : Serializable

            val attributionUrl: String
                get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
        }
    }
}