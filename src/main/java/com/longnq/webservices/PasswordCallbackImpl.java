package com.longnq.webservices;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordCallbackImpl implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for(int i=0;i < callbacks.length; i++) {
			WSPasswordCallback passwordCallback = (WSPasswordCallback) callbacks[i];
			if(passwordCallback.getIdentifier().equals("user1")) {
				passwordCallback.setPassword("123456");
				return;
			}
		}

	}

}
