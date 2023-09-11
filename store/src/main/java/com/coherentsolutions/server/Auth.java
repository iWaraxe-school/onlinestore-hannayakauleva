package com.coherentsolutions.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {

    public Auth(String get) {
        super(get);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
