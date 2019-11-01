package ${package};

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.saml.SAMLCallback;
import org.apache.wss4j.common.saml.bean.SubjectBean;
import org.apache.wss4j.common.saml.bean.Version;
import org.apache.wss4j.common.saml.builder.SAML2Constants;

import org.springframework.stereotype.Component;

@Component("samlCallbackBean")
public class CustomSAMLCallback implements Callback, CallbackHandler {
    private String user = "someuser";

    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof SAMLCallback) {
                SAMLCallback callback = (SAMLCallback) callbacks[i];

                callback.setSamlVersion(Version.SAML_20);
                callback.setIssuer("someissuer");

                SubjectBean subjectBean = new SubjectBean(user, "", SAML2Constants.CONF_SENDER_VOUCHES);
                callback.setSubject(subjectBean);
            } else {
                throw new UnsupportedCallbackException(callbacks[i], "Unrecognized call-back: expecting an instance of SAMLCallback");
            }
        }
    }
}