package net.borkiss.randomuser.di

import net.borkiss.randomuser.data.UserRepository
import net.borkiss.randomuser.data.UserRepositoryImpl
import net.borkiss.randomuser.service.RetrofitFactory
import net.borkiss.randomuser.service.UsersService
import net.borkiss.randomuser.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelsModule: Module = module {
    viewModel { MainViewModel(userRepository = get()) }
}

val apiModule: Module = module {
    single { RetrofitFactory.createService(UsersService::class.java) }
    factory { UserRepositoryImpl(usersService = get()) as UserRepository }
}