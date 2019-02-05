package net.borkiss.randomuser.ui.main

import androidx.lifecycle.MutableLiveData
import net.borkiss.randomuser.common.DisposableViewModel
import net.borkiss.randomuser.data.UserRepository
import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.data.model.ViewState

class MainViewModel(
        private val userRepository: UserRepository
): DisposableViewModel() {

    val users: MutableLiveData<ViewState<List<User>>> = MutableLiveData()

    fun loadUsers() {
        disposables.add(userRepository.getAllUsers()
                .doOnSubscribe {
                    users.postValue(ViewState.Loading())
                }
                .subscribe({
                    users.postValue(ViewState.Data(it))
                }, {
                    users.postValue(ViewState.Error(it))
                }))
    }
}