package me.web.framework.websocket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket处理类
 * @author HoloStan
 *
 */
public class WebsocketEndPoint extends TextWebSocketHandler {
	
	private Logger logger = Logger.getLogger(WebsocketEndPoint.class);
	
	private static final List<WebSocketSession> webSocketSession;
	
	static {
		webSocketSession = new ArrayList<WebSocketSession>();
	}

	/**
	 * recall from [JS]websocket.send()
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);  
		logger.debug("recived message:"+message.getPayload()+"from user:"+session.getId());
		TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server"); 
		logger.debug("send text message for user:"+session.getId());
		session.sendMessage(returnMessage);  
	}
	
	/**
     * recall from [JS]websocket.onopen()
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("user:"+session.getId()+" connect to the server ，connection saved");
		webSocketSession.add(session);
		logger.debug("send established message for user:"+session.getId());
		session.sendMessage(new TextMessage("Connected!!!"));
//		super.afterConnectionEstablished(session);
	}
	
	/**
     * Handle an error from the underlying WebSocket message transport.
     */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if(session.isOpen()){
			session.close();
        }
        logger.debug("websocket connection closed......");
        webSocketSession.remove(session);
	}
	
	 /**
     * Invoked after the WebSocket connection has been closed by either side,
     * or after a transport error has occurred. Although the session may technically still be open,
     * depending on the underlying implementation, sending messages at this point is discouraged and most likely will not succeed.
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		 logger.debug("websocket connection closed......");
	     webSocketSession.remove(session);
	}
	
	@Override
	public boolean supportsPartialMessages() {
		return super.supportsPartialMessages();
	}
	
}
