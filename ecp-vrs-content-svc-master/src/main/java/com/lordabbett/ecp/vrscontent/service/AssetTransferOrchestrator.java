package com.lordabbett.ecp.vrscontent.service;


import com.lordabbett.ecp.vrscontent.model.AssetTransferRequest;
import com.lordabbett.ecp.vrscontent.model.AssetTransferResponse;

public interface AssetTransferOrchestrator {

  AssetTransferResponse processAssetTransferRequest(AssetTransferRequest assetTransferRequest,
      String messageId);
}

