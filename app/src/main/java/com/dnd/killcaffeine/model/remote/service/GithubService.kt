/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.model.remote.service

import com.dnd.killcaffeine.model.data.GithubRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{uesr}/repos")
    fun getUserRepositoryList(@Path("user") id: String) : Single<ArrayList<GithubRepo>>
}