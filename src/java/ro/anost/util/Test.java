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
package ro.anost.util;

/**
 *
 * @author George
 */
public class Test {
    
}

@RequestMapping(value = "/account/fbsignin", method = RequestMethod.GET)
	public String faceBookLogin(HttpServletRequest request, ModelMap model){
		String fbsigninUrl = "https://www.facebook.com/dialog/oauth?client_id=appid&redirect_uri=yourdomain%2Faccount%2Ffbsignin&scope=email&state=";
		String fbIdConfirmUrl = "https://graph.facebook.com/v2.3/oauth/access_token?client_id=appid&redirect_uri=yourdomain%2Faccount%2Ffbsignin&client_secret=appsecret&code=";
		String fbappsec = "appsecret";

		
		//check to see if user already logged in
		if(!("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName()))){
			return "redirect:/myaccount.htm";
		}
		String fbcode =  request.getParameter("code");
		String state =  request.getParameter("state");

		if(StringUtils.isEmpty(state) || StringUtils.isEmpty(fbcode)){
			String statekey = RandomStringUtils.randomAlphanumeric(40);
			request.getSession().setAttribute("fbstate", statekey);
			return "redirect:"+fbsigninUrl+statekey;				
		}else{
			String statekey = (String)request.getSession().getAttribute("fbstate");
			if(!state.equals(statekey)){
				model.addAttribute("errormsg","Login error");	
				return "loginpage";
			}
			FacebookClient fbclient = new DefaultFacebookClient(Version.VERSION_2_3);

			try {
				Response fbrs = fbclient.getWebRequestor().executeGet(fbIdConfirmUrl+fbcode);
				if(fbrs.getStatusCode() != 200){
					model.addAttribute("errormsg","Login error");	
					return "loginpage";
				}
				JsonObject json = new JsonObject(fbrs.getBody().toString());

				request.getSession().setAttribute("fbacesstoken", json.getString("access_token"));
				
				FacebookClient facebookClient = new DefaultFacebookClient(json.getString("access_token"), fbappsec, Version.VERSION_2_3);
				User user = facebookClient.fetchObject("me", User.class);

							
				processSocialLogin(user.getId(),user.getEmail(), user.getFirstName()+" "+user.getLastName(),
						                      "FB",json.getString("access_token"), request);

			} catch (Exception e) {
				model.addAttribute("errormsglg","Login error");	
				return "loginpage";
			}

		}			
		return "redirect:/myaccount.htm";
	}