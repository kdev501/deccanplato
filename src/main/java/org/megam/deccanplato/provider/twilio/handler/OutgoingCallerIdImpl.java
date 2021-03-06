/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.twilio.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.twilio.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

/**
 * @author pandiyaraja
 *
 *This Class implements Outgoing caller id business activities like
 *create caller id, list, update and delete caller in twilio.
 */
public class OutgoingCallerIdImpl implements BusinessActivity{

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args = tempArgs;
			this.bizInfo = tempBizInfo;
		
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=new HashMap<String, String>();
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=list(outMap);
			break;
		case CREATE:
			outMap=create(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		case UPDATE:
			outMap=update(outMap);
			break;
		case DELETE:
			outMap=delete(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * This method delete a particular OutgoingCallerIds by using Outgoing
	 * Caller id SID.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/OutgoingCallerIds/"+args.get(OUTGOING_CALLER_SID)+".json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		
		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.delete(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * this method updates a particula outgoing number by using  
	 * outgoing caller SID.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/OutgoingCallerIds/"+args.get(OUTGOING_CALLER_SID)+".json";

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		
		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * 
	 * This method returns a particular outgoing number details by using
	 * outgoing caller SID.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/OutgoingCallerIds/"+args.get(OUTGOING_CALLER_SID)+".json";

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * Create method creates a outgoing caller ID by using a phone number,
	 * user have to verify after created the outgoing callerid by entering a 5to 6
	 * digit number which is get from return statement of thid method.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/OutgoingCallerIds.json";

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		List<NameValuePair> callerList =new ArrayList<NameValuePair>();
		callerList.add(new BasicNameValuePair("PhoneNumber", args.get(PHONENO)));
		callerList.add(new BasicNameValuePair("FriendlyName", args.get(FRIENDLY_NAME)));
		callerList.add(new BasicNameValuePair("CallDelay", args.get(CALL_DELAY)));
		
		TransportTools tst = new TransportTools(account_view_url, callerList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method lists all outgoing caller id phone numbers
	 * from twilio account.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/OutgoingCallerIds.json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "callerid";
	}

}
