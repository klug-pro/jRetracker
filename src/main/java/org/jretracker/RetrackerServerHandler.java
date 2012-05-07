package org.jretracker;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.jboss.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Retracker handler.
 */
public class RetrackerServerHandler extends SimpleChannelUpstreamHandler {

    private static Logger logger = Logger.getLogger(RetrackerServerHandler.class.getName());

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        logger.info("Accept request.");
        StringBuilder buffer = new StringBuilder();
        buffer.append("<html><body>");
        HttpRequest request = (HttpRequest) e.getMessage();
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
        Map<String, List<String>> params = queryStringDecoder.getParameters();
        buffer.append("Parameters:<br />");
        for (String key : params.keySet()) {
            buffer.append(key);
            buffer.append("<br />");
        }
        buffer.append("</body></html>");
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);

        response.setContent(ChannelBuffers.copiedBuffer(buffer.toString(), CharsetUtil.UTF_8));
        response.setHeader(CONTENT_TYPE, "text/html; charset=UTF-8");

        ChannelFuture future = e.getChannel().write(response);
        future.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getChannel().close();
    }
}
