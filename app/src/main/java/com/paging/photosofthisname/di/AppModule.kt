package com.paging.photosofthisname.di



import com.paging.photosofthisname.data.api.SearchAPI
import com.paging.photosofthisname.data.network.provideRetrofit
import com.paging.photosofthisname.data.repo.SearchDataRepository
import com.paging.photosofthisname.domain.repo.SearchRepository
import com.paging.photosofthisname.domain.usecase.SearchUseCase
import com.paging.photosofthisname.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get())}
}

val networkModules = module {
    single {
        provideRetrofit<SearchAPI>(
            BASE_URL
        )
    }
}

val useCaseModules = module {
    factory{
        SearchUseCase(get())
    }
}

val repositoryModule = module {
    single<SearchRepository> { SearchDataRepository(get()) }
}

private const val BASE_URL = "https://www.flickr.com/services/rest/"






