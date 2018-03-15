/*
 * The MIT License
 *
 * Copyright 2018 George.
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
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class UserDetails {

    private String userId;
    private String accTkn;
    private String pictureUrl;
    private String message;

    public UserDetails(String userId, String accTkn) {
        /*try{
            FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_11);
            FacebookClient fbClient = new DefaultFacebookClient(accTkn, Version.VERSION_2_11);
            try {
                String searchCriteria = userId+"/picture";
                com.restfb.Connection<User> user = fbClient.fetchConnection(searchCriteria, User.class, Parameter.with("height", "500"));
                this.pictureUrl = user.getData().get(2).getPicture().getUrl();
            } catch (FacebookGraphException ex) {
                System.out.println("Could not create user object. Most common problem known: user did not logged in with Facebook account.");
                this.message = "Could not retrieve address for user's picture. Be sure you are logged in with your Facebook account. If problem persists, please contact the administrator!";
                Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (FacebookOAuthException ex) {
            System.out.println("Could not create fbClient Object. Check API version or access token.");
            this.message = "Could not connect with Facebook. If problem persists, please contact the administrator.";
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getMessage() {
        return message;
    }
    
}
