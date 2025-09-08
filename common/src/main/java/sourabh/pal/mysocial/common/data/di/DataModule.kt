package sourabh.pal.mysocial.common.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sourabh.pal.mysocial.common.data.MySocialRepoImpl
import sourabh.pal.mysocial.common.domain.repositories.MySocialRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserDataRepository(userDataRepository: MySocialRepoImpl): MySocialRepository
}