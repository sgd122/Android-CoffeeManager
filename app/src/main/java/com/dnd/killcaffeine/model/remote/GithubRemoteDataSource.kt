/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.model.remote

import com.dnd.killcaffeine.model.data.GithubRepo
import com.dnd.killcaffeine.model.remote.service.GithubService
import io.reactivex.Single

class GithubRemoteDataSource(private val mGithubService: GithubService) : GithubService {

    override fun getUserRepositoryList(id: String): Single<ArrayList<GithubRepo>> {
        return mGithubService.getUserRepositoryList(id = id)
    }
}