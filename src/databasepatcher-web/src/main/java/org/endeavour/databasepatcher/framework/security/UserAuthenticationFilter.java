package org.endeavour.databasepatcher.framework.security;

import org.endeavour.databasepatcher.framework.exceptions.NotAuthorizedException;

public final class UserAuthenticationFilter extends AbstractAuthenticationFilter {

    @Override
    public void doSpecificAuthoriationCheck(UserContext cx) throws NotAuthorizedException {
        //this implementation has no extra checks other than having a signed on user
    }
}
