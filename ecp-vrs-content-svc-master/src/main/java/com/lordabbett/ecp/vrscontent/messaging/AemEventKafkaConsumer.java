package com.lordabbett.ecp.vrscontent.messaging;

import static com.lordabbett.ecp.commons.logging.EcpLoggingConstants.ECP_REQUEST_ID;

import com.lordabbett.ecp.commons.model.AemAssetType;
import com.lordabbett.ecp.commons.model.EcpKafkaEvent;
import com.lordabbett.ecp.commons.model.EcpKafkaEventHeader;
import com.lordabbett.ecp.vrscontent.model.AssetTransferRequest;
import com.lordabbett.ecp.vrscontent.service.AssetTransferOrchestrator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This the component that is listening to the kafka topic. Once a new message is received on the
 * topic, the processing begins.
 */
@Component
public class AemEventKafkaConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(AemEventKafkaConsumer.class);
  private Map<String, AssetTransferOrchestrator> assetHandlerMap;

  @Autowired
  public AemEventKafkaConsumer(
      @Qualifier("orchestratorMapping") Map<String, AssetTransferOrchestrator> assetHandlerMap) {
    this.assetHandlerMap = assetHandlerMap;
  }


  @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}")
  public void processIncomingAemEvent(EcpKafkaEvent ecpKafkaEvent) {
    EcpKafkaEventHeader header = ecpKafkaEvent.getHeader();
    String messageId = header.getMessageId();
    LOG.info("Received message with id:{}", messageId);
    MDC.put(ECP_REQUEST_ID, messageId);
    if (needsProcessing(ecpKafkaEvent)) {
      LOG.info("starting to process event: {} ", ecpKafkaEvent);
      AssetTransferOrchestrator orchestrator = assetHandlerMap
          .get(header.getAemAssetType() + "_" + header.getAemEventType());
      orchestrator
          .processAssetTransferRequest(transformToAssetTransferRequest(ecpKafkaEvent), messageId);
      LOG.info("finished processing incoming request: {} ", ecpKafkaEvent);
    } else {
      LOG.info("Skipping message with messageId: {}", messageId);

    }
    MDC.remove(ECP_REQUEST_ID);
  }

  private AssetTransferRequest transformToAssetTransferRequest(EcpKafkaEvent ecpKafkaEvent) {
    Map<String, Object> payload = ecpKafkaEvent.getPayload();
    EcpKafkaEventHeader header = ecpKafkaEvent.getHeader();
    AssetTransferRequest assetTransferRequest = new AssetTransferRequest();
    assetTransferRequest.setAssetPath((String) payload.get("assetPath"));
    assetTransferRequest.setJcrUuid((String) payload.get("jcrUuid"));
    assetTransferRequest.setEventType(header.getAemEventType());
    assetTransferRequest.setAssetType(header.getAemAssetType());
    return assetTransferRequest;
  }

  private boolean needsProcessing(EcpKafkaEvent ecpKafkaEvent) {
    return ecpKafkaEvent.getHeader().getAemAssetType() == AemAssetType.IP_BIOGRAPHY;
  }
}
