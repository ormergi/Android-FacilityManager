package com.hit.or_oded.facility_manager.logics;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.projects.client_messages.IClientRequest;
import com.projects.server_messages.IServerResponse;

public class ServerStreamer extends Thread
{
	private String mHost;
	private int mPort;

	private ObjectOutputStream mOutputToServer;
	private ObjectInputStream mInputFromServer;
	private boolean mIsConnected;

	public ServerStreamer(String host, int port) 
	{
		mHost = host;
		mPort = port;
	}
	
	@Override
	public void run()
	{
		try 
		{
			Socket socket = new Socket();

			socket.connect(new InetSocketAddress(mHost, mPort), 5000);
			mOutputToServer = new ObjectOutputStream(socket.getOutputStream());
			mInputFromServer = new ObjectInputStream(socket.getInputStream());
			mIsConnected = true;
		} 
		catch (IOException e) 
		{
			mIsConnected = false;
		}		
		
	}
	
	public boolean isConnected()
	{
		return mIsConnected;
	}
	
	public void send(final IServerResponseListener listener, final IClientRequest request)
	{
		new AsyncTask<Void, Void, IServerResponse>()
		{
			@Override
			protected IServerResponse doInBackground(Void... args)
			{
			    IServerResponse response = null;

				try
				{
					mOutputToServer.writeObject(request);
                    response = (IServerResponse) mInputFromServer.readObject();
				}
				catch (ClassNotFoundException | IOException e)
				{
					System.out.println("Error sending to server");
				}

                return response;
			}

			protected void onPostExecute(IServerResponse response)
			{
				listener.onServerResponse(response);
			}
		}.execute();
	}
	
	/*public IServerResponse read()
	{
		IServerResponse response = null;
		
		try 
		{
			response = (IServerResponse)mInputFromServer.readObject();
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			System.out.println("Error reading from server");
		}
		
		return response;
	}*/
}
