package com.hit.or_oded.facility_manager.logics;

import java.io.Serializable;

import com.projects.server_messages.IServerResponse;

public interface IServerResponseListener extends Serializable
{
    void onServerResponse(IServerResponse response);
}
