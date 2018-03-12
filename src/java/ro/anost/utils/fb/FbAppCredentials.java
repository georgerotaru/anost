/*
 * The MIT License
 *
 * Copyright 2018 George <mrgeorge.ro @ gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ro.anost.utils.fb;

import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;

/**
 * This class sets the Facebook credentials and constructs the URL to which a
 * user is redirected to login with his/her FB account.
 * It also sets which permissions will this user be asked to give access to.
 */
public class FbAppCredentials {
    private final String APPID = "1392269750899694";
    private final String APPSECRET = "";
    
    private final String SERVERADDR = "192.168.102.91";
    
    private final String REDIRECTURL = "http://"+SERVERADDR+":8888/anost/FbLogin";
    //private final Parameter ADDITIONALPARAM = Parameter.with("state", "");
    private final ScopeBuilder SCOPEBUILDER = new ScopeBuilder();
    private String loginDialog;

    public String getAPPID() {
        return APPID;
    }

    public String getAPPSECRET() {
        return APPSECRET;
    }

    public String getREDIRECTURL() {
        return REDIRECTURL;
    }

    //public Parameter getADDITIONALPARAM() {
    //    return ADDITIONALPARAM;
    //}

    public ScopeBuilder getSCOPEBUILDER() {
        SCOPEBUILDER.addPermission(FacebookPermissions.RSVP_EVENT);
        return SCOPEBUILDER;
    }

    public String getLoginDialog() {
        loginDialog = CreateFbClient.getClient().getLoginDialogUrl(APPID, REDIRECTURL, getSCOPEBUILDER());
        return loginDialog;
    }
    
}
