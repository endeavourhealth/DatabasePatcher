package org.endeavour.databasepatcher.framework.security;

import org.endeavour.databasepatcher.framework.exceptions.NotAuthorizedException;

public final class SuperUserAuthenticationFilter extends AbstractAuthenticationFilter {
    @Override
    public void doSpecificAuthoriationCheck(UserContext cx) throws NotAuthorizedException {
        if (!cx.isSuperUser()) {
            throw new NotAuthorizedException("Insufficient permissions");
        }
    }
}
