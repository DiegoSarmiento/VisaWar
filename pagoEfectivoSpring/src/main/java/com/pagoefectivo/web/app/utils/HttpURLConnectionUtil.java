package com.pagoefectivo.web.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpURLConnectionUtil {
	public static String sendPOST(String Post_URL, String Post_PARAMS, Map<String, String> Header_PARAMS) throws IOException {
		System.out.println("POST Post_URL :: \"" + Post_URL + "\"");

		System.out.println("POST PARAMS :: \"" + Post_PARAMS + "\"");

		String responseData = "";
		URL obj = new URL(Post_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
		httpURLConnection.setRequestMethod("POST");
		// httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
		// httpURLConnection.setRequestProperty("Content-Type", Content_TYPE);

		for (Map.Entry<String, String> Header_PARAM : Header_PARAMS.entrySet()) {
			System.out.println("Key = \"" + Header_PARAM.getKey() + "\", Value = \"" + Header_PARAM.getValue() + "\"");
			httpURLConnection.setRequestProperty(Header_PARAM.getKey(), Header_PARAM.getValue());

		}

		// For POST only - START
		byte[] post = Post_PARAMS.getBytes(StandardCharsets.UTF_8);
		int postLength = post.length;
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setFixedLengthStreamingMode(postLength);
		OutputStream os = httpURLConnection.getOutputStream();

		os.write(post);
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = httpURLConnection.getResponseCode();
		System.out.println("POST Response Code :: \"" + responseCode + "\"");

		if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) { // success
			responseData = "";
		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			responseData = response.toString();
		}
		System.out.println("POST Response Data :: \"" + responseData + "\"");
		return responseData;
	}
}
