package com.hit.or_oded.facility_manager.logics;

import com.projects.client_messages.*;
import com.projects.enums.*;
import com.projects.persons.*;

public class Logics
{
	private ServerStreamer mServerStreamer = new ServerStreamer("10.100.102.2", 10001);
	private static Logics mInstance = new Logics();
	private boolean mStarted = false;
	private IServerResponseListener mListener;

	public static Logics getInstance()
	{
	    if (!mInstance.mStarted)
		{
			mInstance.start();
		}

		return mInstance;
	}

	public void setListener(IServerResponseListener listener)
    {
        mListener = listener;
    }

	private Logics() {}

	private void start()
	{
		try
		{
			mServerStreamer.start();
			mServerStreamer.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		mStarted = true;
	}
	
	public void askToCreateNewUser(Person person, String password)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new NewUserRequest(person, password));
		}
	}

    public void askLogin(eClientKind kind, String id, String password)
    {
        if (mServerStreamer.isConnected())
        {
            mServerStreamer.send(mListener, new LoginRequest(kind, id, password));
        }
    }
	
	public void askTenantMonthsPaid(String tenantId)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new TenantMonthsPaidRequest(tenantId));
		}
	}
	
	public void askApartmentsPayments()
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new ApartmentsPaymentsRequest());
		}
	}
	
	public void addTenantPayment(String id, int month, int amount)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new AddTenantPaymentRequest(id, month, amount));
		}
	}
	
	public void askApartmentMonthlyPayments(int apartmentNumber)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new ApartmentMonthlyPaymentsRequest(apartmentNumber));
		}
	}
	
	public void askProvidersByCategory(eProvidersCategories category)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new ProviderByCategoryRequest(category));
		}
	}
	
	public void askOptimalProvider(eProvidersCategories category)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new OptimalProviderRequest(category));
		}
	}
	
	public void askToAddOrUpdateProvider(Provider provider)
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new AddOrUpdateProviderRequest(provider));
		}
	}
	
	public void askTenantPayments() // this method should be called from tenant client only
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new TenantPaymentsRequest());
		}
	}
	
	public void askConnectedPersonDetails()
	{
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(mListener, new ConnectedPersonDetailsRequest());
		}
	}
}
