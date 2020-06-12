package com.vincent.dreemtest.domain.usecase

import com.vincent.dreemtest.domain.functional.SynchronousInteractor

class IsSignedIn: SynchronousInteractor<Boolean, Unit>() {

    override fun runOnForeground(params: Unit): Boolean = true

}