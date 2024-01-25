package com.dreamsoftware.tvnexa.utils

import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO

interface ISessionAware {
    var session: AuthSessionBO?
}