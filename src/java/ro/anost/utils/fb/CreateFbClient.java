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

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class CreateFbClient {
    static FacebookClient client;
    static FacebookClient fbClient;

    public static FacebookClient getClient() {
        client = new DefaultFacebookClient(Version.VERSION_2_11);
        return client;
    }

    public static FacebookClient getFbClient() {
        try{
            fbClient = new DefaultFacebookClient(AccTkn.getFbAccessToken(), Version.VERSION_2_11);            
        }catch(NullPointerException ex){
            System.out.println("Problem creating fbClient object. Most probably no access token aquired.");
            Logger.getLogger(CreateFbClient.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return fbClient;
    }
    
}
