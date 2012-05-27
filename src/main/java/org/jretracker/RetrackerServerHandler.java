package org.jretracker;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;
import org.jretracker.utility.Codec;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Retracker handler.
 */
class RetrackerServerHandler extends SimpleChannelUpstreamHandler {

    private static final Logger logger = Logger.getLogger(RetrackerServerHandler.class.getName());

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        logger.info("Accept request.");
        StringBuilder buffer = new StringBuilder();
        HttpRequest request = (HttpRequest) e.getMessage();
        Map<String, String> params = new HashMap<>();
        String[] urlParts = request.getUri().split("\\?");
        if (urlParts.length == 2) {
            String[] paramsArray = urlParts[1].split("&");

            for (String param : paramsArray) {
                String[] parts = param.split("=", 2);
                if (parts.length  == 2) {

                    if (parts[0].equals("info_hash")) {
                        params.put(parts[0], Codec.getHexStringFromUrl(parts[1]));
                    } else {
                        params.put(parts[0], parts[1]);
                    }
                }
            }
        }
        Pattern p = Pattern.compile("/(.*)\\?");
        Matcher m = p.matcher(request.getUri());
        String action;
        if (m.find()) {
            action = m.group(1);
        } else {
            action = request.getUri().substring(1);
        }
        params.put("ip_from_request", e.getRemoteAddress().toString());
        switch (action) {
            case "announce":
                buffer.append(new AnnounceCommand().execute(params));
                break;
            case "stat":
                buffer.append(new StatCommand().execute(params));
                break;
            default:
                buffer.append("Error. Invalid request.");
                break;
        }

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
