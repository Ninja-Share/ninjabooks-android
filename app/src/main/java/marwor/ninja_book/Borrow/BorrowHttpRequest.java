package marwor.ninja_book.Borrow;



import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;

import java.net.HttpURLConnection;

import java.net.URL;

import marwor.ninja_book.UserData;

/**
 * Created by Marcin_stacjonarny on 2017-06-25.
 */

public class BorrowHttpRequest {
    HttpURLConnection urlConnectionToBorrow = null;
    BorrowJsonMaker jsonMaker=new BorrowJsonMaker();

    public int BorrowPutRequest(URL url,String bookId,String token){
        int response=0;
        try {
            urlConnectionToBorrow = (HttpURLConnection) url.openConnection();
            urlConnectionToBorrow.setRequestMethod("PUT");
            urlConnectionToBorrow.setRequestProperty("Authorization","Bearer "+token);
            urlConnectionToBorrow.setChunkedStreamingMode(0);
            JSONObject borrowData=jsonMaker.BorrowJson(bookId);

            DataOutputStream outBorrowData = new DataOutputStream(urlConnectionToBorrow.getOutputStream());
            outBorrowData.writeBytes(borrowData.toString());
            outBorrowData.flush();
            outBorrowData.close();

            response=urlConnectionToBorrow.getResponseCode();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
