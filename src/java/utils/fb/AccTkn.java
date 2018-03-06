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
package utils.fb;

import com.restfb.FacebookClient.AccessToken;
import java.util.Date;

/**
 *
 */
public final class AccTkn {
    private static String fbAccessToken;
    private String verifCode;
    private static Date tknExpDate;

    public void setFbAccessToken(String verifCode) {
        FbAppCredentials fbAppCredentials = new FbAppCredentials();
        try{
            AccessToken accessToken = CreateFbClient.getClient().obtainUserAccessToken(fbAppCredentials.getAPPID(), fbAppCredentials.getAPPSECRET(), fbAppCredentials.getREDIRECTURL(), verifCode);
            fbAccessToken = accessToken.getAccessToken();
            long mili = accessToken.getExpires().getTime();
            tknExpDate = new Date(mili);
        }catch(NullPointerException ex){
            System.out.println("Error creating access token object");
            System.out.println("Access token is "+fbAccessToken);
        }
    }

    public static String getFbAccessToken() {
        return fbAccessToken;
    }

    public static Date getTknExpDate() {
        return tknExpDate;
    }
    
    public static void resetCredentials(){
        fbAccessToken = null;
        tknExpDate = null;
    }
}
